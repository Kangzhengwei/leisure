package com.kzw.leisure.widgets;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.kzw.leisure.R;
import com.kzw.leisure.widgets.pageView.ReadBookControl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: kang4
 * Date: 2019/12/13
 * Description:
 */
public class AdjustMenu extends FrameLayout {
    @BindView(R.id.scb_follow_sys)
    SmoothCheckBox scbFollowSys;
    @BindView(R.id.ll_follow_sys)
    LinearLayout llFollowSys;
    @BindView(R.id.hpb_light)
    SeekBar hpbLight;
    private ReadBookControl readBookControl = ReadBookControl.getInstance();
    private Activity activity;

    public AdjustMenu(Context context) {
        super(context);
        init(context);
        ;
    }

    public AdjustMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdjustMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.adjust_pop_layout, this);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        scbFollowSys.setOnCheckedChangeListener((checkBox, isChecked) -> {
            readBookControl.setLightFollowSys(isChecked);
            if (isChecked) {
                //跟随系统
                hpbLight.setEnabled(false);
                setScreenBrightness();
            } else {
                //不跟随系统
                hpbLight.setEnabled(true);
                setScreenBrightness(readBookControl.getLight());
            }
        });
        hpbLight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (!readBookControl.getLightFollowSys()) {
                    readBookControl.setLight(i);
                    setScreenBrightness(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initData(Activity activity) {
        this.activity = activity;
        hpbLight.setProgress(readBookControl.getLight());
        scbFollowSys.setChecked(readBookControl.getLightFollowSys());
        if (!readBookControl.getLightFollowSys()) {
            setScreenBrightness(readBookControl.getLight());
        }
    }

    public void setScreenBrightness() {
        WindowManager.LayoutParams params = (activity).getWindow().getAttributes();
        params.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        (activity).getWindow().setAttributes(params);
    }

    public void setScreenBrightness(int value) {
        if (value < 1) value = 1;
        WindowManager.LayoutParams params = (activity).getWindow().getAttributes();
        params.screenBrightness = value * 1.0f / 255f;
        (activity).getWindow().setAttributes(params);
    }
}
