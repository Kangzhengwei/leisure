package com.kzw.leisure.utils;

import android.content.Context;
import android.content.Intent;

import com.kzw.leisure.bean.SearchItem;
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
}
