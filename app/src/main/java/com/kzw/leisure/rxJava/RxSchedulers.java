package com.kzw.leisure.rxJava;


import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxSchedulers {
    /**
     * 线程调度器
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> io_main() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
