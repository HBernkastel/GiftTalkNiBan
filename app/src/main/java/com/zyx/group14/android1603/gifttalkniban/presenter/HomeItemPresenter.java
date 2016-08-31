package com.zyx.group14.android1603.gifttalkniban.presenter;

import android.os.Bundle;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.callback.IGiftTalkCallBack;
import com.zyx.group14.android1603.gifttalkniban.model.HomeItemModel;
import com.zyx.group14.android1603.gifttalkniban.model.IGiftTalkModel;
import com.zyx.group14.android1603.gifttalkniban.view.IGiftTalkView;

import java.util.List;
import java.util.Map;

/**
 * Created by yixin on 2016/8/29.
 */
public class HomeItemPresenter implements IGiftTalkPresenter,IGiftTalkCallBack {
    private IGiftTalkView view;
    private IGiftTalkModel model;

    public HomeItemPresenter(IGiftTalkView view){
        this.view = view;
        model = new HomeItemModel();
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
