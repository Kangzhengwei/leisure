package com.kzw.leisure.widgets.popwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.kzw.leisure.R;

/**
 * author: kang4
 * Date: 2019/11/21
 * Description:
 */
public class SiteOperationMenu extends PopupWindow {

    private menuClickListener clickListener;

    public SiteOperationMenu(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.site_operation, null);
        TextView delete = view.findViewById(R.id.delete);
        TextView moveTop = view.findViewById(R.id.move);
        delete.setOnClickListener(v -> {
            dismiss();
            if (clickListener != null) {
                clickListener.delete();
            }
        });
        moveTop.setOnClickListener(v -> {
            dismiss();
            if (clickListener != null) {
                clickListener.moveTop();
            }
        });
        // 设置外部可点击
        this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(view);
        // 设置弹出窗体的宽和高
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setClippingEnabled(false);
        // 实例化一个ColorDrawable颜色为半透明
    }

    public interface menuClickListener {
        void delete();

        void moveTop();
    }

    public void setMenuClickListener(menuClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
