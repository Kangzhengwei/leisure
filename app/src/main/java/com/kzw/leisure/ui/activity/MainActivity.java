package com.kzw.leisure.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.ui.fragment.BookFragment;
import com.kzw.leisure.ui.fragment.ExploreFragment;
import com.kzw.leisure.ui.fragment.VideoFragment;
import com.kzw.leisure.utils.RealmHelper;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation_bar)
    BottomNavigationView navigationBar;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment(ExploreFragment.class, null);
        navigationBar.getMenu().getItem(0).setChecked(true);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);   // Obtain the FirebaseAnalytics instance.
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
                    switchFragment(ExploreFragment.class, null);
                    navigationBar.getMenu().getItem(0).setChecked(true);
                    break;
                case R.id.navigation_book:
                    switchFragment(BookFragment.class, null);
                    navigationBar.getMenu().getItem(1).setChecked(true);
                    break;
                case R.id.navigation_collect:
                    switchFragment(VideoFragment.class, null);
                    navigationBar.getMenu().getItem(2).setChecked(true);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RealmHelper.getInstance().closeRealm();
    }
}
