package com.kzw.leisure.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kzw.leisure.R;

/**
 * Created by Android on 2018/8/7.
 */

public class VideoPlayerRateWindow extends PopupWindow {

    public PopClickListener mclickistener;
    private TextView tvOne, tvTwo, tvThree, tvFour, tvFive;

    public VideoPlayerRateWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_player_rate_window, null);
        tvOne = view.findViewById(R.id.rate_one);
        tvTwo = view.findViewById(R.id.rate_two);
        tvThree = view.findViewById(R.id.rate_three);
        tvFour = view.findViewById(R.id.rate_four);
        tvFive = view.findViewById(R.id.rate_five);

        tvOne.setOnClickListener(v -> {
            if (mclickistener != null) {
                mclickistener.tvOneClick();
            }
        });
        tvTwo.setOnClickListener(v -> {
            if (mclickistener != null) {
                mclickistener.tvTwoClick();
            }
        });
        tvThree.setOnClickListener(v -> {
            if (mclickistener != null) {
                mclickistener.tvThreeClick();
            }
        });
        tvFour.setOnClickListener(v -> {
            if (mclickistener != null) {
                mclickistener.tvFourClick();
            }
        });
        tvFive.setOnClickListener(v -> {
            if (mclickistener != null) {
                mclickistener.tvFiveClick();
            }
        });

        // 设置外部可点击
        this.setOutsideTouchable(false);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 从上面弹出
        this.setAnimationStyle(R.style.top_pop_in);
        Activity activity = (Activity) context;
        hideBottomUIMenu(activity);
    }

    public interface PopClickListener {
        void tvOneClick();

        void tvTwoClick();

        void tvThreeClick();

        void tvFourClick();

        void tvFiveClick();
    }

    public void setPopClickListener(PopClickListener popClickListener) {
        mclickistener = popClickListener;
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu(Activity context) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = context.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = context.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    public void setSelector(float speed) {
        if (speed == 0.5f) {
            tvOne.setSelected(true);
        } else if (speed == 0.75f) {
            tvTwo.setSelected(true);
        } else if (speed == 1.0f) {
            tvThree.setSelected(true);
        } else if (speed == 1.25f) {
            tvFour.setSelected(true);
        } else if (speed == 1.5f) {
            tvFive.setSelected(true);
        }
    }
}
