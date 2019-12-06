package com.kzw.leisure.model;

import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.network.RetrofitHelper;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.rxJava.RxHelper;
import com.kzw.leisure.rxJava.RxSchedulers;
import com.kzw.leisure.utils.AnalyzeRule;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class ReadBookModel implements ReadBookContract.Model {
    @Override
    public Flowable<List<Chapter>> getChapterList(BookSourceRule rule, BookRealm bookRealm) {
        return RetrofitHelper.getInstance().getResponse(rule.getBaseUrl(), bookRealm.getChapterListUrl())
                .flatMap((Function<String, Publisher<List<Chapter>>>) s -> analyze(s, rule))
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }

    private Flowable<List<Chapter>> analyze(String body, BookSourceRule sourceRule) {
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
                    chapter.setChapterUrl(analyzer.getString(sourceRule.getRuleChapterUrl()));
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
}
