package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.BookBean;
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.BookDetailContract;
import com.kzw.leisure.rxJava.RxSubscriber;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class BookDetailPresenter extends BookDetailContract.Presenter {
    @Override
    public void getHtml(BookSourceRule sourceRule, SearchBookBean searchBookBean) {
        mRxManage.addSubscribe(mModel.getHtml(sourceRule, searchBookBean).subscribeWith(new RxSubscriber<BookBean>() {
            @Override
            protected void _onNext(BookBean bookBean) {
                mView.returnResult(bookBean);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }
}
