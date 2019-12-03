package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.QuerySearchVideoBean;
import com.kzw.leisure.bean.SearchItem;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public interface SearchVideoContract {

    interface Model extends BaseModel {
        Flowable<List<SearchItem>> getHtml(QuerySearchVideoBean bean);
    }

    interface View extends BaseView {
        void returnResult(List<SearchItem> list );
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHtml(QuerySearchVideoBean bean);
    }

}
