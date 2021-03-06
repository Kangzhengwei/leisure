package com.kzw.leisure.ui.fragment;

import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.BookListAdapter;
import com.kzw.leisure.base.BaseFragment;
import com.kzw.leisure.event.AddBookEvent;
import com.kzw.leisure.realm.BookContentBean;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.realm.SourceRuleRealm;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;


public class BookFragment extends BaseFragment {

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
    public void initWidget() {
        super.initWidget();
        setHasOptionsMenu(true);
        setToolbar(toolbar);
        setupActionBar();
        swipeRefresh.setEnabled(false);
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
    }

    @Override
    protected void initData() {
        realm = Realm.getDefaultInstance();
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
