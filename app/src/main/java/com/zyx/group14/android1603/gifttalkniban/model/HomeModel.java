package com.zyx.group14.android1603.gifttalkniban.model;

import android.os.Bundle;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeChannel;
import com.zyx.group14.android1603.gifttalkniban.callback.IGiftTalkCallBack;
import com.zyx.group14.android1603.gifttalkniban.constant.GiftTalkUrl;
import com.zyx.group14.android1603.gifttalkniban.gson.GsonParse;
import com.zyx.group14.android1603.gifttalkniban.utils.IOKCallBack;
import com.zyx.group14.android1603.gifttalkniban.utils.OkHttpTool;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by yixin on 2016/8/27.
 */
public class HomeModel implements IGiftTalkModel {

    @Override
    public void loadData(Bundle msg, final IGiftTalkCallBack iGiftTalkCallBack) {
        OkHttpTool.newInstance().start(GiftTalkUrl.HOME_CLASSIFY).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                List<HomeChannel> title = GsonParse.homeTitleParse(result);
                iGiftTalkCallBack.dataLoadComplete(title);
            }
        });
    }
}
