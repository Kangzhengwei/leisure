package com.kzw.leisure.ui.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.kzw.leisure.realm.VideoWatchRealm;
import com.kzw.leisure.realm.VideoWatchTypeRealm;
import com.kzw.leisure.realm.VideoWatchTypeSeriesRealm;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.utils.AdMobUtils;
import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.LogUtils;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.SPUtils;
import com.kzw.leisure.utils.StatusBarUtil;
import com.kzw.leisure.widgets.ToastUtil;
import com.kzw.leisure.widgets.VideoPlayer;
import com.kzw.leisure.widgets.dialog.AwardAdTipDialog;
import com.kzw.leisure.widgets.popwindow.CheckSeriesPopWindow;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
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
    private OrientationUtils orientationUtils;
    private CheckSeriesPopWindow seriesPopWindow;
    private VideoWatchRealm videoWatchRealm;
    private String videoUrlType;

    @Override
    protected int getContentView() {
        return R.layout.activity_video_play;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //GSYVideoType.enableMediaCodec();//打开硬解码会导致横竖屏切换黑屏
        GSYVideoType.enableMediaCodecTexture();
        StatusBarUtil.translucentBar((Activity) mContext);
        //是否可以滑动调整
        seriesPopWindow = new CheckSeriesPopWindow(this);
        seriesPopWindow.setItemClickListener((item, position) -> {
            videoPlayer.getFullWindowPlayer().setUp(item.getVideoUrl(), true, item.getVideoSeries());
            videoPlayer.getFullWindowPlayer().startPlayLogic();
            updateData(item, videoUrlType, position);
        });
        orientationUtils = new OrientationUtils(VideoPlayActivity.this, videoPlayer);
        orientationUtils.setEnable(false);
        orientationUtils.setIsLand((videoPlayer.getCurrentPlayer().isIfCurrentIsFullscreen()) ? 1 : 0);

        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption.setVideoAllCallBack(new VideoAllCallBack() {


            @Override
            public void onStartPrepared(String url, Object... objects) {

            }

            @Override
            public void onPrepared(String url, Object... objects) {

            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {

            }

            @Override
            public void onComplete(String url, Object... objects) {

            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {
                videoPlayer.getCheckSeries().setVisibility(View.VISIBLE);
                orientationUtils.resolveByClick();
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                videoPlayer.getCheckSeries().setVisibility(View.GONE);
                orientationUtils.resolveByClick();
            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {

            }

            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }


        }).build(videoPlayer);

        videoPlayer.getFullscreenButton().setOnClickListener(v -> fullScreen());
        videoPlayer.setIsTouchWiget(true);
        videoPlayer.setKeepScreenOn(true);
        videoPlayer.getBackButton().setOnClickListener(v -> onBackPressed());
        videoPlayer.setReleaseWhenLossAudio(false);
        videoPlayer.setAutoFullWithSize(false);
        videoPlayer.setNeedLockFull(true);
        videoPlayer.setNeedShowWifiTip(false);
        videoPlayer.setDismissControlTime(5000);
        videoPlayer.setEnlargeImageRes(R.mipmap.icon_fullscreen);
        videoPlayer.setShrinkImageRes(R.mipmap.icon_fullscreen);
        videoPlayer.setSeekRatio(2f);
        videoPlayer.getCheckSeries().setVisibility(View.GONE);
        videoPlayer.setShowFullAnimation(false);//全屏动画
        videoPlayer.setCheckSeriesClickListener(() -> seriesPopWindow.showAsDropDown(videoPlayer.getCheckSeries(), 0, 50));
        ////////////////////////////////////
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new SeriesAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener((item, list, urlType, position) -> {
            LogUtils.d(item.getVideoUrl());
            if (this.item != null && this.item.getType() == 2) {
                IntentUtils.intentToBrowserActivity(mContext, Constant.LE_DUO_API + item.getVideoUrl(), 0);
            } else if (this.item != null && this.item.getType() == 3) {
                IntentUtils.intentToBrowserActivity(mContext, item.getVideoUrl(), 1);
            } else {
                videoPlayer.setUp(item.getVideoUrl(), true, item.getVideoSeries());
                videoPlayer.startPlayLogic();
                seriesPopWindow.setData(list);
                videoUrlType = urlType;
                updateData(item, urlType, position);
            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        item = (SearchItem) getIntent().getSerializableExtra("Item");
        realm = RealmHelper.getInstance().getRealm();
    }

    @Override
    public void initData() {
        if (item != null) {
            if (item.getType() == 0) {
                String token = SPUtils.getInstance().getString("token");
                if (!TextUtils.isEmpty(token)) {
                    mPresenter.getVideo(Constant.QUERY_VIDEO.replace("KEYWORD", item.getName()).replace("TOKEN", token));
                } else {
                    showToast("token is empty");
                }
            } else {
                mPresenter.getHtml(item);
            }
        }
        videoWatchRealm = new VideoWatchRealm();//用于记录观看记录
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
        AdMobUtils.getInstance().loadAwardAd(this);
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
        // GSYVideoManager.instance().clearAllDefaultCache(this);
        saveData();
        videoWatchRealm = null;
    }

    @Override
    public void onBackPressed() {
        //释放所有
        if (videoPlayer.isIfCurrentIsFullscreen()) {
            orientationUtils.backToProtVideo();
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
                isSubsrribe = true;
            }
        });
    }

    @Override
    public void returnResult(VideoBean bean) {
        videoBean = bean;
        updateBean(bean);
        adapter.setNewData(bean.getList());
        videoWatchRealm.setVideoName(bean.getVideoName());
        ToastUtil.showCenterLongToast("只能播放m3u8和MP4格式（迅雷下载）");
    }

    @Override
    public void returnVideo(VideoBean bean) {
        videoBean = bean;
        updateBean(bean);
        adapter.setNewData(bean.getList());
        videoWatchRealm.setVideoName(bean.getVideoName());
    }

    @Override
    public void returnFail(String message) {
    }


    private void saveData() {
        if (isSubsrribe && videoWatchRealm.getMlist().size() > 0) {
            List<VideoWatchRealm> list = realm.where(VideoWatchRealm.class).findAll();
            boolean isHas = false;
            if (list != null && list.size() > 0) {
                for (VideoWatchRealm watchRealm : list) {
                    if (watchRealm.getVideoName().equals(videoBean.getVideoName())) {
                        isHas = true;
                        RealmList<VideoWatchTypeRealm> mList = watchRealm.getMlist();
                        RealmList<VideoWatchTypeRealm> sList = videoWatchRealm.getMlist();
                        boolean isHasType = false;
                        for (VideoWatchTypeRealm type : sList) {
                            for (VideoWatchTypeRealm watchType : mList) {
                                if (type.getUrlType().equals(watchType.getUrlType())) {
                                    isHasType = true;
                                    realm.executeTransaction(realm -> {
                                        watchType.getmList().addAll(type.getmList());
                                        HashSet<VideoWatchTypeSeriesRealm> set = new HashSet<>(watchType.getmList());
                                        watchType.getmList().clear();
                                        watchType.getmList().addAll(set);
                                    });
                                    break;
                                }
                            }
                        }
                        if (!isHasType) {
                            realm.executeTransaction(realm -> {
                                mList.addAll(sList);
                            });
                        }
                    }
                }
            }
            if (!isHas) {
                realm.executeTransaction(realm -> {
                    VideoWatchRealm watchRealm = realm.createObject(VideoWatchRealm.class);
                    watchRealm.setVideoName(videoWatchRealm.getVideoName());
                    watchRealm.getMlist().addAll(videoWatchRealm.getMlist());
                });
            }
        }
    }


    private void updateBean(VideoBean bean) {
        List<VideoWatchRealm> list = realm.where(VideoWatchRealm.class).findAll();
        if (list != null && list.size() > 0) {
            for (VideoWatchRealm videoWatchRealm : list) {
                if (videoWatchRealm.getVideoName().equals(bean.getVideoName())) {
                    RealmList<VideoWatchTypeRealm> mList = videoWatchRealm.getMlist();
                    List<VideoBean.Series> seriesList = bean.getList();
                    for (VideoWatchTypeRealm videoWatchTypeRealm : mList) {
                        for (VideoBean.Series series : seriesList) {
                            if (videoWatchTypeRealm.getUrlType().equals(series.getUrlType())) {
                                RealmList<VideoWatchTypeSeriesRealm> smList = videoWatchTypeRealm.getmList();
                                List<VideoBean.Series.Url> urlList = series.getList();
                                for (VideoWatchTypeSeriesRealm videoWatchTypeSeriesRealm : smList) {
                                    for (VideoBean.Series.Url url : urlList) {
                                        if (videoWatchTypeSeriesRealm.getSeriesName().equals(url.getVideoSeries())) {
                                            series.setPostion(videoWatchTypeSeriesRealm.getSeriesNum());
                                            url.setWatched(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private void updateData(VideoBean.Series.Url item, String urlType, int position) {
        if (isSubsrribe) {
            boolean isHas = false;
            for (VideoWatchTypeRealm realm : videoWatchRealm.getMlist()) {
                if (realm.getUrlType().equals(urlType)) {
                    isHas = true;
                    VideoWatchTypeSeriesRealm seriesRealm = new VideoWatchTypeSeriesRealm(item.getVideoSeries(), position);
                    realm.getmList().add(seriesRealm);
                    break;
                }
            }
            if (!isHas) {
                VideoWatchTypeRealm videoWatchTypeRealm = new VideoWatchTypeRealm(urlType);
                VideoWatchTypeSeriesRealm seriesRealm = new VideoWatchTypeSeriesRealm(item.getVideoSeries(), position);
                videoWatchTypeRealm.getmList().add(seriesRealm);
                videoWatchRealm.getMlist().add(videoWatchTypeRealm);
            }
        }
    }

    private void fullScreen() {
        if (!videoPlayer.isIfCurrentIsFullscreen()) {
            showDialog();
        } else {
            videoPlayer.startWindowFullscreen(mContext, false, true);
        }
    }

    private void showDialog() {
        AwardAdTipDialog.builder(this)
                .setMessage(getString(R.string.tip_fullscreen_award))
                .setPositiveClickListener(() -> AdMobUtils.getInstance().showAd(this, () -> videoPlayer.startWindowFullscreen(mContext, false, true)))
                .show();
    }
}
