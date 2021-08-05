package com.kzw.leisure.presenter;

import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.VideoBean;
import com.kzw.leisure.contract.VideoSeriesContract;
import com.kzw.leisure.rxJava.RxSubscriber;

/**
 * author: kang4
 * Date: 2019/12/2
 * Description:
 */
public class VideoSeriesPresenter extends VideoSeriesContract.Presenter {
    @Override
    public void getHtml(SearchItem item) {
        mRxManage.addSubscribe(mModel.getHtml(item).subscribeWith(new RxSubscriber<VideoBean>() {

            @Override
            protected void _onNext(VideoBean seriesBeans) {
                mView.returnResult(seriesBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }

    @Override
    public void getVideo(String url) {
        mRxManage.addSubscribe(mModel.getVideo(url).subscribeWith(new RxSubscriber<VideoBean>(){
            @Override
            protected void _onNext(VideoBean videoBean) {
                mView.returnVideo(videoBean);
            }

            @Override
            protected void _onError(String message) {
                mView.returnFail(message);
            }
        }));
    }
}
