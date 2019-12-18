package com.kzw.leisure.model;

import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.SearchBookContract;
import com.kzw.leisure.network.RetrofitHelper;
import com.kzw.leisure.rxJava.RxHelper;
import com.kzw.leisure.rxJava.RxSchedulers;
import com.kzw.leisure.utils.analyze.AnalyzeRule;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * author: kang4
 * Date: 2019/12/4
 * Description:
 */
public class SearchBookModel implements SearchBookContract.Model {
    @Override
    public Flowable<List<SearchBookBean>> searchBook(Query query, BookSourceRule bean) {
        return RetrofitHelper
                .getInstance()
                .getResponse(query)
                .flatMap((Function<String, Publisher<List<SearchBookBean>>>) s -> analyze(s, bean))
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }

    private Flowable<List<SearchBookBean>> analyze(String body, BookSourceRule bean) {
        return Flowable.create(emitter -> {
            List<SearchBookBean> list = new ArrayList<>();
            AnalyzeRule analyzer = new AnalyzeRule(null);
            analyzer.setContent(body, bean.getRuleSearchUrl());
            try {
                List<Object> collections = analyzer.getElements(bean.getRuleSearchList());
                for (Object o : collections) {
                    analyzer.setContent(o);
                    SearchBookBean searchBookBean = new SearchBookBean();
                    searchBookBean.setSearchAuthor(analyzer.getString(bean.getRuleSearchAuthor()));
                    searchBookBean.setSearchCoverUrl(analyzer.getString(bean.getRuleSearchCoverUrl()));
                    searchBookBean.setSearchIntroduce(analyzer.getString(bean.getRuleSearchIntroduce()));
                    searchBookBean.setSearchKind(analyzer.getString(bean.getRuleSearchKind()));
                    searchBookBean.setSearchLastChapter(analyzer.getString(bean.getRuleSearchLastChapter()));
                    searchBookBean.setSearchName(analyzer.getString(bean.getRuleSearchName()));
                    searchBookBean.setSearchNoteUrl(analyzer.getString(bean.getRuleSearchNoteUrl()));
                    searchBookBean.setCurrentSearchRule(bean);
                    searchBookBean.getSearchNoteUrlList().add(analyzer.getString(bean.getRuleSearchNoteUrl()));
                    searchBookBean.getSearchRuleList().add(bean);
                    list.add(searchBookBean);

                }
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
            emitter.onNext(list);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }
}
