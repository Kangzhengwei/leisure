package com.kzw.leisure.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * author: kang4
 * Date: 2019/12/19
 * Description:
 */
@SuppressLint("AppCompatCustomView")
public class VerticalSeekBar extends SeekBar {

    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private int i = 0;
    private boolean isSelect = false;


    public VerticalSeekBar(Context context) {
        super(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener l) {
        mOnSeekBarChangeListener = l;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }


    protected void onDraw(Canvas c) {
        //将SeekBar转转90度
        c.rotate(-90);
        //将旋转后的视图移动回来
        c.translate(-getHeight(), 0);
        Log.i("getHeight()", getHeight() + "");
        super.onDraw(c);

    }

    void onStartTrackingTouch() {
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    void onProgressChanged() {
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onProgressChanged(this, i, true);
        }
    }

    void onStopTrackingTouch() {
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isSelect = true;
                onStartTrackingTouch();
                break;
            case MotionEvent.ACTION_MOVE:
                isSelect = true;
                //获取滑动的距离
                i = getMax() - (int) (getMax() * event.getY() / getHeight());
                //设置进度
                setProgress(i);
                Log.i("Progress", getProgress() + "");
                //每次拖动SeekBar都会调用
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                Log.i("getWidth()", getWidth() + "");
                Log.i("getHeight()", getHeight() + "");
                onProgressChanged();
                break;
            case MotionEvent.ACTION_UP:
                isSelect = false;
                onStopTrackingTouch();
                break;
            case MotionEvent.ACTION_CANCEL:
                isSelect = false;
                onStopTrackingTouch();
                break;
        }
        return true;
    }

    public boolean isSelect() {
        return isSelect;
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }
}

