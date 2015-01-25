package com.yjh.qinyuan.main.section;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.TabActivity;
import com.yjh.qinyuan.main.userinfo.UserInfoFragment;
import com.yjh.qinyuan.widget.MyActionBar;

public class SectionActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        init();
    }

    @Override
    public void init() {
        super.init();
        setMyActionBar((MyActionBar) findViewById(R.id.action_bar));
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        SiteListFragment fragment = new SiteListFragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
