package com.zyx.group14.android1603.gifttalkniban.constant;

/**
 * 数据请求地址
 * Created by yixin on 2016/8/27.
 */
public class GiftTalkUrl {
    public static final String HOME_CLASSIFY = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2";
    //http://api.liwushuo.com/v2/channels/101/items?gender=1&limit=20&offset=0&generation=2
    //http://api.liwushuo.com/v2/channels/101/items_v2?limit=20&gender=1&offset=0&generation=2
    public static final String HOME_CONTENT_START = "http://api.liwushuo.com/v2/channels/";
    public static final String HOME_CONTENT_END = "/items_v2?gender=1&limit=20&generation=2&offset=";
    public static final String HOME_FIRST_HEAD = "http://api.liwushuo.com/v2/banners";
    public static final String HOME_SECOND_HEAD = "http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=1";
    public static final String HOME_SELECTION = "http://api.liwushuo.com/v2%2Fposts%2F1039043%2Frecommend";
}
