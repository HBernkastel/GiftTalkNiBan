package com.zyx.group14.android1603.gifttalkniban.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeChannel;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeFirstHead;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeItem;
import com.zyx.group14.android1603.gifttalkniban.bean.HomeSecondHead;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yixin on 2016/8/29.
 */
public class GsonParse {
    public static List<HomeChannel> homeTitleParse(String jsonString){
        List<HomeChannel> title = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String json = jsonObject.getString("data");
            JSONObject jsonObject1 = new JSONObject(json);
            String json2 = jsonObject1.getString("channels");
            Gson gson = new Gson();
            title = gson.fromJson(json2,new TypeToken<List<HomeChannel>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return title;
    }
    public static HomeItem homeItemGsonParse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,HomeItem.class);
    }
    public static HomeFirstHead homeFirstHeadParse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,HomeFirstHead.class);
    }
    public static HomeSecondHead homeSecondHeadParse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, HomeSecondHead.class);
    }
}
