package com.kzw.leisure.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.ChapterAdapter;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.bean.BookBean;
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.SearchBookBean;
import com.kzw.leisure.contract.BookDetailContract;
import com.kzw.leisure.event.AddBookEvent;
import com.kzw.leisure.model.BookDetailModel;
import com.kzw.leisure.presenter.BookDetailPresenter;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.GlideUtil;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.StatusBarUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class BookDetailActivity extends BaseActivity<BookDetailPresenter, BookDetailModel> implements BookDetailContract.View {


    @BindView(R.id.bg_shape)
    ImageView bgShape;
    @BindView(R.id.book_bg)
    RoundedImageView bookBg;
    @BindView(R.id.bookName)
    TextView bookName;
    @BindView(R.id.booklastUpdateChapter)
    TextView booklastUpdateChapter;
    @BindView(R.id.bookauthor)
    TextView bookauthor;
    @BindView(R.id.booklastUpdateTime)
    TextView booklastUpdateTime;
    @BindView(R.id.booksite)
    TextView booksite;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.catalogintro)
    TextView catalogintro;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.catalog_rv)
    RecyclerView catalogRv;
    @BindView(R.id.add)
    FloatingActionButton add;
    SearchBookBean searchBookBean;
    BookSourceRule sourceRule;
    ChapterAdapter adapter;
    Realm realm;
    BookBean mBook;

    @Override
    protected int getContentView() {
        return R.layout.activity_book_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        StatusBarUtil.translucentBar(this);
        setToolbar(toolbar);
        getSupportActionBar().setTitle(searchBookBean.getSearchName());
        toolbar.setNavigationOnClickListener(view -> finish());
        toolbarLayout.setContentScrimColor(AppUtils.getColor(R.color.colorPrimaryDark));
        bookName.setText(searchBookBean.getSearchName());
        bookauthor.setText(searchBookBean.getSearchAuthor());
        booklastUpdateChapter.setText(searchBookBean.getSearchLastChapter());
        catalogintro.setText(searchBookBean.getSearchIntroduce());
        GlideUtil.setBookImagesource(mContext, searchBookBean.getSearchCoverUrl(), bookBg);
        GlideUtil.setBlurSource(mContext, searchBookBean.getSearchCoverUrl(), bgShape);
        catalogRv.setLayoutManager(new LinearLayoutManager(this));
        catalogRv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ChapterAdapter();
        catalogRv.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Object object = adapter.getData().get(position);
            if (object instanceof Chapter) {
                Chapter chapter = (Chapter) object;
                BookRealm book = new BookRealm();
                book.setBookName(mBook.getBookName());
                book.setBookAuthor(mBook.getBookAuthor());
                book.setChapterListUrl(searchBookBean.getSearchNoteUrl());
                book.setDurChapterName(chapter.getChapterName());
                book.setDurChapterUrl(chapter.getChapterUrl());
                book.setDurChapter(position);
                book.setDurChapterPage(0);
                book.setChapterListSize(adapter.getData().size());
                RealmHelper.getInstance().setBook(book);
                IntentUtils.intentToBookReadActivity(mContext);
            }
        });
    }

    @Override
    protected void initPresenter() {
        searchBookBean = (SearchBookBean) getIntent().getSerializableExtra("SearchBook");
        sourceRule = (BookSourceRule) getIntent().getSerializableExtra("BookRule");
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initData() {
        mPresenter.getHtml(sourceRule, searchBookBean);
    }


    @OnClick(R.id.add)
    public void onViewClicked() {
        realm.executeTransaction(realm -> {
            if (mBook == null) {
                return;
            }
            boolean isHas = false;
            List<BookRealm> list = realm.where(BookRealm.class).findAll();
            for (BookRealm item : list) {
                if (item.getBookName().equals(mBook.getBookName()) && item.getBookAuthor().equals(mBook.getBookAuthor())) {
                    isHas = true;
                    showToast("已添加");
                    break;
                }
            }
            if (!isHas) {
                BookRealm data = realm.createObject(BookRealm.class);
                data.setBookAuthor(mBook.getBookAuthor());
                data.setBookContent(mBook.getBookContent());
                data.setBookInfoInit(mBook.getBookInfoInit());
                data.setBookKind(mBook.getBookKind());
                data.setBookLastChapter(mBook.getBookLastChapter());
                data.setBookName(mBook.getBookName());
                data.setBookUrlPattern(mBook.getBookUrlPattern());
                data.setCoverUrl(sourceRule.getBaseUrl() + mBook.getCoverUrl());
                data.setChapterListUrl(searchBookBean.getSearchNoteUrl());
                showToast("添加成功");
                RxBus.getInstance().post(new AddBookEvent());
            }
        });

    }

    @Override
    public void returnResult(BookBean bookBean) {
        mBook = bookBean;
        tvIntro.setText(bookBean.getBookInfoInit());
        GlideUtil.setBookImagesource(mContext, sourceRule.getBaseUrl() + bookBean.getCoverUrl(), bookBg);
        GlideUtil.setBlurSource(mContext, sourceRule.getBaseUrl() + bookBean.getCoverUrl(), bgShape);
        adapter.setNewData(bookBean.getList());
    }

    @Override
    public void returnFail(String message) {
        showToast(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
