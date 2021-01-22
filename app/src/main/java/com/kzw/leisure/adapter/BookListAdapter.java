package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.utils.GlideUtil;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class BookListAdapter extends BaseQuickAdapter<BookRealm, BaseViewHolder> {
    public BookListAdapter() {
        super(R.layout.grid_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookRealm item) {
        helper.setText(R.id.video_name, item.getBookName());
        GlideUtil.setBookImagesource(mContext, item.getCoverUrl(), helper.getView(R.id.video_pic));
    }
}
