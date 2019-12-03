package com.kzw.leisure.ui.activity;


import android.os.Bundle;
import android.widget.ImageView;

import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.SeriesBean;
import com.kzw.leisure.contract.VideoSeriesContract;
import com.kzw.leisure.model.VideoSeriesModel;
import com.kzw.leisure.presenter.VideoSeriesPresenter;
import com.kzw.leisure.widgets.VideoPlayer;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class VideoPlayActivity extends BaseActivity<VideoSeriesPresenter, VideoSeriesModel> implements VideoSeriesContract.View {


    @BindView(R.id.video_player)
    VideoPlayer videoPlayer;
    @BindView(R.id.video_sub)
    ImageView videoSub;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SearchItem item;

    @Override
    protected int getContentView() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        item = (SearchItem) getIntent().getSerializableExtra("Item");
    }

    @Override
    public void initData() {
        if (item != null) {
            mPresenter.getHtml(item);
        }
    }


    @OnClick(R.id.video_sub)
    public void onViewClicked() {
    }

    @Override
    public void returnResult(List<SeriesBean> list) {

    }

    @Override
    public void returnFail(String message) {

    }
}
