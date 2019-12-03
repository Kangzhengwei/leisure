package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.SpanUtils;

/**
 * author: kang4
 * Date: 2019/11/29
 * Description:
 */
public class SearchListAdapter extends BaseQuickAdapter<SearchItem, BaseViewHolder> {
    public SearchListAdapter() {
        super(R.layout.search_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchItem item) {
        helper.setText(R.id.video_name, new SpanUtils()
                .append(item.getSiteName())
                .setBold()
                .setForegroundColor(AppUtils.getColor(R.color.colorPrimary))
                .append(" : ")
                .setBold()
                .setForegroundColor(AppUtils.getColor(R.color.colorPrimary))
                .append(item.getName())
                .create());
    }
}
