package com.kzw.leisure.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.kzw.leisure.R;
import com.kzw.leisure.utils.ScreenUtils;
import com.kzw.leisure.utils.Selector;
import com.kzw.leisure.utils.theme.ThemeStore;

import androidx.appcompat.widget.AppCompatTextView;

public class ATEStrokeTextView extends AppCompatTextView {
    public ATEStrokeTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ATEStrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ATEStrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ATEStrokeTextView);

        setBackground(Selector.shapeBuild()
                .setCornerRadius(a.getDimensionPixelSize(R.styleable.ATEStrokeTextView_cornerRadius, 1))
                .setStrokeWidth(ScreenUtils.dpToPx(1))
                .setDisabledStrokeColor(context.getResources().getColor(R.color.md_grey_500))
                .setDefaultStrokeColor(ThemeStore.textColorSecondary(context))
                .setSelectedStrokeColor(ThemeStore.accentColor(context))
                .setPressedBgColor(context.getResources().getColor(R.color.transparent30))
                .create());
        setTextColor(Selector.colorBuild()
                .setDefaultColor(ThemeStore.textColorSecondary(context))
                .setSelectedColor(ThemeStore.accentColor(context))
                .setDisabledColor(context.getResources().getColor(R.color.md_grey_500))
                .create());
    }
}
