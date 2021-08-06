package com.kzw.leisure.ui.fragment;

import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.BookListAdapter;
import com.kzw.leisure.base.BaseFragment;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.ChapterRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.event.AddBookEvent;
import com.kzw.leisure.model.ReadBookModel;
import com.kzw.leisure.presenter.ReadBookPresenter;
import com.kzw.leisure.realm.BookContentBean;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.realm.ChapterList;
import com.kzw.leisure.realm.SourceRuleRealm;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;


public class BookFragment extends BaseFragment<ReadBookPresenter, ReadBookModel> implements ReadBookContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.book_list)
    RecyclerView bookList;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    Realm realm;
    List<BookRealm> list = new ArrayList<>();
    BookListAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_book;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setHasOptionsMenu(true);
        setToolbar(toolbar);
        setupActionBar();
        swipeRefresh.setEnabled(true);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        bookList.setLayoutManager(new GridLayoutManager(mContext, 3));
        bookList.setItemAnimator(new DefaultItemAnimator());
        adapter = new BookListAdapter();
        bookList.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            BookRealm book = list.get(position);
            RealmHelper.getInstance().setBook(book);
            IntentUtils.intentToBookReadActivity(mContext);
        });
        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            BookRealm book = list.get(position);
            showDialog(book, position);
            return false;
        });
        swipeRefresh.setOnRefreshListener(this::referData);
    }

    @Override
    protected void initData() {
        realm = RealmHelper.getInstance().getRealm();
        list = realm.where(BookRealm.class).findAll();
        adapter.setNewData(list);
        RxBus.getInstance().toObservable(this, AddBookEvent.class).subscribe(new Observer<AddBookEvent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AddBookEvent addBookEvent) {
                list = realm.where(BookRealm.class).findAll();
                adapter.setNewData(list);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        referData();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.book_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                IntentUtils.intentToSearchBook(mContext);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void referData() {
        if (list.size() > 0) {
            swipeRefresh.setRefreshing(true);
        }
        int position = 0;
        for (BookRealm bookRealm : list) {
            SourceRuleRealm currentRule = bookRealm.getCurrentRule();
            ChapterList currentChapterListUrl = bookRealm.getCurrentChapterListRule();
            if (currentRule == null) {
                currentRule = bookRealm.getSourceRuleRealmList().get(0);
            }
            if (currentChapterListUrl == null) {
                currentChapterListUrl = bookRealm.getSearchNoteUrlList().get(0);
            }
            realm.executeTransaction(realm -> bookRealm.setRefresh(true));
            getChapterList(true, currentRule, currentChapterListUrl, position);
            position++;
        }
        adapter.notifyDataSetChanged();
    }


    private void getChapterList(boolean isFromNet, SourceRuleRealm currentRule, ChapterList currentChapterListUrl, int position) {
        try {
            ChapterRule chapterRule = new ChapterRule(currentRule);//realm不能在子线程调用get或set方法，这里转换成其他对象
            Query query = new Query(currentChapterListUrl.getChapterListUrlRule(), null, chapterRule.getBaseUrl());
            mPresenter.getChapterList(query, chapterRule, currentChapterListUrl, isFromNet, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnResult(List<Chapter> list, int position) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        BookRealm bookRealm = this.list.get(position);
        realm.executeTransaction(realm -> bookRealm.setRefresh(false));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void returnFail(String message, int position) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        BookRealm bookRealm = this.list.get(position);
        realm.executeTransaction(realm -> bookRealm.setRefresh(false));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void returnFail(String message) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        showToast(message);
    }

    private void showDialog(BookRealm book, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("是否删除这本书！");
        builder.setPositiveButton("确定", (dialogInterface, i) -> realm.executeTransaction(realm -> {
            List<SourceRuleRealm> sourceList = book.getSourceRuleRealmList();
            for (SourceRuleRealm ruleRealm : sourceList) {
                RealmResults<BookContentBean> realmResults = realm.where(BookContentBean.class).equalTo("tag", ruleRealm.getBaseUrl()).findAll();
                if (realmResults != null && realmResults.size() > 0) {
                    for (BookContentBean bean : realmResults) {
                        bean.deleteFromRealm();
                    }
                }
            }
            book.deleteFromRealm();
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeRemoved(position, list.size() - position);
        }));
        builder.setNegativeButton("取消", (dialogInterface, i) -> {
        });
        builder.create().show();
    }

}
