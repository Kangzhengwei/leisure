package com.kzw.leisure.utils;

import android.content.Context;
import android.content.Intent;

import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.ui.activity.BookDetailActivity;
import com.kzw.leisure.ui.activity.DonateActivity;
import com.kzw.leisure.ui.activity.DownloadFrontActivity;
import com.kzw.leisure.ui.activity.MainActivity;
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

    public static void intentToBookDetailActivity(Context context, SearchBookBean searchBookBean) {
        context.startActivity(new Intent(context, BookDetailActivity.class).putExtra("SearchBook", searchBookBean));
    }

    public static void intentToBookReadActivity(Context context) {
        context.startActivity(new Intent(context, ReadBookActivity.class));
    }

    public static void intentToDownloadFront(Context context) {
        context.startActivity(new Intent(context, DownloadFrontActivity.class));
    }

    public static void intentToDonateActivity(Context context) {
        context.startActivity(new Intent(context, DonateActivity.class));
    }


    public static void intentToMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }


}
