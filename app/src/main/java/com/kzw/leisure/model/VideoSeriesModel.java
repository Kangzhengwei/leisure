package com.kzw.leisure.model;

import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.SeriesBean;
import com.kzw.leisure.contract.VideoSeriesContract;
import com.kzw.leisure.network.RetrofitHelper;
import com.kzw.leisure.rxJava.RxHelper;
import com.kzw.leisure.rxJava.RxSchedulers;
import com.kzw.leisure.utils.AnalyzeRule;
import com.kzw.leisure.utils.LogUtils;
import com.kzw.leisure.utils.StringUtils;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * author: kang4
 * Date: 2019/12/2
 * Description:
 */
public class VideoSeriesModel implements VideoSeriesContract.Model {
    @Override
    public Flowable<List<SeriesBean>> getHtml(SearchItem item) {
        return RetrofitHelper
                .getInstance()
                .getResponse(item.getVideoSourceUrl(), item.getUrl())
                .flatMap(new Function<String, Publisher<List<SeriesBean>>>() {
                    @Override
                    public Publisher<List<SeriesBean>> apply(String s) throws Exception {
                        analyze(s, item);
                        return null;
                    }
                })
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }

    private void analyze(String body, SearchItem item) {
        AnalyzeRule analyzer = new AnalyzeRule(null);
        analyzer.setContent(body, item.getUrl());
        try {
            List<Object> collections = analyzer.getElements(item.getRuleSeriesList());
            List<Object> videoTypelist = analyzer.getElements(item.getRuleTypeList());
            for (int i = 0; i < collections.size(); i++) {
                SeriesBean bean = new SeriesBean();
                analyzer.setContent(videoTypelist.get(i));
                bean.setUrlType(analyzer.getString(item.getRulePlayType()));
                Object o = collections.get(i);
                analyzer.setContent(o);
                List<Object> list = analyzer.getElements(item.getRuleItem());
                List<SeriesBean.Url> urList = new ArrayList();
                LogUtils.e(bean.getUrlType());
                for (Object ob : list) {
                    SeriesBean.Url urlBean = new SeriesBean.Url();
                    analyzer.setContent(ob, item.getUrl());
                    urlBean.setVideoSeries(StringUtils.subString(analyzer.getString(item.getRuleSeriesName()), 0));
                    urlBean.setVideoUrl(StringUtils.subString(analyzer.getString(item.getRuleSeriesName()), 1));
                    urList.add(urlBean);
                    LogUtils.e(urlBean.getVideoSeries() + "/" + urlBean.getVideoUrl());
                }
                bean.setList(urList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
