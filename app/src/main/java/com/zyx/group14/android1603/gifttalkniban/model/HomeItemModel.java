package com.zyx.group14.android1603.gifttalkniban.model;

import android.content.ClipData;
import android.os.Bundle;

import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.callback.IGiftTalkCallBack;
import com.zyx.group14.android1603.gifttalkniban.constant.GiftTalkUrl;
import com.zyx.group14.android1603.gifttalkniban.gson.GsonParse;
import com.zyx.group14.android1603.gifttalkniban.utils.IOKCallBack;
import com.zyx.group14.android1603.gifttalkniban.utils.OkHttpTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yixin on 2016/8/29.
 */
public class HomeItemModel implements IGiftTalkModel {
    private int id;

    @Override
    public void loadData(Bundle msg, final IGiftTalkCallBack iGiftTalkCallBack) {
        id = msg.getInt("id");
        int offset = msg.getInt("offset",0);
        String url = GiftTalkUrl.HOME_CONTENT_START + id + GiftTalkUrl.HOME_CONTENT_END + offset;
        OkHttpTool.newInstance().start(url).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                HomeItem homeItem = GsonParse.homeItemGsonParse(result);
                List<HomeItem.DataBean.ItemsBean> itemsBeenList = homeItem.getData().getItems();
                if(itemsBeenList.size() != 0) {
                    List<Object> list = new ArrayList<Object>();
                    list.add(homeItem);
                    iGiftTalkCallBack.dataLoadComplete(list);
//                    }else{
//                        List<Long> dateList = new ArrayList<>();
//                        Map<Long,List<HomeItem.DataBean.ItemsBean>> map = new HashMap<>();
//                        long curDate = 0;
//                        List<HomeItem.DataBean.ItemsBean> beanList = new ArrayList<>();
//                        for(int i = 0;i<itemsBeenList.size();i++){
//                            HomeItem.DataBean.ItemsBean bean = itemsBeenList.get(i);
//                            long date = bean.getCreated_at();
//                            if(date != curDate){
//                                dateList.add(date);
//                                if(curDate != 0) {
//                                    List<HomeItem.DataBean.ItemsBean> list = new ArrayList<>();
//                                    list.addAll(beanList);
//                                    beanList.clear();
//                                    map.put(curDate,list);
//                                }
//                                curDate = date;
//                            }
//                            beanList.add(bean);
//                            if(i == itemsBeenList.size() - 1){
//                                map.put(date,beanList);
//                            }
                }
            }
        });
    }
}
