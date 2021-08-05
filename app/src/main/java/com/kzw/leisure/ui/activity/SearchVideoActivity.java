package com.kzw.leisure.ui.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.SearchListAdapter;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.bean.QuerySearchVideoBean;
import com.kzw.leisure.bean.SearchItem;
import com.kzw.leisure.bean.VideoSearchBean;
import com.kzw.leisure.contract.SearchVideoContract;
import com.kzw.leisure.model.SearchVideoModel;
import com.kzw.leisure.presenter.SearchVideoPresenter;
import com.kzw.leisure.realm.HistoryKeyWordRealm;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.GsonUtil;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.widgets.WordWrapView;
import com.kzw.leisure.widgets.dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmList;


public class SearchVideoActivity extends BaseActivity<SearchVideoPresenter, SearchVideoModel> implements SearchVideoContract.View {


    @BindView(R.id.editKey)
    EditText editKey;
    @BindView(R.id.searchRecyclerView)
    RecyclerView searchRecyclerView;
    @BindView(R.id.historyWrapperLayout)
    LinearLayout wrapLayout;
    @BindView(R.id.iamge_clear)
    ImageView imageClear;
    @BindView(R.id.historyWordWrapView)
    WordWrapView wrapView;

    SearchListAdapter adapter;
    Realm realm;
    List<SearchItem> itemList = new ArrayList<>();
    ProgressDialog dialog;
    List<QuerySearchVideoBean> queryList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_search_video;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        realm = RealmHelper.getInstance().getRealm();
        dialog = new ProgressDialog(this);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SearchListAdapter();
        searchRecyclerView.setAdapter(adapter);
        editKey.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(v.getText().toString());
                return true;
            }
            return false;
        });
        editKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==0){
                    getHistoryKey();
                    wrapLayout.setVisibility(View.VISIBLE);
                    searchRecyclerView.setVisibility(View.GONE);
                }
            }
        });
        adapter.setOnItemClickListener((adapter, view, position) -> {
            SearchItem item = itemList.get(position);
            IntentUtils.intentToVideoPlay(mContext, item);
        });
        getHistoryKey();
        imageClear.setOnClickListener(view -> {
            List<HistoryKeyWordRealm> keyWordRealmList = realm.where(HistoryKeyWordRealm.class).findAll();
            if (keyWordRealmList != null && keyWordRealmList.size() > 0) {
                for (HistoryKeyWordRealm historyKeyWordRealm : keyWordRealmList) {
                    if (historyKeyWordRealm.getType() == 0) {
                        RealmList<String> realmList = historyKeyWordRealm.getStringRealmList();
                        realm.executeTransaction(realm -> realmList.clear());
                        break;
                    }
                }
            }
            wrapView.removeAllViews();
        });
    }

    private void search(String keyword) {
        dialog.show();
        itemList.clear();
        for (QuerySearchVideoBean bean : queryList) {
            try {
                Query query = new Query(bean.getRuleSearchUrl(), keyword, null, null, bean.getVideoSourceUrl());
                mPresenter.getHtml(query, bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mPresenter.getList(Constant.QUERY_SEARCH.replace("KEYWORD",keyword));
        saveKeyWord(keyword);
    }


    private void saveKeyWord(String keyWord) {
        List<HistoryKeyWordRealm> keyWordRealmList = realm.where(HistoryKeyWordRealm.class).findAll();
        if (keyWordRealmList != null && keyWordRealmList.size() > 0) {
            boolean isHas = false;
            for (HistoryKeyWordRealm historyKeyWordRealm : keyWordRealmList) {
                if (historyKeyWordRealm.getType() == 0) {
                    isHas = true;
                    RealmList<String> list = historyKeyWordRealm.getStringRealmList();
                    if (list.size() < 10) {
                        int a = 0;
                        for (String str : list) {
                            if (str.equals(keyWord)) {
                                int finalA = a;
                                realm.executeTransaction(realm -> list.deleteFromRealm(finalA));
                                break;
                            }
                            a++;
                        }
                    } else {
                        realm.executeTransaction(realm -> list.deleteFromRealm(0));
                        int a = 0;
                        for (String str : list) {
                            if (str.equals(keyWord)) {
                                int finalA = a;
                                realm.executeTransaction(realm -> list.deleteFromRealm(finalA));
                                break;
                            }
                            a++;
                        }
                    }
                    realm.executeTransaction(realm -> list.add(keyWord));
                }
            }
            if (!isHas) {
                realm.executeTransaction(realm -> {
                    HistoryKeyWordRealm historyKeyWordRealm = realm.createObject(HistoryKeyWordRealm.class);
                    historyKeyWordRealm.setType(0);
                    historyKeyWordRealm.getStringRealmList().add(keyWord);
                });
            }
        } else {
            realm.executeTransaction(realm -> {
                HistoryKeyWordRealm historyKeyWordRealm = realm.createObject(HistoryKeyWordRealm.class);
                historyKeyWordRealm.setType(0);
                historyKeyWordRealm.getStringRealmList().add(keyWord);
            });
        }
    }

    private void getHistoryKey() {
        List<HistoryKeyWordRealm> keyWordRealmList = realm.where(HistoryKeyWordRealm.class).findAll();
        if (keyWordRealmList != null && keyWordRealmList.size() > 0) {
            for (HistoryKeyWordRealm historyKeyWordRealm : keyWordRealmList) {
                if (historyKeyWordRealm.getType() == 0) {
                    wrapView.removeAllViews();
                    RealmList<String> realmList = historyKeyWordRealm.getStringRealmList();
                    List<String> list = new ArrayList<>(realmList);
                    Collections.reverse(list);
                    for (String str : list) {
                        View view = LayoutInflater.from(mContext).inflate(R.layout.history_keyword_item, null, false);
                        TextView textView = view.findViewById(R.id.key_word_view);
                        textView.setText(str);
                        textView.setOnClickListener(view1 -> {
                            editKey.setText(str);
                            search(str);
                        });
                        wrapView.addView(textView);
                    }
                    break;
                }
            }
        }
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
        wrapLayout.setVisibility(View.GONE);
        searchRecyclerView.setVisibility(View.VISIBLE);
        itemList.addAll(items);
        adapter.setNewData(itemList);
    }

    @Override
    public void returnSearch(List<SearchItem> list) {
        dialog.dismiss();
        wrapLayout.setVisibility(View.GONE);
        searchRecyclerView.setVisibility(View.VISIBLE);
        itemList.addAll(list);
        adapter.setNewData(itemList);
    }

    @Override
    public void returnFail(String message) {
        showToast(message);
    }

}
