package com.kzw.leisure.ui.fragment;

import android.content.res.Configuration;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.WebSiteAdapter;
import com.kzw.leisure.base.BaseWebViewFragment;
import com.kzw.leisure.event.WebCollectEvent;
import com.kzw.leisure.realm.CollectDataBean;
import com.kzw.leisure.realm.CollectDataList;
import com.kzw.leisure.realm.WebSiteBean;
import com.kzw.leisure.realm.WebSiteList;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.utils.CacheUtils;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.DimenUtil;
import com.kzw.leisure.utils.GsonUtil;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.PermessionUtil;
import com.kzw.leisure.widgets.dialog.AddWebSiteDialog;
import com.kzw.leisure.widgets.popwindow.SiteOperationMenu;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.realm.Realm;


public class MovieFragment extends BaseWebViewFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.slide_menu)
    RelativeLayout slideMenu;

    private ActionBarDrawerToggle mDrawerToggle;
    private Realm realm;
    private List<WebSiteBean> list = new ArrayList<>();
    private WebSiteAdapter adapter;

    @Override
    public void initWidget() {
        super.initWidget();
        setHasOptionsMenu(true);
        setToolbar(toolbar);
        setupActionBar(false);
        mDrawerToggle = new ActionBarDrawerToggle(mActivity, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        drawer.addDrawerListener(mDrawerToggle);
        loadUrl(Constant.DEFAULT_URL);
    }

    @Override
    protected void initData() {
        realm = Realm.getDefaultInstance();
        initRecyclerView();
        initList();
    }

    @Override
    public void onResume() {
        super.onResume();
        PermessionUtil.checkPermession(getActivity());
    }

    private void initList() {
        WebSiteList realmList = realm.where(WebSiteList.class).findFirst();
        if (realmList != null) {
            list = realmList.getWebSiteBeanRealmList();
        } else {
            realm.executeTransaction(realm -> {
                List<WebSiteBean> sitelist = GsonUtil.getInstance().fromJson(Constant.webSite, new TypeToken<List<WebSiteBean>>() {
                }.getType());
                WebSiteList data = realm.createObject(WebSiteList.class);
                data.setWebSiteBeanRealmList(sitelist);
                list = data.getWebSiteBeanRealmList();
            });
        }
        adapter.addData(list);
    }

    public void initRecyclerView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter = new WebSiteAdapter();
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            WebSiteBean bean = list.get(position);
            loadUrl(bean.getUrl());
            drawer.closeDrawers();
        });
        adapter.setMenuClickListener((v, bean, position) -> {
            SiteOperationMenu pop = new SiteOperationMenu(mContext);
            if (position == list.size() - 1) {
                pop.showAsDropDown(v, DimenUtil.dip2px(mContext, -100), DimenUtil.dip2px(mContext, -80), Gravity.BOTTOM | Gravity.START);
            } else {
                pop.showAsDropDown(v, DimenUtil.dip2px(mContext, -100), 0, Gravity.BOTTOM | Gravity.START);
            }
            pop.setMenuClickListener(new SiteOperationMenu.menuClickListener() {
                @Override
                public void delete() {
                    realm.executeTransaction(realm -> list.remove(bean));
                    adapter.setNewData(list);
                }

                @Override
                public void moveTop() {
                    realm.executeTransaction(realm -> {
                        list.remove(bean);
                        list.add(0, bean);
                    });
                    adapter.setNewData(list);
                }
            });
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
            case R.id.custom_add_website:
                AddWebSiteDialog dialog = new AddWebSiteDialog(mContext);
                dialog.show();
                dialog.setClickListener((site, url) -> realm.executeTransaction(realm -> {
                    WebSiteBean bean = realm.createObject(WebSiteBean.class);
                    bean.setSiteName(site);
                    bean.setUrl(url);
                    WebSiteList data = realm.where(WebSiteList.class).findFirst();
                    if (data != null) {
                        data.getWebSiteBeanRealmList().add(bean);
                        showToast("添加成功");
                        refrshData();
                    }
                }));
                break;
            case R.id.action_add_to_site:
                realm.executeTransaction(realm -> {
                    WebSiteBean bean = realm.createObject(WebSiteBean.class);
                    bean.setSiteName(webTitle);
                    bean.setUrl(webUrl);
                    WebSiteList data = realm.where(WebSiteList.class).findFirst();
                    if (data != null) {
                        data.getWebSiteBeanRealmList().add(bean);
                        showToast("添加成功");
                        refrshData();
                    }
                });
                break;
            case R.id.action_add_to_collect:
                realm.executeTransaction(realm -> {
                    boolean ishas = false;
                    List<CollectDataBean> list = realm.where(CollectDataBean.class).findAll();
                    if (list != null && list.size() > 0) {
                        for (CollectDataBean bean : list) {
                            if (bean.getUrl().equals(webUrl)) {
                                ishas = true;
                                showToast("该链接已收藏");
                                break;
                            }
                        }
                    }
                    if (!ishas) {
                        CollectDataBean data = realm.createObject(CollectDataBean.class);
                        data.setUrl(webUrl);
                        data.setUrlTitle(webTitle);
                        showToast("收藏成功");
                        CollectDataList dataList = realm.where(CollectDataList.class).findFirst();
                        if (dataList != null) {
                            dataList.getCollectList().add(data);
                        } else {
                            CollectDataList newlist = realm.createObject(CollectDataList.class);
                            newlist.getCollectList().add(data);
                        }
                        RxBus.getInstance().post(new WebCollectEvent());
                    }
                });
                break;
            case R.id.action_clear_cache:
                GSYVideoManager.instance().clearAllDefaultCache(mContext);
                CacheUtils.clearAllCache(getApplicationContext());
                try {
                    String dataSize = CacheUtils.getTotalCacheSize(getApplicationContext());
                    showToast("缓存/" + dataSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.action_donate:
                IntentUtils.intentToDonateActivity(mContext);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        if (webview != null) {//解决Receiver not registered: android.widget.ZoomButtonsController
            webview.getSettings().setBuiltInZoomControls(true);
            webview.setVisibility(View.GONE);// 把destroy()延后
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void refrshData() {
        WebSiteList realmList = realm.where(WebSiteList.class).findFirst();
        if (realmList != null) {
            list = realmList.getWebSiteBeanRealmList();
            adapter.setNewData(list);
        }
    }
}
