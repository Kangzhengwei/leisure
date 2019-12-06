package com.kzw.leisure.widgets.pageView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.kzw.leisure.R;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class BottomMenuWidget extends FrameLayout {
    private Callback callback;
    private LinearLayout ll_catalog;

    public BottomMenuWidget(Context context) {
        super(context);
        init(context);
    }

    public BottomMenuWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomMenuWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_menu_layout, this);
        ll_catalog = view.findViewById(R.id.ll_catalog);
    }

    public void setListener(Callback callback) {
        this.callback = callback;
        bindEvent();
    }

    private void bindEvent() {
        ll_catalog.setOnClickListener(view -> callback.openChapterList());
    }

    public interface Callback {
        void skipToPage(int id);

        void onMediaButton();

        void autoPage();

        void setNightTheme();

        void skipPreChapter();

        void skipNextChapter();

        void openReplaceRule();

        void openChapterList();

        void openAdjust();

        void openReadInterface();

        void openMoreSetting();

        void toast(int id);

        void dismiss();
    }
}
