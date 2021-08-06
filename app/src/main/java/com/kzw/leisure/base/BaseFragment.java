package com.kzw.leisure.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.kzw.leisure.utils.TUtil;
import com.kzw.leisure.widgets.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: kang4
 * Date: 2019/11/19
 * Description:
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {

    public E mModel;
    public T mPresenter;
    protected View mRootView;
    protected Activity mActivity;
    protected LayoutInflater inflater;
    protected Context mContext;
    protected boolean isPrepared;// 标志位 标志已经初始化完成。
    protected boolean isVisible; //标志位 fragment是否可见
    private Unbinder mUnbinder;


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null)
                parent.removeView(mRootView);
        } else {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            mActivity = getSupportActivity();
            mContext = mActivity;
            this.inflater = inflater;
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = getActivity();
        }
        initPresenter();
        initVariables();
        initWidget();
        finishCreateView(savedInstanceState);
        initData();
    }

    protected void lazyLoadData() {

    }

    protected void initData() {
        loadData();
    }

    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mUnbinder.unbind();
    }

    /**
     * 分离
     */
    @Override
    public void onDetach() {
        this.mActivity = null;
        super.onDetach();
    }


    /**
     * 初始化RV
     */
    protected void initRecyclerView() {

    }


    /**
     * 初始化Presenter
     */
    public void initPresenter() {

    }

    /**
     * 初始化刷新
     */
    @SuppressLint("CheckResult")
    protected void initRefreshLayout() {

    }

    /**
     * 清除数据
     */
    protected void clearData() {

    }


    /**
     * 初始化变量
     */
    public void initVariables() {
    }

    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) return;
        lazyLoadData();
        isPrepared = false;
    }

    protected void onInvisible() {

    }

    /**
     * 加载数据
     */
    protected void loadData() {
    }


    protected void finishTask() {

    }

    /**
     * 布局
     *
     * @return int
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public void initWidget() {

    }

    protected void onVisible() {
        lazyLoad();
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 获取Activity
     *
     * @return FragmentActivity
     */
    public FragmentActivity getSupportActivity() {
        return (FragmentActivity) super.getActivity();
    }

    /**
     * 获取ApplicationContext 信息
     *
     * @return Context
     */
    public Context getApplicationContext() {
        return this.mContext == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.mContext.getApplicationContext();
    }


    /**
     * 隐藏View
     *
     * @param views view数组
     */
    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 显示View
     *
     * @param views view数组
     */
    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    /**
     * 隐藏View
     *
     * @param id
     */
    protected void gone(final @IdRes int... id) {
        if (id != null && id.length > 0) {
            for (int resId : id) {
                View view = $(resId);
                if (view != null)
                    gone(view);
            }
        }

    }

    /**
     * 显示View
     *
     * @param id
     */
    protected void visible(final @IdRes int... id) {
        if (id != null && id.length > 0) {
            for (int resId : id) {
                View view = $(resId);
                if (view != null)
                    visible(view);
            }
        }
    }

    public View $(@IdRes int id) {
        View view;
        if (mRootView != null) {
            view = mRootView.findViewById(id);
            return view;
        }
        return null;
    }


    protected void setToolbar(Toolbar toolbar) {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
        }
    }

    //设置ToolBar
    protected void setupActionBar(boolean isShowTitle) {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        if (appCompatActivity != null) {
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(isShowTitle);
            }
        }
    }

    protected void setupActionBar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        if (appCompatActivity != null) {
            ActionBar actionBar = appCompatActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    public void showToast(String msg) {
        ToastUtil.showToast(msg);
    }
}
