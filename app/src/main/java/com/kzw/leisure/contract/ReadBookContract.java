package com.kzw.leisure.contract;

import com.kzw.leisure.base.BaseModel;
import com.kzw.leisure.base.BasePresenter;
import com.kzw.leisure.base.BaseView;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.ChapterRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.realm.ChapterList;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public interface ReadBookContract {
    interface Model extends BaseModel {
        Flowable<List<Chapter>> getChapterList(Query query, ChapterRule rule, ChapterList chapterList,boolean isFromNet );

    }

    interface View extends BaseView {
        void returnResult(List<Chapter> list, int position);

        void returnFail(String message,int position);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getChapterList(Query query, ChapterRule rule,ChapterList chapterList,boolean isFromNet,int position);

    }
}
