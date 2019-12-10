package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.realm.BookRealm;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public interface ReadBookContract {
    interface Model extends BaseModel {
        Flowable<List<Chapter>> getChapterList(BookSourceRule rule, String path);

    }

    interface View extends BaseView {
        void returnResult(List<Chapter> list);

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getChapterList(BookSourceRule rule, String path);

    }
}
