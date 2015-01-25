package com.yjh.qinyuan.main.userinfo;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.TabActivity;
import com.yjh.qinyuan.widget.MyActionBar;

public class UserInfoActivity extends TabActivity {

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
        UserInfoFragment fragment = new UserInfoFragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
