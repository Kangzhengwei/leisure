package com.kzw.leisure.model;

import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.VideoBean;
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

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * author: kang4
 * Date: 2019/12/2
 * Description:
 */
public class VideoSeriesModel implements VideoSeriesContract.Model {
    @Override
    public Flowable<VideoBean> getHtml(SearchItem item) {
        return RetrofitHelper
                .getInstance()
                .getResponse(item.getVideoSourceUrl(), item.getUrl())
                .flatMap((Function<String, Publisher<VideoBean>>) s -> analyze(s, item))
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }

    private Flowable<VideoBean> analyze(String body, SearchItem item) {
        return Flowable.create(emitter -> {
            AnalyzeRule analyzer = new AnalyzeRule(null);
            VideoBean video = new VideoBean();
            try {
                analyzer.setContent(body, item.getUrl());
                video.setVideoImage(analyzer.getString(item.getRuleVideoImage()));
                video.setVideoName(analyzer.getString(item.getRuleVideoName()));
                List<VideoBean.Series> seriesList = new ArrayList<>();
                List<Object> collections = analyzer.getElements(item.getRuleSeriesList());
                List<Object> videoTypelist = analyzer.getElements(item.getRuleTypeList());
                for (int i = 0; i < collections.size(); i++) {
                    VideoBean.Series series = new VideoBean.Series();
                    analyzer.setContent(videoTypelist.get(i));
                    series.setUrlType(analyzer.getString(item.getRulePlayType()));
                    Object o = collections.get(i);
                    analyzer.setContent(o);
                    List<Object> list = analyzer.getElements(item.getRuleItem());
                    List<VideoBean.Series.Url> urList = new ArrayList();
                    LogUtils.e(series.getUrlType());
                    for (Object ob : list) {
                        VideoBean.Series.Url urlBean = new VideoBean.Series.Url();
                        analyzer.setContent(ob, item.getUrl());
                        urlBean.setVideoSeries(StringUtils.subString(analyzer.getString(item.getRuleSeriesName()), 0));
                        urlBean.setVideoUrl(StringUtils.subString(analyzer.getString(item.getRuleSeriesName()), 1));
                        urList.add(urlBean);
                        LogUtils.e(urlBean.getVideoSeries() + "/" + urlBean.getVideoUrl());
                    }
                    series.setList(urList);
                    seriesList.add(series);
                }
                video.setList(seriesList);
            } catch (Exception e) {
                LogUtils.e(e.toString());
                e.printStackTrace();
            }
            emitter.onNext(video);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }
}
