package com.kzw.leisure.ui.activity;


import android.os.Bundle;
import android.widget.ImageView;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.SeriesAdapter;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.VideoBean;
import com.kzw.leisure.contract.VideoSeriesContract;
import com.kzw.leisure.event.VideoCollectEvent;
import com.kzw.leisure.model.VideoSeriesModel;
import com.kzw.leisure.presenter.VideoSeriesPresenter;
import com.kzw.leisure.realm.VideoRealm;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.widgets.VideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class VideoPlayActivity extends BaseActivity<VideoSeriesPresenter, VideoSeriesModel> implements VideoSeriesContract.View {


    @BindView(R.id.video_player)
    VideoPlayer videoPlayer;
    @BindView(R.id.video_sub)
    ImageView videoSub;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SearchItem item;
    public SeriesAdapter adapter;
    private Realm realm;
    private boolean isSubsrribe = false;
    private VideoBean videoBean;

    @Override
    protected int getContentView() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        GSYVideoType.enableMediaCodec();
        GSYVideoType.enableMediaCodecTexture();
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        videoPlayer.getFullscreenButton().setOnClickListener(v -> videoPlayer.startWindowFullscreen(VideoPlayActivity.this, false, true)
        );
        videoPlayer.getBackButton().setOnClickListener(v -> onBackPressed());
        videoPlayer.setReleaseWhenLossAudio(false);
        videoPlayer.setAutoFullWithSize(true);
        videoPlayer.setNeedLockFull(true);
        videoPlayer.setNeedShowWifiTip(false);
        videoPlayer.setDismissControlTime(5000);
        videoPlayer.setEnlargeImageRes(R.mipmap.icon_fullscreen);
        videoPlayer.setShrinkImageRes(R.mipmap.icon_fullscreen);
        videoPlayer.setSeekRatio(2f);
        //全屏动画
        videoPlayer.setShowFullAnimation(true);
        ////////////////////////////////////
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new SeriesAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(item -> {
            videoPlayer.setUp(item.getVideoUrl(), true, item.getVideoSeries());
            videoPlayer.startPlayLogic();
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        item = (SearchItem) getIntent().getSerializableExtra("Item");
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void initData() {
        if (item != null) {
            mPresenter.getHtml(item);
        }

        RealmResults<VideoRealm> list = realm.where(VideoRealm.class).findAll();
        if (list != null && list.size() > 0) {
            for (VideoRealm video : list) {
                if (video.getName().equals(item.getName())) {
                    isSubsrribe = true;
                    videoSub.setImageResource(R.mipmap.icon_has_subscribe);
                    break;
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        GSYVideoManager.instance().clearAllDefaultCache(this);
    }

    @Override
    public void onBackPressed() {
        //释放所有
        if (videoPlayer.isIfCurrentIsFullscreen()) {
            videoPlayer.onBackFullscreen();
            return;
        }
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    @OnClick(R.id.video_sub)
    public void onViewClicked() {
        if (item == null || videoBean == null) {
            return;
        }
        if (isSubsrribe) {
            RealmResults<VideoRealm> list = realm.where(VideoRealm.class).findAll();
            for (VideoRealm video : list) {
                if (video.getName().equals(item.getName())) {
                    videoSub.setImageResource(R.mipmap.icon_item_describe);
                    realm.executeTransaction(realm -> video.deleteFromRealm());
                    RxBus.getInstance().post(new VideoCollectEvent());
                    break;
                }
            }
            isSubsrribe = false;
            return;
        }
        realm.executeTransaction(realm -> {
            RealmResults<VideoRealm> list = realm.where(VideoRealm.class).findAll();
            boolean isHas = false;
            if (list != null && list.size() > 0) {
                for (VideoRealm video : list) {
                    if (video.getName().equals(item.getName())) {
                        showToast("该视频已收藏");
                        isHas = true;
                        break;
                    }
                }
            }
            if (!isHas) {
                VideoRealm videoRealm = realm.createObject(VideoRealm.class);
                videoRealm.setName(item.getName());
                videoRealm.setUrl(item.getUrl());
                videoRealm.setVideoImage(videoBean.getVideoImage());
                videoRealm.setRuleItem(item.getRuleItem());
                videoRealm.setRuleSeriesList(item.getRuleSeriesList());
                videoRealm.setRulePlayType(item.getRulePlayType());
                videoRealm.setRuleSeriesName(item.getRuleSeriesName());
                videoRealm.setRuleSeriesNoteUrl(item.getRuleSeriesNoteUrl());
                videoRealm.setRuleTypeList(item.getRuleTypeList());
                videoRealm.setRuleVideoImage(item.getRuleVideoImage());
                videoRealm.setRuleVideoName(item.getRuleVideoName());
                videoRealm.setVideoSourceUrl(item.getVideoSourceUrl());
                videoSub.setImageResource(R.mipmap.icon_has_subscribe);
                RxBus.getInstance().post(new VideoCollectEvent());
            }
        });
    }

    @Override
    public void returnResult(VideoBean bean) {
        videoBean = bean;
        adapter.setNewData(bean.getList());
    }

    @Override
    public void returnFail(String message) {
        showToast(message);
    }
}
