package com.kzw.leisure.widgets.pageView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kzw.leisure.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: kang4
 * Date: 2019/12/6
 * Description:
 */
public class BottomMenuWidget extends FrameLayout {

    @BindView(R.id.tv_pre)
    TextView tvPre;
    @BindView(R.id.hpb_read_progress)
    SeekBar hpbReadProgress;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.ll_catalog)
    LinearLayout llCatalog;
    @BindView(R.id.ll_adjust)
    LinearLayout llAdjust;
    @BindView(R.id.ll_font)
    LinearLayout llFont;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.llNavigationBar)
    LinearLayout llNavigationBar;


    private Callback callback;


    public BottomMenuWidget(Context context) {
        super(context);
        init(context);
    }

    public BottomMenuWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_menu_layout, this);
        ButterKnife.bind(this, view);
        hpbReadProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                callback.skipToPage(seekBar.getProgress());
            }
        });
    }

    public void setListener(Callback callback) {
        this.callback = callback;
    }

    public void setTvPre(boolean enable) {
        tvPre.setEnabled(enable);
    }

    public void setTvNext(boolean enable) {
        tvNext.setEnabled(enable);
    }

    public SeekBar getReadProgress() {
        return hpbReadProgress;
    }


    @OnClick({R.id.tv_pre, R.id.tv_next, R.id.ll_catalog, R.id.ll_adjust, R.id.ll_font, R.id.ll_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pre:
                callback.skipPreChapter();
                break;
            case R.id.tv_next:
                callback.skipNextChapter();
                break;
            case R.id.ll_catalog:
                callback.openChapterList();
                break;
            case R.id.ll_adjust:
                callback.openAdjust();
                break;
            case R.id.ll_font:
                callback.openReadInterface();
                break;
            case R.id.ll_setting:
                callback.openMoreSetting();
                break;
        }
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
