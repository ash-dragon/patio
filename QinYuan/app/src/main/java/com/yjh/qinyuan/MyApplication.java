package com.yjh.qinyuan;

import com.baidu.mapapi.SDKInitializer;
import com.orm.SugarApp;

public class MyApplication extends SugarApp {

    public static String sUserKey;

    public static final int TYPE_MANAGE_CENTER = 1;
    public static final int TYPE_AGENT = 2;
    public static final int TYPE_SHOP = 3;

    public static final String REQUEST_CORRECT_CODE = "1000";
    public static final String REQUEST_INCORRECT_CODE = "1001";

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
