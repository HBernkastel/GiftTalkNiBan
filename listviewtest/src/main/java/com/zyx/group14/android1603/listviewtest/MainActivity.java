package com.zyx.group14.android1603.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMyRefreshListViewCallBack{
    private List<String> stringList = new ArrayList<>();
    private MyRefreshListView myRefreshListView;
    private ArrayAdapter arrayAdapter;
    private int total = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0;i<total;i++){
            stringList.add("DSB"+i);
        }
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,stringList);
        myRefreshListView = (MyRefreshListView) findViewById(R.id.mlv);
        myRefreshListView.setCallBack(this);
        myRefreshListView.setAdapter(arrayAdapter);
    }

    @Override
    public void startRefresh() {
        Toast.makeText(this,"打得不错&&抱歉",Toast.LENGTH_LONG).show();
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        myRefreshListView.refreshComplete();
    }

    @Override
    public void footRefresh() {
        total += 20;
        stringList.clear();
        for(int i = 0;i<total;i++){
            stringList.add("DSB"+i);
        }
        Toast.makeText(this,"下面...出来了",Toast.LENGTH_LONG).show();
        arrayAdapter.notifyDataSetChanged();
    }
}
