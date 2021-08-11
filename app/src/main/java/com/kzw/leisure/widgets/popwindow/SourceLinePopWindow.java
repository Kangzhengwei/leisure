package com.kzw.leisure.widgets.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.SourceLineAdapter;
import com.kzw.leisure.bean.LineBean;
import com.kzw.leisure.bean.VideoBean;

import java.util.List;

public class SourceLinePopWindow extends PopupWindow {

    private RecyclerView recyclerView;
    private SourceLineAdapter adapter;
    private itemClickListener mListener;

    public static SourceLinePopWindow builder(Context context) {
        return new SourceLinePopWindow(context);
    }

    public SourceLinePopWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.source_line_pop_layout, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SourceLineAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            List list = adapter.getData();
            Object object = list.get(position);
            if (object instanceof LineBean) {
                if (mListener != null) {
                    mListener.itemClick((LineBean) object);
                }
            }
            dismiss();
        });

        // 设置外部可点击
        this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(false);
        // 从上面弹出
        this.setAnimationStyle(R.style.bottom_anim);
    }

    public SourceLinePopWindow setData(List<LineBean> list) {
        adapter.setNewData(list);
        return this;
    }

    public void show(View view) {
        showAtLocation(view, Gravity.BOTTOM,0,0);
    }


    public interface itemClickListener {
        void itemClick(LineBean item);
    }

    public SourceLinePopWindow setItemClickListener(itemClickListener clickListener) {
        this.mListener = clickListener;
        return this;
    }
}
