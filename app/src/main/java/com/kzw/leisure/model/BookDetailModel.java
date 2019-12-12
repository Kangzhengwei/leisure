package com.kzw.leisure.model;

import com.kzw.leisure.bean.BookBean;
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.BookDetailContract;
import com.kzw.leisure.network.RetrofitHelper;
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
 * Date: 2019/12/5
 * Description:
 */
public class BookDetailModel implements BookDetailContract.Model {
    @Override
    public Flowable<BookBean> getBook(Query query, BookSourceRule sourceRule, SearchBookBean searchBookBean) {
        return RetrofitHelper
                .getInstance()
                .getResponse(query)
                .flatMap((Function<String, Publisher<BookBean>>) s -> analyze(s, sourceRule, searchBookBean))
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }

    private Flowable<BookBean> analyze(String body, BookSourceRule sourceRule, SearchBookBean searchBookBean) {
        return Flowable.create(emitter -> {
            BookBean book = new BookBean();
            AnalyzeRule analyzer = new AnalyzeRule(null);
            analyzer.setContent(body, sourceRule.getRuleSearchUrl());
            try {
                book.setBookAuthor(analyzer.getString(sourceRule.getRuleBookAuthor()));
                book.setBookContent(analyzer.getString(sourceRule.getRuleBookContent()));
                book.setBookInfoInit(analyzer.getString(sourceRule.getRuleBookInfoInit()));
                book.setBookKind(analyzer.getString(sourceRule.getRuleBookKind()));
                book.setBookLastChapter(analyzer.getString(sourceRule.getRuleBookLastChapter()));
                book.setBookName(analyzer.getString(sourceRule.getRuleBookName()));
                book.setBookUrlPattern(analyzer.getString(sourceRule.getRuleBookUrlPattern()));
                book.setCoverUrl(analyzer.getString(sourceRule.getRuleCoverUrl()));
                List<Object> collections = analyzer.getElements(sourceRule.getRuleChapterList());
                List<Chapter> chapterList = new ArrayList<>();
                for (Object object : collections) {
                    analyzer.setContent(object);
                    Chapter chapter = new Chapter();
                    chapter.setChapterName(analyzer.getString(sourceRule.getRuleChapterName()));
                    chapter.setChapterUrl(analyzer.getString(sourceRule.getRuleChapterUrl()));
                    chapterList.add(chapter);
                }
                book.setList(chapterList);
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
            emitter.onNext(book);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }
}
