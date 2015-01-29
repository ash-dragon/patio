package com.yjh.qinyuan.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

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

    public static void phoneCall(Context context, String phone) {
        if (!TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            context.startActivity(intent);
        }
    }
}
