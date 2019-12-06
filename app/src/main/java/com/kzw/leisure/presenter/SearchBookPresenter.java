package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.SearchBookContract;
import com.kzw.leisure.rxJava.RxSubscriber;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/4
 * Description:
 */
public class SearchBookPresenter extends SearchBookContract.Presenter {
    @Override
    public void getHtml(BookSourceRule bean) {
        mRxManage.addSubscribe(mModel.getHtml(bean).subscribeWith(new RxSubscriber<List<SearchBookBean>>() {

            @Override
            protected void _onNext(List<SearchBookBean> searchBookBeans) {
                mView.returnResult(searchBookBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }
}
