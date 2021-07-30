package com.kzw.leisure.utils;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

public class AdMobUtils {

    private static volatile AdMobUtils sInstance;
    private long time;
    private RewardedInterstitialAd rewardedInterstitialAd;

    public static AdMobUtils getInstance() {
        if (sInstance == null) {
            synchronized (AdMobUtils.class) {
                if (sInstance == null) {
                    sInstance = new AdMobUtils();
                }
            }
        }
        return sInstance;
    }

    private AdMobUtils() {

    }

    public void initAd(Context context) {
        MobileAds.initialize(context, initializationStatus -> {
        });
    }

    public void loadFullScreenAd(Activity activity, adFinishListener listener) {
        if (System.currentTimeMillis() - time < 5000) {
            return;
        }
        time = System.currentTimeMillis();
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, Constant.FULL_SCREEN_AD_ID, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                interstitialAd.show(activity);
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        listener.onFinish();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        listener.onFinish();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        listener.onFinish();
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

            }
        });
    }


    public void loadAwardAd(Activity activity) {
        RewardedInterstitialAd.load(activity, Constant.AWARD_AD_ID, new AdRequest.Builder().build(), new RewardedInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(RewardedInterstitialAd ad) {
                rewardedInterstitialAd = ad;
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {

            }
        });
    }

    public void showAd(Activity activity, adFinishListener mListener) {
        if (rewardedInterstitialAd == null) {
            return;
        }
        rewardedInterstitialAd.show(activity, rewardItem -> {
            if (mListener != null) {
                mListener.onFinish();
            }
        });
    }

    public interface adFinishListener {
        void onFinish();
    }


}
