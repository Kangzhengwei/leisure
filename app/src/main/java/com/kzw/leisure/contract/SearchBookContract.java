package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.bean.BookSourceRule;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/12/4
 * Description:
 */
public interface SearchBookContract {
    interface Model extends BaseModel {
        Flowable<List<SearchBookBean>> getHtml(BookSourceRule bean);
    }

    interface View extends BaseView {
        void returnResult(List<SearchBookBean> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHtml(BookSourceRule bean);
    }
}
