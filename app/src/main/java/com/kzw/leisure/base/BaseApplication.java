package com.kzw.leisure.base;

import android.app.Application;
import android.content.Context;

import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.CrashCatchUtil;
import com.kzw.leisure.utils.LogUtils;
import com.kzw.leisure.utils.NetworkUtils;
import com.kzw.leisure.utils.SPUtils;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

import androidx.multidex.MultiDex;
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
        initX5();
        initDataBase();
    }

    public static Application getInstance() {
        return instance;
    }

    private void init() {
        CrashCatchUtil.getInstance().init(this);
        MultiDex.install(this);
        AppUtils.init(this);
        NetworkUtils.startNetService(this);
        SPUtils.init(this, getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }


    private void initX5() {
        //x5内核初始化接口
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.e(" onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        QbSdk.initTbsSettings(map);
    }

    /**
     * 初始化数据库
     */
    private void initDataBase() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
