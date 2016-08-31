package com.zyx.group14.android1603.gifttalkniban.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeFirstHead;
import com.zyx.group14.android1603.gifttalkniban.imageloader.ImageLoader;

/**
 * Created by yixin on 2016/8/30.
 */
public class HomeTopVpAdapter extends PagerAdapter{
    private HomeFirstHead firstHead;
    private Context mContext;
    public HomeTopVpAdapter(Context mContext,HomeFirstHead firstHead){
        this.mContext = mContext;
        this.firstHead = firstHead;
    }
    @Override
    public int getCount() {
        return 30000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(firstHead.getData() != null) {
            ImageLoader.init(mContext).load(firstHead.getData().getBanners().get(position % 5).getImage_url(), imageView);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.getChildAt(position));
    }
}
