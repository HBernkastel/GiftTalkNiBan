package com.zyx.group14.android1603.gifttalkniban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyx.group14.android1603.gifttalkniban.R;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.imageloader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yixin on 2016/8/31.
 */
public class HandPickAdapter extends BaseExpandableListAdapter {
    private List<Long> dates;
    private Map<Long,List<HomeItem.DataBean.ItemsBean>> content;
    private Context mContext;
    public HandPickAdapter(Context mContext, List<Long> dates, Map<Long,List<HomeItem.DataBean.ItemsBean>> content){
        this.mContext = mContext;
        this.dates = dates;
        this.content = content;
    }
    @Override
    public int getGroupCount() {
        return dates == null ? 0 : dates.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        long key = dates.get(groupPosition);
        List<HomeItem.DataBean.ItemsBean> beanList = content.get(key);
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        return i1;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        MyGroupHolder myGroupHolder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.hand_pick_group_view,parent,false);
            myGroupHolder = new MyGroupHolder(view);
        }else {
            myGroupHolder = (MyGroupHolder) view.getTag();
        }
        long key = dates.get(groupPosition);
        myGroupHolder.handPickDateTv.setText(formatKey(key));
        myGroupHolder.handPickUpdateTv.setText(getUpdateTime());
        return view;
    }

    private String getUpdateTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour >= 5 && hour < 11){
            return "下次更新 11:00";
        }else if(hour >=11 && hour <17){
            return "下次更新 17:00";
        }else if(hour >= 17 && hour < 23){
            return "下次更新 23:00";
        }else {
            return "下次更新 5:00";
        }
    }

    private String formatKey(long key) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月DD日 EEEE", Locale.CHINESE);
        String date = sdf.format(new Date(key*1000));
        return date;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        MyChildHolder myViewHolder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.home_lv_item,parent,false);
            myViewHolder = new MyChildHolder(view);
        }else {
            myViewHolder = (MyChildHolder) view.getTag();
            myViewHolder.img.setImageResource(R.drawable.ig_holder_image);
            myViewHolder.img.setScaleType(ImageView.ScaleType.CENTER);
        }
        long key = dates.get(groupPosition);
        HomeItem.DataBean.ItemsBean item = content.get(key).get(childPosition);
        if(item.getColumn() != null) {
            myViewHolder.category.setText(item.getColumn().getCategory());
            myViewHolder.cTitle.setText(item.getColumn().getTitle());
        }
        ImageLoader.init(mContext).load(item.getAuthor().getAvatar_url(),myViewHolder.authorIv);
        myViewHolder.nickName.setText(item.getAuthor().getNickname());
        ImageLoader.init(mContext).load(item.getCover_image_url(),myViewHolder.img);
        myViewHolder.title.setText(item.getTitle());
        myViewHolder.count.setText(item.getLikes_count() + "");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    class MyGroupHolder{
        @BindView(R.id.hand_pick_date_tv)
        public TextView handPickDateTv;
        @BindView(R.id.hand_pick_update_time_tv)
        public TextView handPickUpdateTv;
        public MyGroupHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
    class MyChildHolder {
        @BindView(R.id.home_lv_item_category_tv)
        public TextView category;
        @BindView(R.id.home_lv_item_ctitle_tv)
        public TextView cTitle;
        @BindView(R.id.home_lv_item_author_iv)
        public CircleImageView authorIv;
        @BindView(R.id.home_lv_item_author_tv)
        public TextView nickName;
        @BindView(R.id.home_lv_item_iv)
        public ImageView img;
        @BindView(R.id.home_lv_item_title_tv)
        public TextView title;
        @BindView(R.id.home_lv_item_count_tv)
        public TextView count;
        public MyChildHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
