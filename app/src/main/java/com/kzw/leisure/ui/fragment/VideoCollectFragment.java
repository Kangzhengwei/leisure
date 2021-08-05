package com.kzw.leisure.ui.fragment;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.VideoCollectAdapter;
import com.kzw.leisure.base.BaseFragment;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.event.VideoCollectEvent;
import com.kzw.leisure.realm.VideoRealm;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

/**
 * author: kang4
 * Date: 2019/12/3
 * Description:
 */
public class VideoCollectFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Realm realm;
    private VideoCollectAdapter adapter;
    private List<VideoRealm> list = new ArrayList<>();

    public static Fragment newInstance() {
        return new VideoCollectFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect_video;
    }

    @Override
    public void initVariables() {
        realm = RealmHelper.getInstance().getRealm();
        RxBus.getInstance().toObservable(this, VideoCollectEvent.class).subscribe(new Observer<VideoCollectEvent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(VideoCollectEvent videoCollectEvent) {
                initData();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new VideoCollectAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            VideoRealm videoRealm = list.get(position);
            SearchItem item = new SearchItem();
            item.setName(videoRealm.getName());
            item.setUrl(videoRealm.getUrl());
            item.setVideoSourceUrl(videoRealm.getVideoSourceUrl());
            item.setRuleSeriesList(videoRealm.getRuleSeriesList());
            item.setRuleSeriesName(videoRealm.getRuleSeriesName());
            item.setRuleSeriesNoteUrl(videoRealm.getRuleSeriesNoteUrl());
            item.setRuleItem(videoRealm.getRuleItem());
            item.setRuleVideoName(videoRealm.getRuleVideoName());
            item.setRuleVideoImage(videoRealm.getRuleVideoImage());
            item.setRuleTypeList(videoRealm.getRuleTypeList());
            item.setRulePlayType(videoRealm.getRulePlayType());
            IntentUtils.intentToVideoPlay(mContext, item);
        });
    }

    @Override
    protected void initData() {
        list = realm.where(VideoRealm.class).findAll();
        adapter.setNewData(list);
    }

}
