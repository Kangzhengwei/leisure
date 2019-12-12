package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.ChapterRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.realm.ChapterList;
import com.kzw.leisure.rxJava.RxSubscriber;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class ReadBookPresenter extends ReadBookContract.Presenter {
    @Override
    public void getChapterList(Query query, ChapterRule rule, ChapterList chapterList,boolean isFromNet) {
        mRxManage.addSubscribe(mModel.getChapterList(query,rule,chapterList,isFromNet).subscribeWith(new RxSubscriber<List<Chapter>>() {
            @Override
            protected void _onNext(List<Chapter> chapters) {
                mView.returnResult(chapters);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }



}
