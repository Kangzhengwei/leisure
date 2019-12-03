package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.SeriesBean;
import com.kzw.leisure.contract.VideoSeriesContract;
import com.kzw.leisure.rxJava.RxSubscriber;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/2
 * Description:
 */
public class VideoSeriesPresenter extends VideoSeriesContract.Presenter {
    @Override
    public void getHtml(SearchItem item) {
        mRxManage.addSubscribe(mModel.getHtml(item).subscribeWith(new RxSubscriber<List<SeriesBean>>() {

            @Override
            protected void _onNext(List<SeriesBean> seriesBeans) {

            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
