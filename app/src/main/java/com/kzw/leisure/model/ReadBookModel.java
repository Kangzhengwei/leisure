package com.kzw.leisure.model;

import android.text.TextUtils;

import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.ChapterRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.network.RetrofitHelper;
import com.kzw.leisure.realm.BookContentBean;
import com.kzw.leisure.realm.ChapterList;
import com.kzw.leisure.rxJava.RxHelper;
import com.kzw.leisure.rxJava.RxSchedulers;
import com.kzw.leisure.utils.NetworkUtils;
import com.kzw.leisure.utils.analyze.AnalyzeRule;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.StringUtils;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class ReadBookModel implements ReadBookContract.Model {

    /**
     * 获取章节列表
     *
     * @param query
     * @param rule
     * @param chapterList
     * @param isFromNet
     * @return
     */
    @Override
    public Flowable<List<Chapter>> getChapterList(Query query, ChapterRule rule, ChapterList chapterList, boolean isFromNet) {
        if (TextUtils.isEmpty(chapterList.getChapterListCache()) || isFromNet) {
            return RetrofitHelper
                    .getInstance()
                    .getResponse(query)
                    .compose(RxSchedulers.io_main())
                    .flatMap((Function<String, Publisher<String>>) s -> {
                        RealmHelper.getInstance().getRealm().executeTransaction(realm -> {
                            chapterList.setChapterListCache(s);
                        });
                        return Flowable.just(s);
                    })
                    .observeOn(Schedulers.io())
                    .flatMap((Function<String, Publisher<List<Chapter>>>) s -> analyzeChapterList(s, rule, query.getPath()))
                    .compose(RxHelper.handleResult())
                    .compose(RxSchedulers.io_main());
        } else {
            return getCacheList(chapterList, rule, query.getPath());
        }
    }

    /**
     * 获取缓存章节列表后进行解析
     *
     * @param chapterList
     * @param sourceRule
     * @param path
     * @return
     */
    public Flowable<List<Chapter>> getCacheList(ChapterList chapterList, ChapterRule sourceRule, String path) {
        return analyzeChapterList(chapterList.getChapterListCache(), sourceRule, path);
    }

    /**
     * 解析章节列表
     *
     * @param body
     * @param sourceRule
     * @param path
     * @return
     */
    private Flowable<List<Chapter>> analyzeChapterList(String body, ChapterRule sourceRule, String path) {
        return Flowable.create(emitter -> {
            List<Chapter> chapterList = new ArrayList<>();
            AnalyzeRule analyzer = new AnalyzeRule(null);
            analyzer.setContent(body);
            try {
                List<Object> collections = analyzer.getElements(sourceRule.getRuleChapterList());
                for (int i = 0; i < collections.size(); i++) {
                    Object object = collections.get(i);
                    analyzer.setContent(object);
                    Chapter chapter = new Chapter();
                    chapter.setChapterName(analyzer.getString(sourceRule.getRuleChapterName()));
                    if (sourceRule.getRuleChapterUrlType() == 0) {
                        chapter.setChapterUrl(path + analyzer.getString(sourceRule.getRuleChapterUrl()));
                    } else if (sourceRule.getRuleChapterUrlType() == 1) {
                        chapter.setChapterUrl(analyzer.getString(sourceRule.getRuleChapterUrl()));
                    }
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


    /**
     * 获取章节内容
     *
     * @param sourceRule
     * @param chapter
     * @return
     */
    public Flowable<BookContentBean> getContent(ChapterRule sourceRule, Chapter chapter) {
        try {
            Query query = new Query(chapter.getChapterUrl(), null, sourceRule.getBaseUrl());
            return RetrofitHelper
                    .getInstance()
                    .getResponse(query)
                    .flatMap((Function<String, Publisher<BookContentBean>>) s -> analyzeContent(s, sourceRule, chapter))
                    .compose(RxHelper.handleResult())
                    .compose(RxSchedulers.io_main())
                    .flatMap((Function<BookContentBean, Publisher<BookContentBean>>) this::saveContent);
        } catch (Exception e) {
            return Flowable.error(e);
        }
    }


    /**
     * 解析章节内容
     *
     * @param body
     * @param sourceRule
     * @param chapter
     * @return
     */
    private Flowable<BookContentBean> analyzeContent(String body, ChapterRule sourceRule, Chapter chapter) {
        return Flowable.create(emitter -> {
            BookContentBean bookContentBean = new BookContentBean();
            AnalyzeRule analyzeRule = new AnalyzeRule(null);
            analyzeRule.setContent(body, sourceRule.getRuleChapterUrl());
            try {
                bookContentBean.setDurChapterContent(StringUtils.formatHtml(analyzeRule.getString(sourceRule.getRuleContentUrl())));
                bookContentBean.setDurChapterIndex(chapter.getIndex());
                bookContentBean.setDurChapterUrl(chapter.getChapterUrl());
                bookContentBean.setTag(sourceRule.getBaseUrl());
                if (!TextUtils.isEmpty(sourceRule.getRuleContentUrlNext())) {//解析章节内容下一页的url
                    bookContentBean.setNextContentUrl(analyzeRule.getString(sourceRule.getRuleContentUrlNext(), true));
                }
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
            if (!TextUtils.isEmpty(bookContentBean.getNextContentUrl())) {//获取下一页内容
                List<String> usedUrlList = new ArrayList<>();
                usedUrlList.add(chapter.getChapterUrl());
                while (!TextUtils.isEmpty(bookContentBean.getNextContentUrl()) && !usedUrlList.contains(bookContentBean.getNextContentUrl())) {
                    usedUrlList.add(bookContentBean.getNextContentUrl());
                    try {
                        Query query = new Query(bookContentBean.getNextContentUrl(), null, StringUtils.getAbsoluteURL(sourceRule.getBaseUrl(), chapter.getChapterUrl()));
                        String response = RetrofitHelper.getInstance().getResponse(query).blockingFirst();
                        analyzeRule.setContent(response, StringUtils.getAbsoluteURL(sourceRule.getBaseUrl(),chapter.getChapterUrl()));
                        bookContentBean.setDurChapterContent(bookContentBean.getDurChapterContent()+ "\n" +StringUtils.formatHtml(analyzeRule.getString(sourceRule.getRuleContentUrl())));
                        if(!TextUtils.isEmpty(sourceRule.getRuleContentUrlNext())){
                            bookContentBean.setNextContentUrl(analyzeRule.getString(sourceRule.getRuleContentUrlNext(),true));
                        }
                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                }
            }
            emitter.onNext(bookContentBean);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }

    /**
     * 缓存章节内容
     *
     * @param bean
     * @return
     */
    private Flowable<BookContentBean> saveContent(BookContentBean bean) {
        return Flowable.create(emitter -> {
            RealmHelper.getInstance().getRealm().executeTransaction(realm -> {
                BookContentBean bookContentBean = realm.createObject(BookContentBean.class);
                bookContentBean.setDurChapterUrl(bean.getDurChapterUrl());
                bookContentBean.setDurChapterIndex(bean.getDurChapterIndex());
                bookContentBean.setDurChapterContent(bean.getDurChapterContent());
                bookContentBean.setTag(bean.getTag());
            });
            emitter.onNext(bean);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }


}