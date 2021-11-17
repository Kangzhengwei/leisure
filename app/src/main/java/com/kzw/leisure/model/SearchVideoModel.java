package com.kzw.leisure.model;

import com.google.gson.reflect.TypeToken;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.bean.QuerySearchVideoBean;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.VideoSearchBean;
import com.kzw.leisure.contract.SearchVideoContract;
import com.kzw.leisure.network.RetrofitHelper;
import com.kzw.leisure.rxJava.RxHelper;
import com.kzw.leisure.rxJava.RxSchedulers;
import com.kzw.leisure.constant.Constant;
import com.kzw.leisure.utils.GsonUtil;
import com.kzw.leisure.utils.StringUtils;
import com.kzw.leisure.utils.analyze.AnalyzeRule;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public class SearchVideoModel implements SearchVideoContract.Model {
    @Override
    public Flowable<List<SearchItem>> getHtml(Query query, QuerySearchVideoBean bean) {
        return RetrofitHelper
                .getInstance()
                .getResponse(query)
                .flatMap((Function<String, Publisher<List<SearchItem>>>) s -> alalyze(s, bean))
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());

    }

    @Override
    public Flowable<List<SearchItem>> getList(String url) {
        return RetrofitHelper
                .getInstance()
                .getResponse(Constant.QUERY_BASE,url)
                .map(this::mega)
                .compose(RxHelper.handleResult())
                .compose(RxSchedulers.io_main());
    }

    private List<SearchItem> mega(String s){
        List<VideoSearchBean> strings=  GsonUtil.getInstance().fromJson(s, new TypeToken<List<VideoSearchBean>>() {
        }.getType());
        List<SearchItem> list=new ArrayList<>();
        for(VideoSearchBean str:strings){
            SearchItem item=new SearchItem();
            item.setSiteName("解析");
            item.setName(str.getName());
            item.setType(0);
            list.add(item);
        }
        return list;
    }

    private Flowable<List<SearchItem>> alalyze(String body, QuerySearchVideoBean bean) {
        return Flowable.create(emitter -> {
            List<SearchItem> listString = new ArrayList<>();
            AnalyzeRule analyzer = new AnalyzeRule(null);
            analyzer.setContent(body, bean.getRuleSearchUrl());
            try {
                List<Object> collections = analyzer.getElements(bean.getRuleSearchList());
                for (int i = 0; i < collections.size(); i++) {
                    Object object = collections.get(i);
                    analyzer.setContent(object, bean.getRuleSearchUrl());//<span class="xing_vb4"><a href="/?m=vod-detail-id-37236.html" target="_blank">斗破苍穹第三季完结</a></span>
                    SearchItem item = new SearchItem();
                    item.setName(StringUtils.formatHtml(analyzer.getString(bean.getRuleSearchName())));
                    item.setUrl(StringUtils.formatHtml(analyzer.getString(bean.getRuleSearchNoteUrl())));
                    item.setSiteName(bean.getSiteName());
                    item.setVideoSourceUrl(bean.getVideoSourceUrl());
                    item.setRuleSeriesList(bean.getRuleSeriesList());
                    item.setRuleSeriesName(bean.getRuleSeriesName());
                    item.setRuleSeriesNoteUrl(bean.getRuleSeriesNoteUrl());
                    item.setRulePlayType(bean.getRulePlayType());
                    item.setRuleItem(bean.getRuleItem());
                    item.setRuleTypeList(bean.getRuleTypeList());
                    item.setRuleVideoImage(bean.getRuleVideoImage());
                    item.setRuleVideoName(bean.getRuleVideoName());
                    item.setSourceType(bean.getSourceType());
                    item.setType(bean.getType());
                    listString.add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            emitter.onNext(listString);
            emitter.onComplete();
        }, BackpressureStrategy.ERROR);

    }
}
