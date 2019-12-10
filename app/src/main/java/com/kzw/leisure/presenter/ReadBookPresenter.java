package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.rxJava.RxSubscriber;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class ReadBookPresenter extends ReadBookContract.Presenter {
    @Override
    public void getChapterList(BookSourceRule rule,String path) {
        mRxManage.addSubscribe(mModel.getChapterList(rule, path).subscribeWith(new RxSubscriber<List<Chapter>>() {
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
