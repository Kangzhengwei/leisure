package com.kzw.leisure.ui.activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

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
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.GsonUtil;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.SPUtils;
import com.kzw.leisure.widgets.ChangeSourceDialog;
import com.kzw.leisure.widgets.dialog.ProgressDialog;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SearchBookActivity extends BaseActivity<SearchBookPresenter, SearchBookModel> implements SearchBookContract.View {


    @BindView(R.id.editKey)
    EditText editKey;
    @BindView(R.id.top_bar)
    Toolbar topBar;
    @BindView(R.id.searchRecyclerView)
    RecyclerView searchRecyclerView;
    ProgressDialog dialog;
    List<BookSourceRule> sourceList = new ArrayList<>();
    List<SearchBookBean> bookList = new ArrayList<>();
    SearchBookAdapter adapter;
    BookSourceRule defaultRule;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_book;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setToolbar(topBar);
        setupActionBar(false);
        dialog = new ProgressDialog(this);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SearchBookAdapter();
        searchRecyclerView.setAdapter(adapter);
        editKey.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                dialog.show();
                bookList.clear();
                try {
                    if (defaultRule == null) {
                        for (BookSourceRule rule : sourceList) {
                            Query query = new Query(rule.getRuleSearchUrl(), v.getText().toString(), null, null, rule.getBaseUrl());
                            mPresenter.searchBook(query, rule);
                        }
                    } else {
                        Query query = new Query(defaultRule.getRuleSearchUrl(), v.getText().toString(), null, null, defaultRule.getBaseUrl());
                        mPresenter.searchBook(query, defaultRule);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        });
        adapter.setOnItemClickListener((adapter, view, position) -> {
            SearchBookBean bean = bookList.get(position);
            IntentUtils.intentToBookDetailActivity(mContext, bean);
        });
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
}
