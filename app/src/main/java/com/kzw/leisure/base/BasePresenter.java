package com.kzw.leisure.base;

import android.content.Context;

import com.kzw.leisure.rxJava.RxManager;


public abstract class BasePresenter<T, E> {

    public E mModel;
    public Context mContext;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }

    public void onDestroy() {
        mRxManage.unSubscribe();
    }
}
