package com.kzw.leisure.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.kzw.leisure.R;
import com.kzw.leisure.utils.ActivityManagerUtils;
import com.kzw.leisure.utils.PermessionUtil;
import com.kzw.leisure.utils.TUtil;
import com.kzw.leisure.widgets.ToastUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: kang4
 * Date: 2019/11/19
 * Description:
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {

    public E mModel;
    public T mPresenter;
    public Context mContext;
    private Unbinder bind;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(getContentView());
        bind = ButterKnife.bind(this);
        ActivityManagerUtils.getActivityManager().addActivity(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initView(savedInstanceState);
        this.initData();
        PermessionUtil.checkPermession(this);
    }


    /**
     * 初始化布局
     *
     * @return
     */
    abstract protected int getContentView();

    /**
     * 初始化view
     *
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * presenter初始化
     */
    abstract protected void initPresenter();

    /**
     * 初始化数据
     */
    abstract public void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        ActivityManagerUtils.getActivityManager().finishActivity(this);
        bind.unbind();
    }

    public void showToast(String msg) {
        ToastUtil.showToast(msg);
    }


    /**
     * 切换fragment
     */
    public void switchFragment(Class<? extends Fragment> type, Bundle args) {
        FragmentManager manager = getSupportFragmentManager();
        String tagString = type.getSimpleName();
        Fragment fragment = manager.findFragmentByTag(tagString);
        hideAllFragment(manager);
        if (fragment == null) {
            try {
                fragment = type.newInstance();
                if (args != null)
                    fragment.setArguments(args);
                FragmentTransaction ts = manager.beginTransaction();
                ts.add(R.id.container, fragment, tagString);
                ts.commit();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            FragmentTransaction ts = manager.beginTransaction();
            ts.show(fragment);
            ts.commit();
        }
    }

    private void hideAllFragment(FragmentManager manager) {
        List<Fragment> fragmentList = manager.getFragments();
        if (fragmentList.isEmpty())
            return;
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : fragmentList) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    public Fragment getFragment(Class<? extends Fragment> type) {
        FragmentManager manager = getSupportFragmentManager();
        String tagString = type.getSimpleName();
        return manager.findFragmentByTag(tagString);
    }

    @SuppressLint("PrivateApi")
    @SuppressWarnings("unchecked")
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            //展开菜单显示图标
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                    method = menu.getClass().getDeclaredMethod("getNonActionItems");
                    ArrayList<MenuItem> menuItems = (ArrayList<MenuItem>) method.invoke(menu);
                    if (!menuItems.isEmpty()) {
                        for (MenuItem menuItem : menuItems) {
                            Drawable drawable = menuItem.getIcon();
                            if (drawable != null) {
                                drawable.mutate();
                                drawable.setColorFilter(getResources().getColor(R.color.blank), PorterDuff.Mode.SRC_ATOP);
                            }
                        }
                    }
                } catch (Exception ignored) {
                }
            }

        }
        return super.onMenuOpened(featureId, menu);
    }

    protected void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    //设置ToolBar
    protected void setupActionBar(boolean isShowTitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(isShowTitle);
        }
    }


    protected void setActionBar(String title, boolean homeDisplay, Toolbar toolbar) {
        setToolbar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeDisplay);
            if (TextUtils.isEmpty(title)) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            } else {
                getSupportActionBar().setTitle(title);
            }
        }
    }




}
