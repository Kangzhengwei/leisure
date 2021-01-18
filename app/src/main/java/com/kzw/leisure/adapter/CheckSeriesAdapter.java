package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.VideoBean;

/**
 * author: kang4
 * Date: 2021/1/18
 * Description:
 */
public class CheckSeriesAdapter extends BaseQuickAdapter<VideoBean.Series.Url, BaseViewHolder> {
    public CheckSeriesAdapter() {
        super(R.layout.check_series_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean.Series.Url item) {
        helper.setText(R.id.tv_series, item.getVideoSeries());
    }
}