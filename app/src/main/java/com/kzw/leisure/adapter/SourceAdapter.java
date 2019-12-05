package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.BookSourceRule;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class SourceAdapter extends BaseQuickAdapter<BookSourceRule, BaseViewHolder> {
    public SourceAdapter() {
        super(R.layout.source_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookSourceRule item) {
        helper.setText(R.id.site_name, item.getSiteName());
        helper.setText(R.id.site_url, item.getBaseUrl());
    }
}
