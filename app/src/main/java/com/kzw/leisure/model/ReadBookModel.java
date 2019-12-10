package com.kzw.leisure.model;

import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.network.RetrofitHelper;
import com.kzw.leisure.realm.BookContentBean;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.rxJava.RxHelper;
import com.kzw.leisure.rxJava.RxSchedulers;
import com.kzw.leisure.utils.AnalyzeRule;
import com.kzw.leisure.utils.LogUtils;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.StringUtils;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;
import io.realm.Realm;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class ReadBookModel implements ReadBookContract.Model {
    @Override
    public Flowable<List<Chapter>> getChapterList(BookSourceRule rule, String path) {
        return RetrofitHelper.getInstance().getResponse(rule.getBaseUrl(), path)
                .flatMap((Function<String, Publisher<List<Chapter>>>) s -> analyze(s, rule, path))
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }

    public Flowable<BookContentBean> getContent(BookSourceRule sourceRule, Chapter chapter) {
        LogUtils.e(sourceRule.getBaseUrl() + chapter.getChapterUrl());
        return RetrofitHelper.getInstance().getResponse(sourceRule.getBaseUrl(), chapter.getChapterUrl())
                .flatMap((Function<String, Publisher<BookContentBean>>) s -> analyzeContent(s, sourceRule, chapter))
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main())
                .flatMap((Function<BookContentBean, Publisher<BookContentBean>>) bookContentBean -> saveContent(bookContentBean));

    }


    private Flowable<List<Chapter>> analyze(String body, BookSourceRule sourceRule, String path) {
        return Flowable.create(emitter -> {
            List<Chapter> chapterList = new ArrayList<>();
            AnalyzeRule analyzer = new AnalyzeRule(null);
            analyzer.setContent(body, sourceRule.getRuleSearchUrl());
            try {
                List<Object> collections = analyzer.getElements(sourceRule.getRuleChapterList());
                for (int i = 0; i < collections.size(); i++) {
                    Object object = collections.get(i);
                    analyzer.setContent(object);
                    Chapter chapter = new Chapter();
                    chapter.setChapterName(analyzer.getString(sourceRule.getRuleChapterName()));
                    chapter.setChapterUrl(path + analyzer.getString(sourceRule.getRuleChapterUrl()));
                    chapter.setIndex(i);
                    chapterList.add(chapter);
                }
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
            emitter.onNext(chapterList);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }

    private Flowable<BookContentBean> analyzeContent(String body, BookSourceRule sourceRule, Chapter chapter) {
        return Flowable.create(emitter -> {
            BookContentBean bookContentBean = new BookContentBean();
            AnalyzeRule analyzeRule = new AnalyzeRule(null);
            analyzeRule.setContent(body, sourceRule.getRuleChapterUrl());
            try {
                bookContentBean.setDurChapterContent(StringUtils.formatHtml(analyzeRule.getString(sourceRule.getRuleContentUrl())));
                bookContentBean.setDurChapterIndex(chapter.getIndex());
                bookContentBean.setDurChapterUrl(chapter.getChapterUrl());
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
            emitter.onNext(bookContentBean);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }

    private Flowable<BookContentBean> saveContent(BookContentBean bean) {
        return Flowable.create(emitter -> {
            RealmHelper.getInstance().getRealm().executeTransaction(realm -> {
                BookContentBean bookContentBean = realm.createObject(BookContentBean.class);
                bookContentBean.setDurChapterUrl(bean.getDurChapterUrl());
                bookContentBean.setDurChapterIndex(bean.getDurChapterIndex());
                bookContentBean.setDurChapterContent(bean.getDurChapterContent());
            });
            emitter.onNext(bean);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }

}
