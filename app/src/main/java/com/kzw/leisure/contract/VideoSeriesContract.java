package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.SeriesBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/12/2
 * Description:
 */
public interface VideoSeriesContract {
    interface Model extends BaseModel {
        Flowable<List<SeriesBean>> getHtml(SearchItem item);
    }

    interface View extends BaseView {
        void returnResult(List<SeriesBean> strings );
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHtml(SearchItem item);
    }

}
