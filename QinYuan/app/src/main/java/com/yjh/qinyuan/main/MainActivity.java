package com.yjh.qinyuan.main;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

import com.yjh.qinyuan.R;
import com.yjh.qinyuan.main.aroundme.AroundMeActivity;
import com.yjh.qinyuan.main.section.SectionActivity;
import com.yjh.qinyuan.main.userinfo.UserInfoActivity;
import com.yjh.qinyuan.widget.MyActionBar;

public class MainActivity extends ActivityGroup {

    private static final String TAG = "MainActivity";
    private static final String TAB_SECTION = "tab_section";
    private static final String TAB_AROUND_ME = "tab_around_me";
    private static final String TAB_USER_INFO = "tab_user_info";
    private static final int SECTION = 0;
    private static final int AROUND_ME = 1;
    private static final int USER_INFO = 2;
    private static final int TAB_COUNT = 3;
    private MyActionBar mActionBar;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        mActionBar = (MyActionBar) findViewById(R.id.action_bar);
        LayoutInflater inflater = LayoutInflater.from(this);
        View tabChat = inflater.inflate(R.layout.tab_section, null);
        View tabContact = inflater.inflate(R.layout.tab_around_me, null);
        View tabStream = inflater.inflate(R.layout.tab_user_info, null);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(getLocalActivityManager());
        mTabHost.setBackgroundColor(getResources().getColor(R.color.transparent));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_SECTION)
                .setIndicator(tabChat).setContent(new Intent(this, SectionActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_AROUND_ME)
                .setIndicator(tabContact).setContent(new Intent(this, AroundMeActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_USER_INFO)
                .setIndicator(tabStream).setContent(new Intent(this, UserInfoActivity.class)));

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // todo
                int i = 0;
            }
        });

        mTabHost.setCurrentTab(SECTION);
    }
}
