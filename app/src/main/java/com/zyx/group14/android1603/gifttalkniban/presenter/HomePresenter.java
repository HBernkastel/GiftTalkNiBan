package com.zyx.group14.android1603.gifttalkniban.presenter;

import android.os.Bundle;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.callback.IGiftTalkCallBack;
import com.zyx.group14.android1603.gifttalkniban.model.HomeModel;
import com.zyx.group14.android1603.gifttalkniban.model.IGiftTalkModel;
import com.zyx.group14.android1603.gifttalkniban.view.IGiftTalkView;

import java.util.List;
import java.util.Map;

/**
 * Created by yixin on 2016/8/27.
 */
public class HomePresenter implements IGiftTalkPresenter,IGiftTalkCallBack {
    private IGiftTalkModel model = new HomeModel();
    private IGiftTalkView view;
    public HomePresenter(IGiftTalkView view){
        this.view = view;
    }

    @Override
    public void loadData(Bundle msg) {
       model.loadData(msg,this);
    }

    @Override
    public void dataLoadComplete(List... objectList) {
         view.refreshView(objectList);
    }

}
