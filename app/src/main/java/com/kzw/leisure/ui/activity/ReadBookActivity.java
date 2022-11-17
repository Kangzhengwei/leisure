package com.kzw.leisure.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.kzw.leisure.utils.AdMobUtils;
import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.LogUtils;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.StatusBarUtil;
import com.kzw.leisure.utils.SystemUtil;
import com.kzw.leisure.widgets.AdjustMenu;
import com.kzw.leisure.widgets.ReadBookChangeSourceDialog;
import com.kzw.leisure.widgets.SettingMenu;
import com.kzw.leisure.widgets.UISettingMenu;
import com.kzw.leisure.widgets.VerticalSeekBar;
import com.kzw.leisure.widgets.anim.PageAnimation;
import com.kzw.leisure.widgets.pageView.BottomMenuWidget;
import com.kzw.leisure.widgets.pageView.PageLoader;
import com.kzw.leisure.widgets.pageView.PageView;
import com.kzw.leisure.widgets.pageView.ReadBookControl;
import com.kzw.leisure.widgets.pageView.TopMenuWidget;
import com.kzw.leisure.widgets.pageView.TxtChapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.adjust_menu)
    AdjustMenu adjustMenu;
    @BindView(R.id.setting_menu)
    SettingMenu settingMenu;
    @BindView(R.id.ui_setting_menu)
    UISettingMenu uiSettingMenu;
    @BindView(R.id.vertical_seekbar)
    VerticalSeekBar verticalSeekbar;


    BookRealm bookRealm;//保存的书籍信息
    SourceRuleRealm currentRule;//保存的书籍章节解析规则
    ChapterList currentChapterListUrl;//保存的书籍的章节列表和获取章节列表的path url
    ChapterRule chapterRule;//将保存的章节解析规则转换成对象
    ReadBookControl readBookControl = ReadBookControl.getInstance();
    List<Chapter> chapterList = new ArrayList<>();
    ChapterAdapter adapter;
    Animation menuTopIn, menuTopOut, menuBottomIn, menuBottomOut;
    PageLoader mPageLoader;
    int screenTimeOut;
    Runnable keepScreenRunnable;
    private int currentChapterIndex;

    @Override
    protected int getContentView() {
        return R.layout.activity_read_book;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.fullScreen(this);
        StatusBarUtil.StatusBarLightMode(this, true);
        setActionBar(bookRealm.getBookName(), true, topmenu.toolbar);
        keepScreenRunnable = this::unKeepScreenOn;
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
                allMenuMiss();
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
                allMenuMiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        adjustMenu.initData(this);
        settingMenu.setListener(new SettingMenu.Callback() {
            @Override
            public void upBar() {

            }

            @Override
            public void keepScreenOnChange(int keepScreenOn) {
                screenTimeOut = getResources().getIntArray(R.array.screen_time_out_value)[keepScreenOn];
                screenOffTimerStart();
            }

            @Override
            public void recreate() {
                ReadBookActivity.this.recreate();
            }

            @Override
            public void refreshPage() {
                mPageLoader.refreshUi();
            }
        });
        uiSettingMenu.setListener(this, new UISettingMenu.Callback() {
            @Override
            public void upPageMode() {
                if (mPageLoader != null) {
                    mPageLoader.setPageMode(PageAnimation.Mode.getPageMode(readBookControl.getPageMode()));
                }
            }

            @Override
            public void upTextSize() {
                if (mPageLoader != null) {
                    mPageLoader.setTextSize();
                }
            }

            @Override
            public void bgChange() {
                readBookControl.initTextDrawableIndex();
                pageView.setBackground(readBookControl.getTextBackground(ReadBookActivity.this));
                if (mPageLoader != null) {
                    mPageLoader.refreshUi();
                }
            }

            @Override
            public void refresh() {
                if (mPageLoader != null) {
                    mPageLoader.refreshUi();
                }
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
                if (bookRealm.getChapterListSize() == 1) {
                    bottommenu.setTvPre(false);
                    bottommenu.setTvNext(false);
                } else {
                    if (pos == 0) {
                        bottommenu.setTvPre(false);
                        bottommenu.setTvNext(true);
                    } else if (pos == bookRealm.getChapterListSize() - 1) {
                        bottommenu.setTvPre(true);
                        bottommenu.setTvNext(false);
                    } else {
                        bottommenu.setTvPre(true);
                        bottommenu.setTvNext(true);
                    }
                }
            }

            @Override
            public void onCategoryFinish(List<Chapter> chapters) {
                chapterList = chapters;
                adapter.setNewData(chapterList);
                recyclerview.scrollToPosition(bookRealm.getDurChapter());
            }

            @Override
            public void onPageCountChange(int count) {
                bottommenu.getReadProgress().setMax(Math.max(0, count - 1));
                bottommenu.getReadProgress().setProgress(0);
                // 如果处于错误状态，那么就冻结使用
                bottommenu.getReadProgress().setEnabled(mPageLoader.getPageStatus() != TxtChapter.Status.LOADING && mPageLoader.getPageStatus() != TxtChapter.Status.ERROR);
            }

            @Override
            public void onPageChange(int chapterIndex, int pageIndex, boolean resetReadAloud) {
                bottommenu.getReadProgress().post(() -> bottommenu.getReadProgress().setProgress(pageIndex));
                if (currentChapterIndex != chapterIndex) {
                    currentChapterIndex = chapterIndex;
                    AdMobUtils.getInstance().loadFullScreenAd((Activity) mContext, () -> StatusBarUtil.fullScreen((Activity) mContext));
                }
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
                mPageLoader.skipToPage(id);
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
                mPageLoader.skipPreChapter();
            }

            @Override
            public void skipNextChapter() {
                mPageLoader.skipNextChapter();
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
                menuMiss();
                AppUtils.runOnUIDelayed(ReadBookActivity.this::adjustMenuShow, menuBottomOut.getDuration() + 100);
            }

            @Override
            public void openReadInterface() {
                menuMiss();
                AppUtils.runOnUIDelayed(ReadBookActivity.this::uiSettingMenuShow, menuBottomOut.getDuration() + 100);
            }

            @Override
            public void openMoreSetting() {
                menuMiss();
                AppUtils.runOnUIDelayed(ReadBookActivity.this::settingMenuShow, menuBottomOut.getDuration() + 100);
            }

            @Override
            public void toast(int id) {

            }

            @Override
            public void dismiss() {
                menuMiss();
            }
        });
        verticalSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i > 0) {
                    double result = new BigDecimal((double) i / (double) 100).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
                    recyclerview.scrollToPosition(chapterList.size() - Double.valueOf(result * chapterList.size()).intValue());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        AppUtils.removeRunnable(ReadBookActivity.this::scrollbarDismiss);
                        if (!isFinishing() && verticalSeekbar.getVisibility() == View.INVISIBLE) {
                            verticalSeekbar.setVisibility(View.VISIBLE);
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (!isFinishing() && !verticalSeekbar.isSelect()) {
                            AppUtils.runOnUIDelayed(ReadBookActivity.this::scrollbarDismiss, 2000);
                        }
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int range = recyclerView.computeVerticalScrollRange();
                int offset = recyclerView.computeVerticalScrollOffset();
                if (!isFinishing() && !verticalSeekbar.isSelect()) {
                    double result = new BigDecimal((double) offset / (double) range).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    verticalSeekbar.setProgress(100 - Double.valueOf(result * 100).intValue());
                }
            }
        });
    }

    private void scrollbarDismiss() {
        verticalSeekbar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
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
        currentChapterIndex = bookRealm.getDurChapter();
        getChapterList(false);
    }

    private void getChapterList(boolean isFromNet) {
        try {
            chapterRule = new ChapterRule(currentRule);//realm不能在子线程调用get或set方法，这里转换成其他对象
            LogUtils.d(chapterRule.toString());
            Query query = new Query(currentChapterListUrl.getChapterListUrlRule(), null, chapterRule.getBaseUrl());
            mPresenter.getChapterList(query, chapterRule, currentChapterListUrl, isFromNet, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnResult(List<Chapter> list, int position) {
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
    public void returnFail(String message, int position) {

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
        AppUtils.removeRunnable(this::scrollbarDismiss);
        if (mPageLoader != null) {
            mPageLoader.closeBook();
            mPageLoader = null;
        }
    }

    private void menuMiss() {
        if (menuLayout.getVisibility() == View.VISIBLE) {
            if (topmenu.getVisibility() == View.VISIBLE) {
                topmenu.startAnimation(menuTopOut);
            }
            if (bottommenu.getVisibility() == View.VISIBLE) {
                bottommenu.startAnimation(menuBottomOut);
            }
            if (adjustMenu.getVisibility() == View.VISIBLE) {
                adjustMenu.startAnimation(menuBottomOut);
            }
            if (settingMenu.getVisibility() == View.VISIBLE) {
                settingMenu.startAnimation(menuBottomOut);
            }
            if (uiSettingMenu.getVisibility() == View.VISIBLE) {
                uiSettingMenu.startAnimation(menuBottomOut);
            }
        }
    }

    private void allMenuMiss() {
        menuLayout.setVisibility(View.INVISIBLE);
        topmenu.setVisibility(View.INVISIBLE);
        bottommenu.setVisibility(View.INVISIBLE);
        adjustMenu.setVisibility(View.INVISIBLE);
        settingMenu.setVisibility(View.INVISIBLE);
        uiSettingMenu.setVisibility(View.INVISIBLE);
    }

    private void menuShow() {
        menuLayout.setVisibility(View.VISIBLE);
        topmenu.setVisibility(View.VISIBLE);
        topmenu.startAnimation(menuTopIn);
        bottommenu.setVisibility(View.VISIBLE);
        bottommenu.startAnimation(menuBottomIn);
    }

    private void adjustMenuShow() {
        menuLayout.setVisibility(View.VISIBLE);
        adjustMenu.setVisibility(View.VISIBLE);
        adjustMenu.startAnimation(menuBottomIn);
    }

    private void settingMenuShow() {
        menuLayout.setVisibility(View.VISIBLE);
        settingMenu.setVisibility(View.VISIBLE);
        settingMenu.startAnimation(menuBottomIn);
    }

    private void uiSettingMenuShow() {
        menuLayout.setVisibility(View.VISIBLE);
        uiSettingMenu.setVisibility(View.VISIBLE);
        uiSettingMenu.startAnimation(menuBottomIn);
    }


    /**
     * 重置黑屏时间
     */
    private void screenOffTimerStart() {
        if (screenTimeOut < 0) {
            keepScreenOn(true);
            return;
        }
        int screenOffTime = screenTimeOut * 1000 - SystemUtil.getScreenOffTime(this);
        if (screenOffTime > 0) {
            AppUtils.removeRunnable(keepScreenRunnable);
            keepScreenOn(true);
            AppUtils.runOnUIDelayed(keepScreenRunnable, screenOffTime);
        } else {
            keepScreenOn(false);
        }
    }

    /**
     * @param keepScreenOn 是否保持亮屏
     */
    public void keepScreenOn(boolean keepScreenOn) {
        if (keepScreenOn) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    /**
     * 取消亮屏保持
     */
    private void unKeepScreenOn() {
        keepScreenOn(false);
    }

}
