package com.kzw.leisure.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.realm.HistoryDataBean;


public class HistoryAdapter extends BaseQuickAdapter<HistoryDataBean, BaseViewHolder> {

    public HistoryAdapter() {
        super(R.layout.website_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryDataBean item) {
        helper.setText(R.id.site_name, item.getUrlTitle());
        helper.setVisible(R.id.overflow,false);
    }


}
