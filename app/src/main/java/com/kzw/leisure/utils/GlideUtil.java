package com.kzw.leisure.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kzw.leisure.R;
import com.makeramen.roundedimageview.RoundedImageView;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class GlideUtil {

    public static void setVideoImagesource(Context mContext, String src, RoundedImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.video_img_default)
                .error(R.mipmap.video_img_default)
                .fallback(R.mipmap.video_img_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load(src).apply(options).into(imageView);
    }

    public static void setBookImagesource(Context mContext, String src, RoundedImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.icon_default)
                .error(R.mipmap.icon_default)
                .fallback(R.mipmap.icon_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext).load(src).apply(options).into(imageView);
    }

    public static void setBlurSource(Context context, String src, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.icon_default)
                .error(R.mipmap.icon_default)
                .fallback(R.mipmap.icon_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(src)
                .apply(options)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                .into(imageView);
    }
}
