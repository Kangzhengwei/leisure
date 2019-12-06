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
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.contract.ReadBookContract;
import com.kzw.leisure.model.ReadBookModel;
import com.kzw.leisure.presenter.ReadBookPresenter;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.utils.SPUtils;
import com.kzw.leisure.utils.StatusBarUtil;
import com.kzw.leisure.widgets.pageView.BottomMenuWidget;
import com.kzw.leisure.widgets.pageView.PageLoader;
import com.kzw.leisure.widgets.pageView.PageView;
import com.kzw.leisure.widgets.pageView.ReadBookControl;
import com.kzw.leisure.widgets.pageView.TopMenuWidget;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

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
    BookRealm bookRealm;
    BookSourceRule rule;
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
                drawerlayout.openDrawer(slideLayout);
                menuMiss();
            }
        });
        mPageLoader.refreshChapterList();
    }

    @Override
    protected void initPresenter() {
        bookRealm = (BookRealm) getIntent().getSerializableExtra("BookRealm");
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initData() {
        rule = SPUtils.getInstance().getObject("defaultRule", BookSourceRule.class);
        mPresenter.getChapterList(rule, bookRealm);
        readBookControl.initTextDrawableIndex();
        readBookControl.setPageMode(3);
    }

    @Override
    public void returnResult(List<Chapter> list) {
        chapterList = list;
        adapter.setNewData(chapterList);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
