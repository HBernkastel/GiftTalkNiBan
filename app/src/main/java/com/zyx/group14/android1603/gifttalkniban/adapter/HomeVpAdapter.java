package com.zyx.group14.android1603.gifttalkniban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeChannel;

import java.util.List;

/**
 * Created by yixin on 2016/8/29.
 */
public class HomeVpAdapter extends FragmentPagerAdapter {
    private List<Object> title;
    private List<Fragment> content;
    public HomeVpAdapter(FragmentManager fm, List<Object> title,List<Fragment> content) {
        super(fm);
        this.title = title;
        this.content = content;
    }

    @Override
    public Fragment getItem(int position) {
        return content.get(position);
    }

    @Override
    public int getCount() {
        return content == null ? 0 : content.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((HomeChannel)title.get(position)).getName();
    }
}
