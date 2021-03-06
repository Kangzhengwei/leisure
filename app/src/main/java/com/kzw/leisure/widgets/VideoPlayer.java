package com.kzw.leisure.widgets;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kzw.leisure.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by Android on 2018/8/6.
 */

public class VideoPlayer extends StandardGSYVideoPlayer {
    protected TextView tvAlert;
    protected TextView tvRate;
    protected VideoPlayerRateWindow popwin;
    protected ImageView backof;
    protected ImageView forword;
    protected TextView checkSeries;
    protected long exitTime = 0;
    protected int times = 0;

    protected checkSeriesClickListener mListener;

    public VideoPlayer(Context context) {
        super(context);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_player_view;
    }

    @Override
    protected void init(final Context context) {
        super.init(context);
        tvAlert = findViewById(R.id.video_alert);
        mBackButton = findViewById(R.id.back);
        tvRate = findViewById(R.id.play_rate);
        backof = findViewById(R.id.back_of);
        forword = findViewById(R.id.forward);
        checkSeries = findViewById(R.id.check_series);
        mLockScreen = findViewById(R.id.lock_screen);
        mLockScreen.setVisibility(View.GONE);
        checkSeries.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onClick();
            }
        });
        backof.setOnClickListener(v -> {
            if ((System.currentTimeMillis() - exitTime) > 600) {
                exitTime = System.currentTimeMillis();
                long position = getCurrentPositionWhenPlaying();//346541
                long time = position - 15 * 1000;
                seekTo(time);
            } else {
                if (times < 5) {
                    times++;
                    if (times == 4) {
                        times = 0;
                    }
                    if (times == 0) {
                        Toast.makeText(context, "点击不能太频繁哦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        forword.setOnClickListener(v -> {
            if ((System.currentTimeMillis() - exitTime) > 600) {
                exitTime = System.currentTimeMillis();
                long position = getCurrentPositionWhenPlaying();//346541
                long time = position + 15 * 1000;
                seekTo(time);
            } else {
                if (times < 5) {
                    times++;
                    if (times == 4) {
                        times = 0;
                    }
                    if (times == 0) {
                        Toast.makeText(context, "点击不能太频繁哦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRate.setOnClickListener(v -> {
            popwin = new VideoPlayerRateWindow(context);
            float speed = getSpeed();
            popwin.setSelector(speed);
            popwin.setPopClickListener(new VideoPlayerRateWindow.PopClickListener() {
                @Override
                public void tvOneClick() {
                    setSpeedPlaying(0.5f, true);
                    tvRate.setText("0.5");
                    popwin.dismiss();
                }

                @Override
                public void tvTwoClick() {
                    setSpeedPlaying(0.75f, true);
                    tvRate.setText("0.75");
                    popwin.dismiss();
                }

                @Override
                public void tvThreeClick() {
                    setSpeedPlaying(1.0f, true);
                    tvRate.setText("1.0");
                    popwin.dismiss();
                }

                @Override
                public void tvFourClick() {
                    setSpeedPlaying(1.25f, true);
                    tvRate.setText("1.25");
                    popwin.dismiss();
                }

                @Override
                public void tvFiveClick() {
                    setSpeedPlaying(1.5f, true);
                    tvRate.setText("1.5");
                    popwin.dismiss();
                }
            });
            popwin.showAsDropDown(findViewById(R.id.play_rate), 0, 50);
        });
    }

    public void SeekTo(long position) {
        setSeekOnStart(position);
    }


    @Override
    protected void hideAllWidget() {
        super.hideAllWidget();
        if (popwin != null) {
            popwin.dismiss();
        }
    }

    @Override
    protected void onClickUiToggle() {
        super.onClickUiToggle();
        if (mNeedLockFull) {
            mLockScreen.setVisibility(View.VISIBLE);
            if (mLockCurScreen) {
                hideAllWidget();
            }
        }
    }

    @Override
    public StandardGSYVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        StandardGSYVideoPlayer standardGSYVideoPlayer = (StandardGSYVideoPlayer) super.startWindowFullscreen(context, actionBar, statusBar);
        if (!isIfCurrentIsFullscreen()) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (currentVolume == 0) {
                Toast.makeText(context, "调大音量才能听到声音哦", Toast.LENGTH_SHORT).show();
            }
        }
        if (standardGSYVideoPlayer != null) {
            VideoPlayer videoPlayer = (VideoPlayer) standardGSYVideoPlayer;
            initUI(videoPlayer);
        }
        return standardGSYVideoPlayer;
    }

    private void initUI(VideoPlayer videoPlayer) {
        if (mListener != null) {
            videoPlayer.setCheckSeriesClickListener(mListener);
        }
    }


    public ImageView getBackButton() {
        return mBackButton;
    }

    public TextView getCheckSeries() {
        return checkSeries;
    }

    @Override
    protected void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        tvAlert.setVisibility(View.GONE);
    }

    @Override
    protected void changeUiToNormal() {
        super.changeUiToNormal();
        tvAlert.setVisibility(View.GONE);
    }

    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        tvAlert.setVisibility(View.VISIBLE);
        tvAlert.setText("载入中...");
    }

    @Override
    protected void changeUiToPlayingBufferingShow() {
        super.changeUiToPlayingBufferingShow();
        tvAlert.setVisibility(View.VISIBLE);
        tvAlert.setText("加载中...");
    }

    @Override
    protected void changeUiToCompleteShow() {
        super.changeUiToCompleteShow();
    }

    @Override
    protected void changeUiToError() {
        super.changeUiToError();
        tvAlert.setText("加载失败");
        mStartButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void showProgressDialog(float deltaX, String seekTime, int seekTimePosition, String totalTime, int totalTimeDuration) {
        super.showProgressDialog(deltaX, seekTime, seekTimePosition, totalTime, totalTimeDuration);
        mCurrentTimeTextView.setText(seekTime);
        mProgressBar.setProgress(seekTimePosition * 100 / totalTimeDuration);
        mBottomProgressBar.setProgress(seekTimePosition * 100 / totalTimeDuration);
    }

    public interface checkSeriesClickListener {
        void onClick();
    }

    public void setCheckSeriesClickListener(checkSeriesClickListener listener) {
        mListener = listener;
    }


}
