package com.kzw.leisure.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.kzw.leisure.R;

/**
 * author: kang4
 * Date: 2019/9/24
 * Description:
 */
public class ProgressDialog extends Dialog {

    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.transparent_dialog);
        init(context);
    }
    public void init(final Context context) {
        View v =  LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
        // 设置布局，设为全屏
        setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }
}
