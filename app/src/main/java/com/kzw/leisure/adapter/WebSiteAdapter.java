package com.kzw.leisure.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.ExploreMultiItemBean;
import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.DimenUtil;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.StringUtils;

import java.util.List;


/**
 * author: kang4
 * Date: 2019/11/20
 * Description:
 */
public class WebSiteAdapter extends BaseMultiItemQuickAdapter<ExploreMultiItemBean, BaseViewHolder> {

    private int width;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public WebSiteAdapter(List<ExploreMultiItemBean> data, Activity activity) {
        super(data);
        addItemType(ExploreMultiItemBean.TYPE_TAG, R.layout.explore_tags_item);
        addItemType(ExploreMultiItemBean.TYPE_ITEM, R.layout.explore_list_item);
        width = (DimenUtil.getScreeWidth(activity) - DimenUtil.dip2px(activity, 12) * 3) / 2;
    }

    @Override
    protected void convert(BaseViewHolder helper, ExploreMultiItemBean item) {
        switch (item.getItemType()) {
            case ExploreMultiItemBean.TYPE_TAG:
                ImageView imageView = helper.getView(R.id.image_tag);
                imageView.setColorFilter(AppUtils.getColor(R.color.colorPrimary));
                helper.setText(R.id.tv_tag_name, item.getTagName());
                break;
            case ExploreMultiItemBean.TYPE_ITEM:
                CardView cardView = helper.getView(R.id.card_view);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cardView.getLayoutParams();
                params.width = width;
                cardView.setLayoutParams(params);
                helper.setText(R.id.tv_tag, StringUtils.getFirstChar(item.getBean().getSiteName()));
                helper.setBackgroundColor(R.id.tv_tag, Color.parseColor(StringUtils.getRandColor()));
                helper.setText(R.id.tv_site_name, item.getBean().getSiteName());
                helper.setText(R.id.site_url, item.getBean().getUrl());
                cardView.setOnClickListener(view -> IntentUtils.intentToBrowserActivity(mContext, item.getBean().getUrl(),0));
                break;
        }
    }
}
