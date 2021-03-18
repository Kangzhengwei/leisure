package com.kzw.leisure.adapter;

import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.realm.SourceRuleRealm;

/**
 * author: kang4
 * Date: 2019/12/12
 * Description:
 */
public class ReadBookSourceAdapter extends BaseQuickAdapter<SourceRuleRealm, BaseViewHolder> {

    private SourceRuleRealm currentRule;
    public ReadBookSourceAdapter(SourceRuleRealm currentRule) {
        super(R.layout.source_list_item);
        this.currentRule=currentRule;
    }

    @Override
    protected void convert(BaseViewHolder helper, SourceRuleRealm item) {
        helper.setText(R.id.site_name, item.getSiteName());
        helper.setText(R.id.site_url, item.getBaseUrl());
        RadioButton radioButton = helper.getView(R.id.tick);
        radioButton.setClickable(false);
        radioButton.setChecked(currentRule != null && currentRule.getBaseUrl().equals(item.getBaseUrl()));
    }
}
