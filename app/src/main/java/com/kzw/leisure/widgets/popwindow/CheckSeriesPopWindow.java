package com.kzw.leisure.widgets.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.CheckSeriesAdapter;
import com.kzw.leisure.bean.VideoBean;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: kang4
 * Date: 2021/1/18
 * Description:
 */
public class CheckSeriesPopWindow extends PopupWindow {

    private RecyclerView recyclerView;
    private CheckSeriesAdapter adapter;

    public CheckSeriesPopWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_pop_layout, null);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter=new CheckSeriesAdapter();
        recyclerView.setAdapter(adapter);
        // 设置外部可点击
        this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setFocusable(false);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 从上面弹出
        this.setAnimationStyle(R.style.top_pop_in);
        Activity activity = (Activity) context;
        hideBottomUIMenu(activity);
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

    public void setData(List<VideoBean.Series.Url> list){
        adapter.setNewData(list);
    }

}
