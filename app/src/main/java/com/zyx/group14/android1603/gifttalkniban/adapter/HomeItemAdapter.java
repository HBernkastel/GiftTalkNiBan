package com.zyx.group14.android1603.gifttalkniban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyx.group14.android1603.gifttalkniban.R;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.imageloader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yixin on 2016/8/29.
 */
public class HomeItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeItem.DataBean.ItemsBean> content;
    public HomeItemAdapter(Context mContext,List<HomeItem.DataBean.ItemsBean> content){
        this.mContext = mContext;
        this.content = content;
    }
    @Override
    public int getCount() {
        return content == null ? 0 : content.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        MyViewHolder myViewHolder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.home_lv_item,parent,false);
            myViewHolder = new MyViewHolder(view);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
            myViewHolder.img.setImageResource(R.drawable.ig_holder_image);
            myViewHolder.img.setScaleType(ImageView.ScaleType.CENTER);
        }
        HomeItem.DataBean.ItemsBean item = content.get(position);
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
    class MyViewHolder {
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
        public MyViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
