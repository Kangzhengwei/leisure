package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.Query;
import com.kzw.leisure.bean.QuerySearchVideoBean;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.contract.SearchVideoContract;
import com.kzw.leisure.rxJava.RxSubscriber;

import java.util.List;


/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public class SearchVideoPresenter extends SearchVideoContract.Presenter {
    @Override
    public void getHtml(Query query, QuerySearchVideoBean bean) {
        mRxManage.addSubscribe(mModel.getHtml(query, bean).subscribeWith(new RxSubscriber<List<SearchItem>>() {


            @Override
            protected void _onNext(List<SearchItem> items) {
                mView.returnResult(items);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }
}
