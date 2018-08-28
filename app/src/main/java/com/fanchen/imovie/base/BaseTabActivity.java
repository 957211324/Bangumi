package com.fanchen.imovie.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.fanchen.imovie.R;
import com.fanchen.imovie.view.NoScrollViewPager;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by fanchen on 2017/7/24.
 */
public abstract class BaseTabActivity extends BaseToolbarActivity {

    @InjectView(R.id.sliding_tabs)
    protected TabLayout mSlidingTab;
    @InjectView(R.id.view_pager)
    protected NoScrollViewPager mViewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_tab_pager;
    }

    @Override
    protected void initActivity(Bundle savedState, LayoutInflater inflater) {
        super.initActivity(savedState, inflater);
        PagerAdapter adapter = getAdapter(getSupportFragmentManager());
        if(!checkTabViewNull()){
            mViewPager.setAdapter(adapter);
            mViewPager.setOffscreenPageLimit(adapter.getCount());
            mSlidingTab.setupWithViewPager(mViewPager);
            mSlidingTab.setTabMode(getTabMode(adapter));
        }
    }

    protected int getTabMode(PagerAdapter adapter){
        return adapter.getCount() < 5 ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE;
    }

    /**
     * @return
     */
    public ViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * @return
     */
    public TabLayout getSlidingTab() {
        return mSlidingTab;
    }

    /**
     * @return
     */
    protected abstract PagerAdapter getAdapter(FragmentManager fm);

    public boolean checkTabViewNull(){
        return mSlidingTab == null || mViewPager == null;
    }
}
