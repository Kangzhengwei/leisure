package com.kzw.leisure.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.kzw.leisure.utils.DimenUtil;


/**
 * author: kang4
 * Date: 2021/1/21
 * Description:
 */
public class WordWrapView extends ViewGroup {
    private int PADDING_HOR = 16;//水平方向padding
    private int PADDING_VERTICAL = 6;//垂直方向padding
    private int SIDE_MARGIN = 16;//左右间距
    private int TEXT_MARGIN = 16;

    /**
     * @param context
     */
    public WordWrapView(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public WordWrapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        PADDING_HOR = DimenUtil.dip2px(context, 12);
        PADDING_VERTICAL = DimenUtil.dip2px(context, 6);
    }


    /**
     * @param context
     * @param attrs
     */
    public WordWrapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        int x = SIDE_MARGIN;// 横坐标开始
        int y = 0;//纵坐标开始
        int rows = 1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
           /* view.setBackgroundColor(Color.GREEN);
            view.setBackgroundColor(getResources().getColor(R.color.transparent));
            view.setBackgroundResource(R.drawable.searchtag_textview_round_corner);*/
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            x += width + TEXT_MARGIN + (i == 0 ? 0 : TEXT_MARGIN);
            if (x > autualWidth) {
                x = width + SIDE_MARGIN;
                rows++;
            }
            if (rows == 1) {
                y = rows * (height + TEXT_MARGIN);
            } else {
                y = rows * (height + TEXT_MARGIN * 2);
            }
            if (i == 0) {
                view.layout(x - width - TEXT_MARGIN, y - height, x - TEXT_MARGIN, y);
            } else {
                view.layout(x - width, y - height, x, y);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;//横坐标
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - SIDE_MARGIN * 2;//实际宽度
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            child.setPadding(PADDING_HOR, PADDING_VERTICAL, PADDING_HOR, PADDING_VERTICAL);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width + TEXT_MARGIN * 2;
            if (x > actualWidth) {//换行
                x = width;
                rows++;
            }
            y = rows * (height + (TEXT_MARGIN * 2)) + TEXT_MARGIN;
        }
        setMeasuredDimension(actualWidth, y);
    }


    public void setSideMargin(int margin) {
        SIDE_MARGIN = margin;
    }
}
