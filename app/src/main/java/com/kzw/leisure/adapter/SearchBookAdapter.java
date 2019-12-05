package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.utils.GlideUtil;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class SearchBookAdapter extends BaseQuickAdapter<SearchBookBean, BaseViewHolder> {
    public SearchBookAdapter() {
        super(R.layout.search_book_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBookBean item) {
        helper.setText(R.id.item_book_name, item.getSearchName());
        helper.setText(R.id.item_book_lastUpdateChapter, item.getSearchLastChapter());
        helper.setText(R.id.item_book_author, item.getSearchAuthor());
        GlideUtil.setBookImagesource(mContext, item.getSearchCoverUrl(), helper.getView(R.id.roundImageView));
    }
}
