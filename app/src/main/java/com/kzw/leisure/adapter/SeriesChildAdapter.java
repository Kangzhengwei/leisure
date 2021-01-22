package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.VideoBean;

/**
 * author: kang4
 * Date: 2019/12/3
 * Description:
 */
public class SeriesChildAdapter extends BaseQuickAdapter<VideoBean.Series.Url, BaseViewHolder> {
    public SeriesChildAdapter() {
        super(R.layout.series_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean.Series.Url item) {
        helper.setText(R.id.series, item.getVideoSeries());
        helper.setVisible(R.id.image_tag, item.isWatched());
    }
}
