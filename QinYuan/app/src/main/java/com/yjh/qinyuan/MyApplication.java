package com.yjh.qinyuan;

import com.baidu.mapapi.SDKInitializer;
import com.orm.SugarApp;

public class MyApplication extends SugarApp {

    public static String sUserKey;

    public static final int TYPE_MANAGE_CENTER = 1;
    public static final int TYPE_AGENT = 2;
    public static final int TYPE_SHOP = 3;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }

    public static int getUserTypeRes(int type) {
        switch (type) {
            case TYPE_MANAGE_CENTER:
                return R.string.type_manage_center;
            case TYPE_AGENT:
                return R.string.type_agent;
            case TYPE_SHOP:
                return R.string.type_shop;
        }

        return R.string.type_manage_center;
    }
}
