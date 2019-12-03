package com.kzw.leisure.rxJava;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class RxManager {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();//管理Observables 和 Subscribers订阅

    /**
     * 管理观察者
     *
     * @param disposable
     */
    public void addSubscribe(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消所有观察者
     */
    public void unSubscribe() {
        mCompositeDisposable.dispose();// 取消所有订阅
    }

}
