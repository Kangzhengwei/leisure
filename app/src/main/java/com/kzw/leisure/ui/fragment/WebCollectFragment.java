package com.kzw.leisure.ui.fragment;

import android.content.Intent;
import android.view.Gravity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.WebSiteCollectAdapter;
import com.kzw.leisure.base.BaseFragment;
import com.kzw.leisure.realm.CollectDataBean;
import com.kzw.leisure.realm.CollectDataList;
import com.kzw.leisure.constant.Constant;
import com.kzw.leisure.utils.DimenUtil;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.widgets.popwindow.SiteOperationMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.Realm;

/**
 * author: kang4
 * Date: 2019/12/3
 * Description:
 */
public class WebCollectFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private WebSiteCollectAdapter adapter;
    private Realm realm;
    private List<CollectDataBean> list = new ArrayList<>();

    public static Fragment newInstance() {
        return new WebCollectFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect_web;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        realm = RealmHelper.getInstance().getRealm();
        initView();
        initList();
    }

    @Override
    public void initVariables() {
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new WebSiteCollectAdapter();
        recyclerView.setAdapter(adapter);
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
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra("url", list.get(position).getUrl());
            mActivity.setResult(Constant.ACTIVITY_RESULT_CODE, intent);
            mActivity.finish();
        });
    }

    public void initList() {
        CollectDataList realmList = realm.where(CollectDataList.class).findFirst();
        if (realmList != null) {
            list = realmList.getCollectList();
        }
        adapter.setNewData(list);
    }

}
