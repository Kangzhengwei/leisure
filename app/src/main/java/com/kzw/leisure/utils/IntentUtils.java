package com.kzw.leisure.utils;

import android.content.Context;
import android.content.Intent;

import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.ui.activity.BookDetailActivity;
import com.kzw.leisure.ui.activity.ReadBookActivity;
import com.kzw.leisure.ui.activity.SearchBookActivity;
import com.kzw.leisure.ui.activity.SearchVideoActivity;
import com.kzw.leisure.ui.activity.VideoPlayActivity;

/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public class IntentUtils {

    public static void intentToSearchVideo(Context mContext) {
        mContext.startActivity(new Intent(mContext, SearchVideoActivity.class));
    }

    public static void intentToVideoPlay(Context context, SearchItem item) {
        context.startActivity(new Intent(context, VideoPlayActivity.class).putExtra("Item", item));
    }

    public static void intentToSearchBook(Context context) {
        context.startActivity(new Intent(context, SearchBookActivity.class));
    }

    public static void intentToBookDetailActivity(Context context, SearchBookBean searchBookBean, BookSourceRule rule) {
        context.startActivity(new Intent(context, BookDetailActivity.class).putExtra("SearchBook", searchBookBean).putExtra("BookRule", rule));
    }

    public static void intentToBookReadActivity(Context context) {
        context.startActivity(new Intent(context, ReadBookActivity.class));
    }


}
