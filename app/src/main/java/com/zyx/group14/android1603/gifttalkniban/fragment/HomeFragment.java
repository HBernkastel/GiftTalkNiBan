package com.zyx.group14.android1603.gifttalkniban.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zyx.group14.android1603.gifttalkniban.R;
import com.zyx.group14.android1603.gifttalkniban.adapter.HomeVpAdapter;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeChannel;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeFirstHead;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeSecondHead;
import com.zyx.group14.android1603.gifttalkniban.presenter.HomePresenter;
import com.zyx.group14.android1603.gifttalkniban.presenter.IGiftTalkPresenter;
import com.zyx.group14.android1603.gifttalkniban.view.IGiftTalkView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yixin on 2016/8/25.
 */
public class HomeFragment extends Fragment implements IGiftTalkView{
    private View view;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentActivity activity;

    @BindView(R.id.home_top_tl)
    TabLayout homeTopTl;
    @BindView(R.id.home_content_vp)
    ViewPager homeContentVp;
    @BindView(R.id.home_top_classify_iv)
    ImageView homeTopCfIv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,view);
        IGiftTalkPresenter presenter = new HomePresenter(this);
        presenter.loadData(null);
        return view;
    }

    private void initFragment(List<Object> title) {
        for(int i = 0; i<title.size(); i++){
            HomeChannel homeChannel = (HomeChannel)title.get(i);
            fragmentList.add(HomeItemFragment.newInstance(homeChannel.getId()));
        }
    }


    @Override
    public void refreshView(List... objectList) {
        List<Object> title = new ArrayList<>();
         for(List list : objectList){
             title.addAll(list);
             break;
         }
        initFragment(title);
        HomeVpAdapter adapter = new HomeVpAdapter(activity.getSupportFragmentManager(),title,fragmentList);
        homeContentVp.setAdapter(adapter);
        homeTopTl.setupWithViewPager(homeContentVp);
    }

    @Override
    public void showFirstHeader(HomeFirstHead firstHead) {

    }

    @Override
    public void showSecondHeader(HomeSecondHead secondHead) {

    }
}
