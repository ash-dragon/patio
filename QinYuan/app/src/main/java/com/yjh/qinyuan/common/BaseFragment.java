package com.yjh.qinyuan.common;

import android.app.Fragment;
import android.view.View;

import com.yjh.qinyuan.widget.LoadingProgress;

public abstract class BaseFragment extends Fragment {

    public LoadingProgress mProgressBar;

    public abstract void init(View rootView);
}
