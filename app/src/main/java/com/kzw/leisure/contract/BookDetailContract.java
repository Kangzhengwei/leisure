package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.BookBean;
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.bean.SearchBookBean;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public interface BookDetailContract {
    interface Model extends BaseModel {
        Flowable<BookBean> getBook(Query query, BookSourceRule sourceRule, SearchBookBean searchBookBean);
    }

    interface View extends BaseView {
        void returnResult(BookBean list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getBook(Query query, BookSourceRule sourceRule, SearchBookBean searchBookBean);
    }
}
