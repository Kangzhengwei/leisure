package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.Chapter;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class ChapterAdapter extends BaseQuickAdapter<Chapter, BaseViewHolder> {
    public ChapterAdapter() {
        super(R.layout.item_catalog);
    }

    @Override
    protected void convert(BaseViewHolder helper, Chapter item) {
        helper.setText(R.id.item_catalog_name, item.getChapterName());
    }
}
