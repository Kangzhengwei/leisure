package com.kzw.leisure.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.kzw.leisure.bean.ExploreMultiItemBean;
import com.kzw.leisure.bean.SiteSourceBean;
import com.kzw.leisure.contract.WebSiteSourceContract;
import com.kzw.leisure.network.RetrofitHelper;
import com.kzw.leisure.rxJava.RxHelper;
import com.kzw.leisure.rxJava.RxSchedulers;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class WebSiteSourceModel implements WebSiteSourceContract.Model {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Flowable<List<ExploreMultiItemBean>> getSource() {
        return RetrofitHelper.getInstance()
                .getSiteSource()
                .flatMap((Function<List<SiteSourceBean>, Publisher<List<ExploreMultiItemBean>>>) r -> {
                    List<ExploreMultiItemBean> list = new ArrayList<>();
                    r.forEach(siteSourceBean -> {
                        list.add(new ExploreMultiItemBean().setItemType(ExploreMultiItemBean.TYPE_TAG).setTagName(siteSourceBean.getListName()));
                        siteSourceBean.getList().forEach(listDTO -> list.add(new ExploreMultiItemBean().setItemType(ExploreMultiItemBean.TYPE_ITEM).setBean(listDTO)));
                    });
                    return Flowable.just(list);
                }).compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }
}
