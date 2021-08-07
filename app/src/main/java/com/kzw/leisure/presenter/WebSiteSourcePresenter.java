package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.ExploreMultiItemBean;
import com.kzw.leisure.contract.WebSiteSourceContract;
import com.kzw.leisure.rxJava.RxSubscriber;

import java.util.List;

public class WebSiteSourcePresenter extends WebSiteSourceContract.Presenter {
    @Override
    public void getSource() {
        mRxManage.addSubscribe(mModel.getSource().subscribeWith(new RxSubscriber<List<ExploreMultiItemBean>>() {
            @Override
            protected void _onNext(List<ExploreMultiItemBean> objects) {
                mView.returnResult(objects);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }
}
