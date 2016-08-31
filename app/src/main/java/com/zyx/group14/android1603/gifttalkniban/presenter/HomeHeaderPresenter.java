package com.zyx.group14.android1603.gifttalkniban.presenter;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeFirstHead;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeSecondHead;
import com.zyx.group14.android1603.gifttalkniban.callback.IHomeHeaderCallBack;
import com.zyx.group14.android1603.gifttalkniban.model.HomeHeaderModel;
import com.zyx.group14.android1603.gifttalkniban.model.IHomeHeaderModel;
import com.zyx.group14.android1603.gifttalkniban.view.IGiftTalkView;

/**
 * Created by yixin on 2016/8/30.
 */
public class HomeHeaderPresenter implements IHomeHeaderPresenter,IHomeHeaderCallBack{
    private IGiftTalkView view;
    private IHomeHeaderModel headerModel = new HomeHeaderModel();
    public HomeHeaderPresenter(IGiftTalkView view){
        this.view = view;
    }
    @Override
    public void loadFirst() {
        headerModel.loadFirst(this);
    }

    @Override
    public void loadSecond() {
        headerModel.loadSecond(this);
    }

    @Override
    public void firstComplete(HomeFirstHead firstHead) {
        view.showFirstHeader(firstHead);
    }

    @Override
    public void secondComplete(HomeSecondHead secondHead) {
        view.showSecondHeader(secondHead);
    }
}
