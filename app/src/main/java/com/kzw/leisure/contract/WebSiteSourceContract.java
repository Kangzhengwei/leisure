package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.ExploreMultiItemBean;

import java.util.List;

import io.reactivex.Flowable;

public interface WebSiteSourceContract {
    interface Model extends BaseModel {
        Flowable<List<ExploreMultiItemBean>> getSource();
    }

    interface View extends BaseView {
        void returnResult(List<ExploreMultiItemBean> bean);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getSource();
    }
}
