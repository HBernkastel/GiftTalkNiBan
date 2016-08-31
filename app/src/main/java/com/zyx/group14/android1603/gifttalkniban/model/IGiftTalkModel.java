package com.zyx.group14.android1603.gifttalkniban.model;

import android.os.Bundle;

import com.zyx.group14.android1603.gifttalkniban.callback.IGiftTalkCallBack;

import java.util.List;

/**
 * Created by yixin on 2016/8/27.
 */
public interface IGiftTalkModel {
    void loadData(Bundle msg, IGiftTalkCallBack iGiftTalkCallBack);
}
