package com.kzw.leisure.widgets.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.CheckSeriesAdapter;
import com.kzw.leisure.bean.VideoBean;

import java.util.List;

/**
 * author: kang4
 * Date: 2021/1/18
 * Description:
 */
public class CheckSeriesPopWindow extends PopupWindow {

    private RecyclerView recyclerView;
    private CheckSeriesAdapter adapter;
    private itemClickListener mListener;

    public CheckSeriesPopWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_pop_layout, null);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CheckSeriesAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            dismiss();
            List list = adapter.getData();
            Object object = list.get(position);
            if (object instanceof VideoBean.Series.Url) {
                if (mListener != null) {
                    mListener.itemClick(((VideoBean.Series.Url) object), position);
                }
            }
        });
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
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.half_blank));
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 从上面弹出
        this.setAnimationStyle(R.style.top_pop_in);
    }

    public void setData(List<VideoBean.Series.Url> list) {
        adapter.setNewData(list);
    }

    public interface itemClickListener {
        void itemClick(VideoBean.Series.Url item, int position);
    }

    public void setItemClickListener(itemClickListener clickListener) {
        this.mListener = clickListener;
    }

}
