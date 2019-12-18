package com.kzw.leisure.ui.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunchActivity extends BaseActivity {


    @BindView(R.id.iv_bg)
    ImageView ivBg;

    @Override
    protected int getContentView() {
        return R.layout.activity_lunch;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ValueAnimator welAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(800);
        welAnimator.setStartDelay(500);
        welAnimator.addUpdateListener(animation -> {
            float alpha = (Float) animation.getAnimatedValue();
            if (ivBg != null) {
                ivBg.setAlpha(alpha);
            }
        });
        welAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                IntentUtils.intentToMainActivity(mContext);
                finish();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        welAnimator.start();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initData() {

    }

}
