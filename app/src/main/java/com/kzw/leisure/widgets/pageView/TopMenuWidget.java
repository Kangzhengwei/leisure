package com.kzw.leisure.widgets.pageView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.kzw.leisure.R;
import com.kzw.leisure.utils.StatusBarUtil;

import androidx.appcompat.widget.Toolbar;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class TopMenuWidget extends FrameLayout {

    public Toolbar toolbar;

    public TopMenuWidget(Context context) {
        super(context);
        init(context);
    }

    public TopMenuWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopMenuWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_menu_layout, this);
        toolbar = view.findViewById(R.id.toolbar);
        AppBarLayout.LayoutParams param = new AppBarLayout.LayoutParams(toolbar.getLayoutParams());
        param.topMargin = StatusBarUtil.getStatusBarHeight(context);
        toolbar.setLayoutParams(param);
    }
}
