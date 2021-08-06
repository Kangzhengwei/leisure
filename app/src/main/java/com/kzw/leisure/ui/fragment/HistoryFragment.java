package com.kzw.leisure.ui.fragment;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.HistoryAdapter;
import com.kzw.leisure.base.BaseFragment;
import com.kzw.leisure.realm.HistoryDataBean;
import com.kzw.leisure.realm.HistoryDataList;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.RealmHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import io.realm.Realm;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private HistoryAdapter adapter;
    private Realm realm;
    private List<HistoryDataBean> list = new ArrayList<>();


    public static Fragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void initVariables() {
        super.initVariables();
        realm = RealmHelper.getInstance().getRealm();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra("url", list.get(position).getUrl());
            mActivity.setResult(Constant.ACTIVITY_RESULT_CODE, intent);
            mActivity.finish();
        });
    }

    @Override
    protected void initData() {
        super.initData();
        HistoryDataList realmList = realm.where(HistoryDataList.class).findFirst();
        if (realmList != null) {
            list = realmList.getHistoryDataBeanRealmList();
        }
        realm.executeTransaction(realm -> Collections.reverse(list));
        adapter.setNewData(list);
    }
}
