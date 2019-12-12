package com.kzw.leisure.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.ChapterAdapter;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.bean.ChapterRule;
import com.kzw.leisure.bean.Query;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.model.ReadBookModel;
import com.kzw.leisure.presenter.ReadBookPresenter;
import com.kzw.leisure.realm.BookContentBean;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.realm.ChapterList;
import com.kzw.leisure.realm.SourceRuleRealm;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.StatusBarUtil;
import com.kzw.leisure.widgets.ReadBookChangeSourceDialog;
import com.kzw.leisure.widgets.pageView.BottomMenuWidget;
import com.kzw.leisure.widgets.pageView.PageLoader;
import com.kzw.leisure.widgets.pageView.PageView;
import com.kzw.leisure.widgets.pageView.ReadBookControl;
import com.kzw.leisure.widgets.pageView.TopMenuWidget;
import com.zia.easybook.widget.TxtChapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import io.reactivex.Flowable;

public class ReadBookActivity extends BaseActivity<ReadBookPresenter, ReadBookModel> implements ReadBookContract.View {


    @BindView(R.id.pageView)
    PageView pageView;
    @BindView(R.id.topmenu)
    TopMenuWidget topmenu;
    @BindView(R.id.bottommenu)
    BottomMenuWidget bottommenu;
    @BindView(R.id.menu_layout)
    FrameLayout menuLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.slideLayout)
    LinearLayout slideLayout;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;


    BookRealm bookRealm;//保存的书籍信息
    SourceRuleRealm currentRule;//保存的书籍章节解析规则
    ChapterList currentChapterListUrl;//保存的书籍的章节列表和获取章节列表的path url
    ChapterRule chapterRule;//将保存的章节解析规则转换成对象
    ReadBookControl readBookControl = ReadBookControl.getInstance();
    List<Chapter> chapterList = new ArrayList<>();
    ChapterAdapter adapter;
    Animation menuTopIn, menuTopOut, menuBottomIn, menuBottomOut;
    PageLoader mPageLoader;


    @Override
    protected int getContentView() {
        return R.layout.activity_read_book;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.fullScreen(this);
        StatusBarUtil.StatusBarLightMode(this, true);
        setActionBar(bookRealm.getBookName(), true, topmenu.toolbar);

        drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        menuBottomIn = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_bottom_in);
        menuTopIn = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_top_in);
        menuTopOut = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_top_out);
        menuBottomOut = AnimationUtils.loadAnimation(this, R.anim.anim_readbook_bottom_out);
        menuTopIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                menuLayout.setOnClickListener(view -> menuMiss());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        menuBottomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                menuLayout.setOnClickListener(view -> menuMiss());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        menuTopOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                menuLayout.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        menuBottomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                menuLayout.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter = new ChapterAdapter();
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            drawerlayout.closeDrawer(slideLayout);
            mPageLoader.skipToChapter(position, 0);
        });
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setEnabled(false);
        pageView.setBackground(readBookControl.getTextBackground(mContext));
        mPageLoader = pageView.getPageLoader(this, bookRealm, new PageLoader.Callback() {
            @Override
            public List<Chapter> getChapterList() {
                return chapterList;
            }

            @Override
            public void onChapterChange(int pos) {

            }

            @Override
            public void onCategoryFinish(List<Chapter> chapters) {
                chapterList = chapters;
                adapter.setNewData(chapterList);
                recyclerview.scrollToPosition(bookRealm.getDurChapter());
            }

            @Override
            public void onPageCountChange(int count) {

            }

            @Override
            public void onPageChange(int chapterIndex, int pageIndex, boolean resetReadAloud) {

            }

            @Override
            public void vipPop() {

            }

            @Override
            public Flowable<BookContentBean> getContent(Chapter chapter) {
                return mModel.getContent(chapterRule, chapter);
            }

        });
        pageView.setTouchListener(new PageView.TouchListener() {
            @Override
            public void onTouch() {

            }

            @Override
            public void onTouchClearCursor() {

            }

            @Override
            public void onLongPress() {

            }

            @Override
            public void center() {
                menuShow();
            }
        });
        bottommenu.setListener(new BottomMenuWidget.Callback() {
            @Override
            public void skipToPage(int id) {

            }

            @Override
            public void onMediaButton() {

            }

            @Override
            public void autoPage() {

            }

            @Override
            public void setNightTheme() {

            }

            @Override
            public void skipPreChapter() {

            }

            @Override
            public void skipNextChapter() {

            }

            @Override
            public void openReplaceRule() {

            }

            @Override
            public void openChapterList() {
                getChapterList(true);
                drawerlayout.openDrawer(slideLayout);
                swipeRefresh.setRefreshing(true);
                menuMiss();
            }

            @Override
            public void openAdjust() {

            }

            @Override
            public void openReadInterface() {

            }

            @Override
            public void openMoreSetting() {

            }

            @Override
            public void toast(int id) {

            }

            @Override
            public void dismiss() {
                menuMiss();
            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
        RealmHelper.getInstance().init();
        bookRealm = RealmHelper.getInstance().getBook();
        currentRule = bookRealm.getCurrentRule();
        currentChapterListUrl = bookRealm.getCurrentChapterListRule();
        if (currentRule == null) {
            currentRule = bookRealm.getSourceRuleRealmList().get(0);
        }
        if (currentChapterListUrl == null) {
            currentChapterListUrl = bookRealm.getSearchNoteUrlList().get(0);
        }
        readBookControl.initTextDrawableIndex();
        readBookControl.setPageMode(3);
    }

    @Override
    public void initData() {
        getChapterList(false);
    }

    private void getChapterList(boolean isFromNet) {
        try {
            chapterRule = new ChapterRule(currentRule);//realm不能在子线程调用get或set方法，这里转换成其他对象
            Query query = new Query(currentChapterListUrl.getChapterListUrlRule(), null, chapterRule.getBaseUrl());
            mPresenter.getChapterList(query, chapterRule, currentChapterListUrl, isFromNet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnResult(List<Chapter> list) {
        chapterList = list;
        adapter.setNewData(chapterList);
        mPageLoader.refreshChapterList();
        if (bookRealm.getDurChapter() < list.size()) {
            recyclerview.scrollToPosition(bookRealm.getDurChapter());
        } else {
            recyclerview.scrollToPosition(list.size() - 1);
        }
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }


    @Override
    public void returnFail(String message) {
        showToast(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_change_source:
                menuMiss();
                ReadBookChangeSourceDialog
                        .builder(mContext, currentRule)
                        .setList(bookRealm.getSourceRuleRealmList())
                        .setListener((bean, position) -> {
                            currentRule = bean;
                            currentChapterListUrl = bookRealm.getSearchNoteUrlList().get(position);
                            RealmHelper.getInstance().getRealm().executeTransaction(realm -> {
                                bookRealm.setCurrentRule(currentRule);
                                bookRealm.setCurrentChapterListRule(currentChapterListUrl);
                            });
                            mPageLoader.setStatus(TxtChapter.Status.CHANGE_SOURCE);
                            getChapterList(false);
                        })
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RealmHelper.getInstance().destoryRealm();
        if (mPageLoader != null) {
            mPageLoader.closeBook();
            mPageLoader = null;
        }
    }

    private void menuMiss() {
        menuLayout.setVisibility(View.INVISIBLE);
        topmenu.setVisibility(View.INVISIBLE);
        topmenu.startAnimation(menuTopOut);
        bottommenu.setVisibility(View.INVISIBLE);
        bottommenu.startAnimation(menuBottomOut);
    }

    private void menuShow() {
        menuLayout.setVisibility(View.VISIBLE);
        topmenu.setVisibility(View.VISIBLE);
        topmenu.startAnimation(menuTopIn);
        bottommenu.setVisibility(View.VISIBLE);
        bottommenu.startAnimation(menuBottomIn);
    }
}
