package com.yjh.qinyuan.main;

import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.viewpagerindicator.CirclePageIndicator;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseActivity;
import com.yjh.qinyuan.gson.TownBranch;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.widget.MyActionBar;

import java.util.ArrayList;


public class GalleryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        init();
    }

    public void init() {
        ((MyActionBar) findViewById(R.id.action_bar)).setTitle(R.string.images);
        TownBranch branch = (TownBranch) getIntent().getSerializableExtra(Constants.TOWN_BRANCH_STRING);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        ArrayList<String> urls = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            urls.add("http://ucenter.51cto.com/avatar.php?uid=3911954&size=middle");
//        }
//        viewPager.setAdapter(new GalleryAdapter(this, urls));
        viewPager.setAdapter(new GalleryAdapter(this, branch.getUrls()));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        indicator.setSnap(true);
    }
}
