package com.yjh.qinyuan.util;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.yjh.qinyuan.R;

public class Utils {
    public static DisplayImageOptions getImageOptions() {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(0))
                .showStubImage(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheOnDisc()
                .build();
    }
}
