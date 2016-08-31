package com.zyx.group14.android1603.gifttalkniban;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zyx.group14.android1603.gifttalkniban.fragment.CategoryFragment;
import com.zyx.group14.android1603.gifttalkniban.fragment.HomeFragment;
import com.zyx.group14.android1603.gifttalkniban.fragment.ProfileFragment;
import com.zyx.group14.android1603.gifttalkniban.fragment.SelectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    private int curFragment = 0;

    @BindView(R.id.top_title_tv)
    TextView topTitleTv;
    @BindView(R.id.main_rg)
    RadioGroup bottomRg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
        showFragment(0);

    }

    private void showFragment(int position) {
        ((RadioButton)bottomRg.getChildAt(position)).setChecked(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();
        Fragment fragment = fragmentList.get(position);
        if(fragmentList.get(curFragment).isAdded()){
            fragmentTransaction.hide(fragmentList.get(curFragment));
        }
        if(fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        }else {
            fragmentTransaction.add(R.id.main_fl,fragment);
        }
        curFragment = position;
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SelectFragment());
        fragmentList.add(new CategoryFragment());
        fragmentList.add(new ProfileFragment());
    }

    private void initView() {
        ButterKnife.bind(this);

        for(int i = 0; i < fragmentList.size();i++){
            final int j = i;
            bottomRg.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showFragment(j);
                }
            });
        }
    }
}
