package com.kzw.leisure.rxJava;


import com.kzw.leisure.network.ApiException;

import io.reactivex.subscribers.ResourceSubscriber;


public abstract class RxSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onComplete() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            _onError(e.toString());
        } else if (e instanceof Exception) {
            _onError(e.toString());
        } else {
            _onError("未知错误ヽ(≧Д≦)ノ");
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
