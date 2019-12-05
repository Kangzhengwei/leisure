package com.kzw.leisure.ui.fragment;

import com.google.android.material.tabs.TabLayout;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.ViewPagerAdapter;
import com.kzw.leisure.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


public class CollectFragment extends BaseFragment {


    @BindView(R.id.tb_home)
    TabLayout tbHome;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        titles.add("视频");
        titles.add("网站");
        fragments.add(VideoCollectFragment.newInstance());
        fragments.add(WebCollectFragment.newInstance());
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), titles, fragments));
        tbHome.setupWithViewPager(viewPager);
    }
}
