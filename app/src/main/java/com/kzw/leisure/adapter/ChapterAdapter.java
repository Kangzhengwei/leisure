package com.kzw.leisure.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.realm.BookContentBean;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.SpanUtils;

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
        if (isHasChapter(item)) {
            helper.setText(R.id.item_catalog_name, new SpanUtils()
                    .append(item.getChapterName())
                    .setBold()
                    .create());
        } else {
            helper.setText(R.id.item_catalog_name, item.getChapterName());
        }
    }

    private boolean isHasChapter(Chapter item) {
        BookContentBean bean = RealmHelper.getInstance().getRealm().where(BookContentBean.class).equalTo("durChapterUrl", item.getChapterUrl()).findFirst();
        return bean != null;
    }
}
