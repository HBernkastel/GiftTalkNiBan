package com.zyx.group14.android1603.gifttalkniban.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zyx.group14.android1603.gifttalkniban.R;
import com.zyx.group14.android1603.gifttalkniban.adapter.HandPickAdapter;
import com.zyx.group14.android1603.gifttalkniban.adapter.HomeHPAdapter;
import com.zyx.group14.android1603.gifttalkniban.adapter.HomeItemAdapter;
import com.zyx.group14.android1603.gifttalkniban.adapter.HomeTopVpAdapter;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeFirstHead;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeSecondHead;
import com.zyx.group14.android1603.gifttalkniban.callback.IMyRefreshListViewCallBack;
import com.zyx.group14.android1603.gifttalkniban.custom.MyRefreshListView;
import com.zyx.group14.android1603.gifttalkniban.imageloader.ImageLoader;
import com.zyx.group14.android1603.gifttalkniban.presenter.HomeHeaderPresenter;
import com.zyx.group14.android1603.gifttalkniban.presenter.HomeItemPresenter;
import com.zyx.group14.android1603.gifttalkniban.presenter.IGiftTalkPresenter;
import com.zyx.group14.android1603.gifttalkniban.presenter.IHomeHeaderPresenter;
import com.zyx.group14.android1603.gifttalkniban.view.IGiftTalkView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页listView的fragment,继承自定义listView的接口来完成上拉\下拉刷新功能
 * Created by yixin on 2016/8/27.
 */
public class HomeItemFragment extends Fragment implements IGiftTalkView,IMyRefreshListViewCallBack{
    private View view;
    private Context mContext;
    private HomeItemAdapter adapter;
    private List<HomeItem.DataBean.ItemsBean> content = new ArrayList<>();
    private HomeFirstHead firstHead = new HomeFirstHead();
    private HomeItemPresenter presenter;
    private int id;

    private int offset = 0;

    private int curVpItem;
    private ViewPager homeTopVp;
    private LinearLayout homeTopLl;
    private HorizontalScrollView homeTopHsv;

    //这是一个自定义下拉刷新listView,礼物说版
    @BindView(R.id.home_vp_item_mrlv)
    MyRefreshListView myRefreshListView;
    private HomeHPAdapter homeHPAdapter;
    private boolean isHandPick = false;

    public static HomeItemFragment newInstance(int id){
        HomeItemFragment fragment = new HomeItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = this.getArguments().getInt("id");
        if(id == 101){
            isHandPick = true;
        }
        this.mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_item,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        presenter = new HomeItemPresenter(this);
        presenter.loadData(this.getArguments());
        if(isHandPick) {
            IHomeHeaderPresenter headerPresenter = new HomeHeaderPresenter(this);
            headerPresenter.loadFirst();
            headerPresenter.loadSecond();
        }
    }

    private void init() {
        content = new ArrayList<>();
        ButterKnife.bind(this,view);
        myRefreshListView.setVerticalScrollBarEnabled(true);
        myRefreshListView.setCallBack(this);
        adapter = new HomeItemAdapter(mContext,content);
        homeHPAdapter = new HomeHPAdapter(mContext,content);
        if(isHandPick){
            initHeaderView();
            myRefreshListView.setAdapter(homeHPAdapter);
        }else {
            myRefreshListView.setAdapter(adapter);
        }
    }
//    @BindView(R.id.home_top_vp)
//    ViewPager homeTopVp;
//    @BindView(R.id.home_top_ll)
//    LinearLayout homeTopLl;
//    @BindView(R.id.home_top_hsv)
//    HorizontalScrollView homeTopHsv;
    private void initHeaderView() {

        View header1 = LayoutInflater.from(mContext).inflate(R.layout.home_first_header,myRefreshListView,false);
        homeTopVp = (ViewPager) header1.findViewById(R.id.home_top_vp);
        homeTopLl = (LinearLayout) header1.findViewById(R.id.home_top_ll);
//        ButterKnife.bind(this,header1);
        View header2 = LayoutInflater.from(mContext).inflate(R.layout.home_second_header,myRefreshListView,false);
        homeTopHsv = (HorizontalScrollView) header2.findViewById(R.id.home_top_hsv);
//        ButterKnife.bind(this,header2);
        myRefreshListView.addHeaderView(header1);
        myRefreshListView.addHeaderView(header2);

        HomeTopVpAdapter homeTopVpadapter = new HomeTopVpAdapter(mContext,firstHead);
        homeTopVp.setAdapter(homeTopVpadapter);
        setDotsEnable(0);
        homeTopVp.setCurrentItem(10000);
        curVpItem = 10000;
        homeTopVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDotsEnable(position);
                curVpItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        autoScrollSet();
    }

    private void autoScrollSet() {
        mHandler.sendEmptyMessageDelayed(1,5000);
        homeTopVp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(1,5000);
                        break;
                    default:break;
                }
                return false;
            }
        });
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            curVpItem ++;
            homeTopVp.setCurrentItem(curVpItem);
            sendEmptyMessageDelayed(1,5000);
        }
    };

    private void setDotsEnable(int position) {
        for(int i = 0;i<homeTopLl.getChildCount();i++){
            if(i == position%5){
                homeTopLl.getChildAt(i).setEnabled(false);
            }else{
                homeTopLl.getChildAt(i).setEnabled(true);
            }
        }
    }


    @Override
    public void refreshView(List... objectList) {
        HomeItem homeItems = (HomeItem) objectList[0].get(0);
        if(offset == 0) {
            content.clear();
        }
        content.addAll(homeItems.getData().getItems());
        if(isHandPick){
            homeHPAdapter.notifyDataSetChanged();
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void startRefresh() {
        /**
         *下拉刷新
         */
        offset = 0;
        presenter.loadData(this.getArguments());
        myRefreshListView.refreshComplete();
        Toast.makeText(mContext,"刷新完毕",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void footRefresh() {
        /**
         *上拉到离尾部还有10个listView的item时触发的加载数据
         */
        offset += 20;
        Bundle bundle = new Bundle();
        bundle.putInt("id",this.getArguments().getInt("id"));
        bundle.putInt("offset",offset);
        presenter.loadData(bundle);

    }
    @Override
    public void showFirstHeader(HomeFirstHead firstHead) {
        homeTopVp.setAdapter(new HomeTopVpAdapter(mContext,firstHead));
        homeTopVp.setCurrentItem(10000);
        curVpItem = 10000;

    }

    @Override
    public void showSecondHeader(HomeSecondHead secondHead) {
        List<HomeSecondHead.DataBean.SecondaryBannersBean> secondaryBannersBeanList = new ArrayList<>();
        secondaryBannersBeanList.addAll(secondHead.getData().getSecondary_banners());
        for(int i = 0 ; i < secondaryBannersBeanList.size() ; i++ ){
            HomeSecondHead.DataBean.SecondaryBannersBean bean = secondaryBannersBeanList.get(i);
            if(bean != null){
                LinearLayout ll = (LinearLayout) homeTopHsv.getChildAt(0);
                ImageView iv = (ImageView) ll.getChildAt(i);;
                ImageLoader.init(mContext).load(bean.getImage_url(),iv);
            }
        }
    }

}

