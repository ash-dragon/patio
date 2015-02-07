package com.yjh.qinyuan.main.aroundme;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.TabActivity;
import com.yjh.qinyuan.widget.MyActionBar;

public class AroundMeActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_tabs);

        init();
    }

    @Override
    public void init() {
        super.init();
        setMyActionBar((MyActionBar) findViewById(R.id.action_bar));
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        AroundMeFragment fragment = new AroundMeFragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

}
