package com.zyx.group14.android1603.gifttalkniban.view;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeFirstHead;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeSecondHead;

import java.util.List;
import java.util.Map;

/**
 * Created by yixin on 2016/8/27.
 */
public interface IGiftTalkView {
    void refreshView(List...objectList);
    void showFirstHeader(HomeFirstHead firstHead);
    void showSecondHeader(HomeSecondHead secondHead);
}
