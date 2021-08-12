package com.kzw.leisure.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.kzw.leisure.realm.MyMigration;
import com.kzw.leisure.utils.AdMobUtils;
import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.CrashCatchUtil;
import com.kzw.leisure.utils.NetworkUtils;
import com.kzw.leisure.utils.SPUtils;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.player.SystemPlayerManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * author: kang4
 * Date: 2019/11/19
 * Description:
 */
public class BaseApplication extends Application {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        initDataBase();
        initPlayer();
    }

    private void initPlayer() {
        PlayerFactory.setPlayManager(SystemPlayerManager.class);
    }

    public static Application getInstance() {
        return instance;
    }

    private void init() {
        AppUtils.init(this);
        MultiDex.install(this);
        NetworkUtils.startNetService(this);
        CrashCatchUtil.getInstance().init(this);
        SPUtils.init(this, getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
        AdMobUtils.getInstance().initAd(this);
    }

    /**
     * 初始化数据库
     */
    private void initDataBase() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .schemaVersion(4)
                .migration(new MyMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
