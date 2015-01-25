package com.yjh.qinyuan.common;

import android.app.Fragment;
import android.view.View;

import com.yjh.qinyuan.widget.LoadingProgress;
import com.yjh.qinyuan.widget.MyActionBar;

public abstract class BaseFragment extends Fragment {

    public LoadingProgress mProgressBar;
    public View mRootView;

    public abstract void init();

    public MyActionBar getActionBar() {
        return ((BaseActivity) getActivity()).getMyActionBar();
    }
}
