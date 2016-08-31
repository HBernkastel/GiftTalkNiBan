package com.zyx.group14.android1603.listviewtest;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by yixin on 2016/8/25.
 */
public class MyRefreshListView extends ListView implements Animation.AnimationListener {
//    public static final int SCROLL_NO = 0;
    public static final int SCROLL_DOWN = 1;
    public static final int SCROLL_UP = 2;
    public static final String TAG = "zyx";
    private final Context mContext;

    private View header;
    private RelativeLayout ll;
//    private int maxHeight = 400;
    private int lastScrollState = 0;
//    private int curScrollState = 0;
    private int lastFirstVisibleItem = 0;
    private int lastFirstGetTop = 0;
    private boolean isHeaderVisible = false;
//    private boolean isReachTop = false;
    private ImageView boxIv;
    private ImageView heartIv;
//    private AnimatorSet animatorSet;
    private AnimationSet animationSet;
    private boolean isOnRefreshing = false;
    private IMyRefreshListViewCallBack callBack;

    public void setCallBack(IMyRefreshListViewCallBack callBack){
        this.callBack = callBack;
    }

    public MyRefreshListView(Context context) {
        this(context,null);
    }

    public MyRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public MyRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        setOnListener();
    }

    private void initView() {
        header = LayoutInflater.from(mContext).inflate(R.layout.home_pull_to_refresh_header,this,false);
        addHeaderView(header);
        boxIv = (ImageView) header.findViewById(R.id.home_box_iv);
        heartIv = (ImageView) header.findViewById(R.id.home_heart_iv);
        ll = (RelativeLayout) header.findViewById(R.id.ll);
    }

    private void setOnListener() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "onTouch: " + getCount() + getLastVisiblePosition());
                        if(isListViewReachTop()){
                            setHeaderVisible();
                        }else if(getLastVisiblePosition() > getCount() - 10){
                            callBack.footRefresh();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(ll.getVisibility() == VISIBLE){
                            int refreshHeight = heartIv.getMeasuredHeight() + boxIv.getMeasuredHeight();
                            if(header.getBottom() < refreshHeight) {
                                smoothScrollBy(header.getBottom(), 1000);
                            }else{
                                smoothScrollBy(header.getBottom() - refreshHeight,1000);
                                if(!isOnRefreshing) {
                                    boxIv.setImageResource(R.drawable.home_load_box);
                                    AnimationDrawable animationDrawable = (AnimationDrawable) boxIv.getDrawable();
                                    animationDrawable.start();
                                    initAnime();
//                                    heartIv.setVisibility(VISIBLE);
                                    heartIv.startAnimation(animationSet);
                                    isOnRefreshing = true;
                                }
                            }
                        }
                }
                return false;
            }
        });
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
//                switch (scrollState){
//                    case OnScrollListener.SCROLL_STATE_IDLE:
//
//                        Log.i("zyx",view1.getTop()+"  "+getFirstVisiblePosition());
//                        break;
//                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastScroll(firstVisibleItem, visibleItemCount, totalItemCount);
//                Log.i(TAG, "onScroll: "+header.getBottom());
                if(lastScrollState == SCROLL_DOWN ){
                    ll.setVisibility(GONE);
                    isHeaderVisible = false;
                }
            }
        });
    }
    private void setHeaderVisible(){
        if(!isHeaderVisible) {
            setSelection(1);
        }
        ll.setVisibility(VISIBLE);
        isHeaderVisible = true;
    }


    private void initAnime() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-header.getBottom());
        translateAnimation.setDuration(2000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(1000);

        animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillBefore(true);
        animationSet.setAnimationListener(this);
//         ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(heartIv,"translationY", 0, -header.getBottom()).setDuration(2000);
//         ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(heartIv,"alpha",0.0f,1.0f).setDuration(1000);
//         animatorSet = new AnimatorSet();
//         animatorSet.playTogether(objectAnimator,objectAnimator1);
    }

    private void lastScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(getChildCount()!=0) {
         //   Log.i("zyx", firstVisibleItem + "  " + totalItemCount + "  " + visibleItemCount);
            if (firstVisibleItem > lastFirstVisibleItem && lastFirstVisibleItem!=0) {
                lastScrollState = SCROLL_DOWN;
            } else if (firstVisibleItem < lastFirstVisibleItem) {
                lastScrollState = SCROLL_UP;
            } else {
                int getTop = getChildAt(0).getTop();
                if (getTop < lastFirstGetTop && firstVisibleItem != 0) {
                    lastScrollState = SCROLL_DOWN;
                } else if (getTop > lastFirstGetTop) {
                    lastScrollState = SCROLL_UP;
                }
            }
            lastFirstGetTop = getChildAt(0).getTop();
            lastFirstVisibleItem = firstVisibleItem;
        }
    }

    public boolean isListViewReachTop(){
        boolean result = false;
        if(this.getFirstVisiblePosition() == 0 && this.getChildCount()!=0){
            result = (this.getChildAt(0).getTop() == 0);
        }
        Log.i(TAG, "isListViewReachTop: "+result);
        return result;
    }

    public void refreshComplete(){
        boxIv.setImageResource(R.drawable.box_01);
        smoothScrollBy(header.getBottom(),1000);
        isOnRefreshing = false;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
         callBack.startRefresh();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    //    @Override
//    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxHeight, isTouchEvent);
//    }
}
