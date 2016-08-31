package com.zyx.group14.android1603.gifttalkniban.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yixin on 2016/8/31.
 */
public class HomeHPAdapter extends BaseAdapter {
  //  private List<HomeItem.DataBean.ItemsBean> content;
    private List<Object> datas = new ArrayList<>();
    private List<Integer> types = new ArrayList<>();
    public static final int TYPE_DATE = 1;
    public static final int TYPE_DATA = 2;
    private long curDate = 0;
    private Context mContext;
    public HomeHPAdapter(Context mContext, List<HomeItem.DataBean.ItemsBean> content){
        this.mContext = mContext;
        for(int i = 0; i< content.size();i++){
            HomeItem.DataBean.ItemsBean bean = content.get(i);

        }
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
