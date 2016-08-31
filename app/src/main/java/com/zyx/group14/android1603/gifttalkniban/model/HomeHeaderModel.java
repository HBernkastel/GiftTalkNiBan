package com.zyx.group14.android1603.gifttalkniban.model;

import com.google.gson.Gson;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeFirstHead;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeSecondHead;
import com.zyx.group14.android1603.gifttalkniban.callback.IHomeHeaderCallBack;
import com.zyx.group14.android1603.gifttalkniban.constant.GiftTalkUrl;
import com.zyx.group14.android1603.gifttalkniban.gson.GsonParse;
import com.zyx.group14.android1603.gifttalkniban.utils.IOKCallBack;
import com.zyx.group14.android1603.gifttalkniban.utils.OkHttpTool;

/**
 * Created by yixin on 2016/8/30.
 */
public class HomeHeaderModel implements IHomeHeaderModel {
    @Override
    public void loadFirst(final IHomeHeaderCallBack callBack) {
        OkHttpTool.newInstance().start(GiftTalkUrl.HOME_FIRST_HEAD).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                HomeFirstHead firstHead = GsonParse.homeFirstHeadParse(result);
                callBack.firstComplete(firstHead);
            }
        });
    }

    @Override
    public void loadSecond(final IHomeHeaderCallBack callBack) {
        OkHttpTool.newInstance().start(GiftTalkUrl.HOME_SECOND_HEAD).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                HomeSecondHead secondHead = GsonParse.homeSecondHeadParse(result);
                callBack.secondComplete(secondHead);
            }
        });
    }
}
