package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.VideoBean;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/12/2
 * Description:
 */
public interface VideoSeriesContract {
    interface Model extends BaseModel {
        Flowable<VideoBean> getHtml(SearchItem item);
    }

    interface View extends BaseView {
        void returnResult(VideoBean bean );
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHtml(SearchItem item);
    }

}
