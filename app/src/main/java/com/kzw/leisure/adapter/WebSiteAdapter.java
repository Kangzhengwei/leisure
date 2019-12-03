package com.kzw.leisure.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.realm.WebSiteBean;


/**
 * author: kang4
 * Date: 2019/11/20
 * Description:
 */
public class WebSiteAdapter extends BaseQuickAdapter<WebSiteBean, BaseViewHolder> {

    private menuClickListener menuClickListener;

    public WebSiteAdapter() {
        super(R.layout.website_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, WebSiteBean item) {
        helper.setText(R.id.site_name, item.getSiteName());
        helper.getView(R.id.overflow).setOnClickListener(view -> {
            if (menuClickListener != null) {
                menuClickListener.menuClick(view, item, helper.getLayoutPosition());
            }
        });
    }

    public interface menuClickListener {
        void menuClick(View v, WebSiteBean bean, int position);
    }

    public void setMenuClickListener(menuClickListener listener) {
        menuClickListener = listener;
    }
}
