package com.kzw.leisure.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.realm.CollectDataBean;

/**
 * author: kang4
 * Date: 2019/11/21
 * Description:
 */
public class WebSiteCollectAdapter extends BaseQuickAdapter<CollectDataBean, BaseViewHolder> {

    private menuClickListener menuClickListener;

    public WebSiteCollectAdapter() {
        super(R.layout.website_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectDataBean item) {
        helper.setText(R.id.site_name, item.getUrlTitle());
        helper.getView(R.id.overflow).setOnClickListener(view -> {
            if (menuClickListener != null) {
                menuClickListener.menuClick(view, item, helper.getLayoutPosition());
            }
        });
    }
    public interface menuClickListener {
        void menuClick(View v, CollectDataBean bean, int position);
    }

    public void setMenuClickListener(menuClickListener listener) {
        menuClickListener = listener;
    }
}
