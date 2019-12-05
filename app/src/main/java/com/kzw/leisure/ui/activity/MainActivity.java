package com.kzw.leisure.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.ui.fragment.BookFragment;
import com.kzw.leisure.ui.fragment.CollectFragment;
import com.kzw.leisure.ui.fragment.MovieFragment;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation_bar)
    BottomNavigationView navigationBar;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment(MovieFragment.class, null);
        navigationBar.getMenu().getItem(0).setChecked(true);
    }

    @Override
    protected void initPresenter() { }

    @Override
    public void initData() { }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    switchFragment(MovieFragment.class, null);
                    navigationBar.getMenu().getItem(0).setChecked(true);
                    break;
                case R.id.navigation_book:
                    switchFragment(BookFragment.class, null);
                    navigationBar.getMenu().getItem(1).setChecked(true);
                    break;
                case R.id.navigation_collect:
                    switchFragment(CollectFragment.class, null);
                    navigationBar.getMenu().getItem(2).setChecked(true);
                    break;
            }
            return false;
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                MovieFragment fragment = (MovieFragment) getFragment(MovieFragment.class);
                if (fragment != null && fragment.isVisible()) {
                    if (fragment.customView != null) {
                        fragment.hideCustomView(fragment.webview);
                    } else if (fragment.webview.canGoBack()) {
                        fragment.webview.goBack();
                    } else {
                        finish();
                    }
                } else {
                    finish();
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    public void intentFragment(String url) {
        MovieFragment fragment = (MovieFragment) getFragment(MovieFragment.class);
        if (fragment != null) {
            fragment.loadUrl(url);
            switchFragment(MovieFragment.class, null);
            navigationBar.getMenu().getItem(0).setChecked(true);
        }
    }


}
