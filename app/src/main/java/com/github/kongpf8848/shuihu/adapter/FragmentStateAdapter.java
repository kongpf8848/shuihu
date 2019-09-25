package com.github.kongpf8848.shuihu.adapter;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by jack on 2016/5/10.
 */
public class FragmentStateAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;


    public FragmentStateAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragments.get(position);
    }

    @Override
    public int getCount() {
        return this.mFragments.size();
    }

}
