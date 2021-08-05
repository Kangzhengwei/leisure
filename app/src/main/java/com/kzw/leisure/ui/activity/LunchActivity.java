package com.kzw.leisure.ui.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.SPUtils;
import com.kzw.leisure.utils.StatusBarUtil;

import butterknife.BindView;

public class LunchActivity extends BaseActivity {


    @BindView(R.id.iv_bg)
    ImageView ivBg;

    @Override
    protected int getContentView() {
        return R.layout.activity_lunch;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.fullScreen(this);
        ValueAnimator welAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(500);
        welAnimator.setStartDelay(100);
        welAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                IntentUtils.intentToMainActivity(mContext);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        welAnimator.start();
        saveStatus();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initData() {

    }

    private void saveStatus() {
        SPUtils.getInstance().putBoolean("isLaunch", true);
    }

}
