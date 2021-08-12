package com.kzw.leisure.ui.fragment;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.WebSiteAdapter;
import com.kzw.leisure.base.BaseFragment;
import com.kzw.leisure.bean.ExploreMultiItemBean;
import com.kzw.leisure.contract.WebSiteSourceContract;
import com.kzw.leisure.model.WebSiteSourceModel;
import com.kzw.leisure.presenter.WebSiteSourcePresenter;
import com.kzw.leisure.utils.CacheUtils;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.PermessionUtil;
import com.kzw.leisure.utils.WebClient;
import com.kzw.leisure.widgets.X5WebView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ExploreFragment extends BaseFragment<WebSiteSourcePresenter, WebSiteSourceModel> implements WebSiteSourceContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.slide_menu)
    RelativeLayout slideMenu;
    @BindView(R.id.navigation)
    NavigationView navigationView;
    @BindView(R.id.webView)
    X5WebView webView;

    private ActionBarDrawerToggle mDrawerToggle;
    private List<ExploreMultiItemBean> list = new ArrayList<>();
    private WebSiteAdapter adapter;

    @Override
    public void initPresenter() {
        super.initPresenter();
        mPresenter.setVM(this, mModel);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setHasOptionsMenu(true);
        setToolbar(toolbar);
        setupActionBar(false);
        initNavigation();
        initRecyclerView();
        mDrawerToggle = new ActionBarDrawerToggle(mActivity, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        drawer.addDrawerListener(mDrawerToggle);
        initWebView();
    }

    private void initWebView() {
        webView.setWebViewClient(new WebClient());
        webView.loadUrl(Constant.DEFAULT_URL);
    }

    @Override
    protected void initData() {
        mPresenter.getSource();
    }

    @Override
    public void onResume() {
        super.onResume();
        PermessionUtil.checkPermession(getActivity());
    }

    public void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter = new WebSiteAdapter(list, mActivity);
        recyclerview.setAdapter(adapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = adapter.getItemViewType(position);
                if (type == ExploreMultiItemBean.TYPE_TAG) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.settting_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                } else {
                    drawer.openDrawer(GravityCompat.START, true);
                }
                break;
            case R.id.action_search:
                IntentUtils.intentToSearchVideo(mContext);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    private void initNavigation() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawer.closeDrawers();
            switch (menuItem.getItemId()) {
                case R.id.action_setting:
                    GSYVideoManager.instance().clearAllDefaultCache(mContext);
                    CacheUtils.clearAllCache(getApplicationContext());
                    try {
                        String dataSize = CacheUtils.getTotalCacheSize(getApplicationContext());
                        showToast("缓存/" + dataSize);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.action_about:
                    IntentUtils.intentToDisclaimersActivity(mContext);
                    break;

            }
            return false;
        });
    }

    @Override
    public void returnFail(String message) {
        showToast(message);
    }

    @Override
    public void returnResult(List<ExploreMultiItemBean> list) {
        adapter.setNewData(list);
    }
}
