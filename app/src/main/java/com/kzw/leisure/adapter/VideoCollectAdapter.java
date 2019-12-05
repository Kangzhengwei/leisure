package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.realm.VideoRealm;
import com.kzw.leisure.utils.GlideUtil;

/**
 * author: kang4
 * Date: 2019/12/3
 * Description:
 */
public class VideoCollectAdapter extends BaseQuickAdapter<VideoRealm, BaseViewHolder> {
    public VideoCollectAdapter() {
        super(R.layout.grid_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoRealm item) {
        helper.setText(R.id.video_name, item.getName());
        GlideUtil.setVideoImagesource(mContext, item.getVideoImage(), helper.getView(R.id.video_pic));
    }
}
