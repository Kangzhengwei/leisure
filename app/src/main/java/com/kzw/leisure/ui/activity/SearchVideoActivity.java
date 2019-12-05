package com.kzw.leisure.ui.activity;


import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.SearchListAdapter;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.bean.QuerySearchVideoBean;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.contract.SearchVideoContract;
import com.kzw.leisure.model.SearchVideoModel;
import com.kzw.leisure.presenter.SearchVideoPresenter;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.GsonUtil;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.widgets.dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class SearchVideoActivity extends BaseActivity<SearchVideoPresenter, SearchVideoModel> implements SearchVideoContract.View {


    @BindView(R.id.editKey)
    EditText editKey;
    @BindView(R.id.searchRecyclerView)
    RecyclerView searchRecyclerView;

    SearchListAdapter adapter;

    List<SearchItem> itemList = new ArrayList<>();
    ProgressDialog dialog;
    List<QuerySearchVideoBean> queryList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_search_video;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        dialog = new ProgressDialog(this);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SearchListAdapter();
        searchRecyclerView.setAdapter(adapter);
        editKey.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                dialog.show();
                itemList.clear();
                for (QuerySearchVideoBean bean : queryList) {
                    Map<String, String> map = bean.getParams();
                    if (map.containsKey("wd")) {
                        map.put("wd", v.getText().toString());
                    }
                    mPresenter.getHtml(bean);
                }
                return true;
            }
            return false;
        });
        adapter.setOnItemClickListener((adapter, view, position) -> {
            SearchItem item = itemList.get(position);
            IntentUtils.intentToVideoPlay(mContext,item);
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initData() {
        queryList = GsonUtil.getInstance().fromJson(Constant.ruleSource, new TypeToken<List<QuerySearchVideoBean>>() {
        }.getType());
    }

    @Override
    public void returnResult(List<SearchItem> items) {
        dialog.dismiss();
        itemList.addAll(items);
        adapter.setNewData(itemList);
    }

    @Override
    public void returnFail(String message) {
        showToast(message);
    }

}
