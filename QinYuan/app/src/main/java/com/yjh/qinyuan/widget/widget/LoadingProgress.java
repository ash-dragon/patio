package com.yjh.qinyuan.widget.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.yjh.qinyuan.R;

public class LoadingProgress extends ProgressDialog {

    public LoadingProgress(Context context) {
        super(context);
    }

    public LoadingProgress(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }

    public static LoadingProgress show(Context ctx) {
        LoadingProgress d = new LoadingProgress(ctx,R.style.dialog);
        d.setCancelable(false);
        d.show();
        return d;
    }

    public static LoadingProgress getProgressBar(Context ctx) {
        LoadingProgress d = new LoadingProgress(ctx,R.style.dialog);
        d.setCancelable(false);
        return d;
    }

    public static LoadingProgress getLoadingProgress(Context ctx) {
        LoadingProgress d = new LoadingProgress(ctx,R.style.dialog);
        d.setCancelable(false);
        return d;
    }


}
