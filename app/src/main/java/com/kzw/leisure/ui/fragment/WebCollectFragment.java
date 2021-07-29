package com.kzw.leisure.ui.fragment;

import android.view.Gravity;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.WebSiteCollectAdapter;
import com.kzw.leisure.base.BaseFragment;
import com.kzw.leisure.event.WebCollectEvent;
import com.kzw.leisure.realm.CollectDataBean;
import com.kzw.leisure.realm.CollectDataList;
import com.kzw.leisure.rxJava.RxBus;
import com.kzw.leisure.ui.activity.MainActivity;
import com.kzw.leisure.utils.DimenUtil;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.widgets.popwindow.SiteOperationMenu;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

/**
 * author: kang4
 * Date: 2019/12/3
 * Description:
 */
public class WebCollectFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private WebSiteCollectAdapter adapter;
    private Realm realm;
    private List<CollectDataBean> list = new ArrayList<>();

    public static Fragment newInstance() {
        return new WebCollectFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect_web;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        realm = RealmHelper.getInstance().getRealm();
        initView();
        initList();
    }

    @Override
    public void initVariables() {
        RxBus.getInstance().toObservable(this, WebCollectEvent.class).subscribe(new Observer<WebCollectEvent>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WebCollectEvent event) {
                initList();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new WebSiteCollectAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setMenuClickListener((v, bean, position) -> {
            SiteOperationMenu pop = new SiteOperationMenu(mContext);
            if (position == list.size() - 1) {
                pop.showAsDropDown(v, DimenUtil.dip2px(mContext, -100), DimenUtil.dip2px(mContext, -80), Gravity.BOTTOM | Gravity.START);
            } else {
                pop.showAsDropDown(v, DimenUtil.dip2px(mContext, -100), 0, Gravity.BOTTOM | Gravity.START);
            }
            pop.setMenuClickListener(new SiteOperationMenu.menuClickListener() {
                @Override
                public void delete() {
                    realm.executeTransaction(realm -> list.remove(bean));
                    adapter.setNewData(list);
                }

                @Override
                public void moveTop() {
                    realm.executeTransaction(realm -> {
                        list.remove(bean);
                        list.add(0, bean);
                    });
                    adapter.setNewData(list);
                }
            });
        });
        adapter.setOnItemClickListener((adapter, view, position) -> {
            MainActivity activity = (MainActivity) mActivity;
            if (activity != null) {
                activity.intentFragment(list.get(position).getUrl());
            }
        });
    }

    public void initList() {
        CollectDataList realmList = realm.where(CollectDataList.class).findFirst();
        if (realmList != null) {
            list = realmList.getCollectList();
        }
        adapter.setNewData(list);
    }

}
