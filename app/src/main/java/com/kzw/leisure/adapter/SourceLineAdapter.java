package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.LineBean;

public class SourceLineAdapter extends BaseQuickAdapter<LineBean, BaseViewHolder> {
    public SourceLineAdapter() {
        super(R.layout.website_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, LineBean item) {
        helper.setText(R.id.site_name, item.getName());
        helper.setVisible(R.id.overflow,false);
    }
}
