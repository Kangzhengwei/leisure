package com.kzw.leisure.ui.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.SearchBookAdapter;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.SearchBookContract;
import com.kzw.leisure.model.SearchBookModel;
import com.kzw.leisure.presenter.SearchBookPresenter;
import com.kzw.leisure.realm.HistoryKeyWordRealm;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.GsonUtil;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.SPUtils;
import com.kzw.leisure.widgets.ChangeSourceDialog;
import com.kzw.leisure.widgets.WordWrapView;
import com.kzw.leisure.widgets.dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmList;

public class SearchBookActivity extends BaseActivity<SearchBookPresenter, SearchBookModel> implements SearchBookContract.View {


    @BindView(R.id.editKey)
    EditText editKey;
    @BindView(R.id.top_bar)
    Toolbar topBar;
    @BindView(R.id.searchRecyclerView)
    RecyclerView searchRecyclerView;
    @BindView(R.id.historyWrapperLayout)
    LinearLayout wrapLayout;
    @BindView(R.id.iamge_clear)
    ImageView imageClear;
    @BindView(R.id.historyWordWrapView)
    WordWrapView wrapView;

    ProgressDialog dialog;
    List<BookSourceRule> sourceList = new ArrayList<>();
    List<SearchBookBean> bookList = new ArrayList<>();
    SearchBookAdapter adapter;
    BookSourceRule defaultRule;
    Realm realm;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_book;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setToolbar(topBar);
        setupActionBar(false);
        realm = RealmHelper.getInstance().getRealm();
        dialog = new ProgressDialog(this);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SearchBookAdapter();
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
            SearchBookBean bean = bookList.get(position);
            IntentUtils.intentToBookDetailActivity(mContext, bean);
        });
        getHistoryKey();
        imageClear.setOnClickListener(view -> {
            List<HistoryKeyWordRealm> keyWordRealmList = realm.where(HistoryKeyWordRealm.class).findAll();
            if (keyWordRealmList != null && keyWordRealmList.size() > 0) {
                for (HistoryKeyWordRealm historyKeyWordRealm : keyWordRealmList) {
                    if (historyKeyWordRealm.getType() == 1) {
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
        bookList.clear();
        try {
            if (defaultRule == null) {
                for (BookSourceRule rule : sourceList) {
                    Query query = new Query(rule.getRuleSearchUrl(), keyword, null, null, rule.getBaseUrl());
                    mPresenter.searchBook(query, rule);
                }
            } else {
                Query query = new Query(defaultRule.getRuleSearchUrl(), keyword, null, null, defaultRule.getBaseUrl());
                mPresenter.searchBook(query, defaultRule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveKeyWord(keyword);
    }

    private void saveKeyWord(String keyWord) {
        List<HistoryKeyWordRealm> keyWordRealmList = realm.where(HistoryKeyWordRealm.class).findAll();
        if (keyWordRealmList != null && keyWordRealmList.size() > 0) {
            boolean isHas = false;
            for (HistoryKeyWordRealm historyKeyWordRealm : keyWordRealmList) {
                if (historyKeyWordRealm.getType() == 1) {
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
                    historyKeyWordRealm.setType(1);
                    historyKeyWordRealm.getStringRealmList().add(keyWord);
                });
            }
        } else {
            realm.executeTransaction(realm -> {
                HistoryKeyWordRealm historyKeyWordRealm = realm.createObject(HistoryKeyWordRealm.class);
                historyKeyWordRealm.setType(1);
                historyKeyWordRealm.getStringRealmList().add(keyWord);
            });
        }
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initData() {
        sourceList = GsonUtil.getInstance().fromJson(Constant.bookRuleSource, new TypeToken<List<BookSourceRule>>() {
        }.getType());
        defaultRule = SPUtils.getInstance().getObject("defaultRule", BookSourceRule.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_book_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.change_source:
                ChangeSourceDialog.builder(mContext)
                        .setList(sourceList)
                        .setListener(new ChangeSourceDialog.itemClickListener() {
                            @Override
                            public void itemClick(BookSourceRule bean) {
                                defaultRule = bean;
                                SPUtils.getInstance().putObject("defaultRule", bean);
                            }

                            @Override
                            public void allRule() {
                                defaultRule = null;
                            }
                        })
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void returnResult(List<SearchBookBean> list) {
        dialog.dismiss();
        wrapLayout.setVisibility(View.GONE);
        searchRecyclerView.setVisibility(View.VISIBLE);
        bookList.addAll(list);
        Set<SearchBookBean> set = new LinkedHashSet<>(bookList);
        bookList.clear();
        bookList.addAll(set);
        adapter.setNewData(bookList);
    }

    @Override
    public void returnFail(String message) {
        dialog.dismiss();
        showToast(message);
    }

    private void getHistoryKey() {
        List<HistoryKeyWordRealm> keyWordRealmList = realm.where(HistoryKeyWordRealm.class).findAll();
        if (keyWordRealmList != null && keyWordRealmList.size() > 0) {
            for (HistoryKeyWordRealm historyKeyWordRealm : keyWordRealmList) {
                if (historyKeyWordRealm.getType() == 1) {
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
}
