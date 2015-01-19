package com.yjh.qinyuan.widget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yjh.qinyuan.R;

public class MyActionBar extends RelativeLayout {

    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private ImageButton mSearchButton;
    private TextView mRightText;
    private TextView mLeftText;
    private TextView mTimeStampText;
    private Context mContext;
    private ImageView mHeadImg;

    public MyActionBar(Context context) {
        super(context, null);
    }

    public MyActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

//        mContext = context;
//        LayoutInflater.from(context).inflate(R.layout.widget_actionbar, this, true);
//        mLeftButton = (ImageButton) findViewById(R.id.btn_titlebar_back);
//        mRightButton = (ImageButton) findViewById(R.id.btn_titlebar_right);
//        mSearchButton = (ImageButton) findViewById(R.id.btn_search);
//        mRightText = (TextView) findViewById(R.id.tv_titlebar_right);
//        mLeftText = (TextView) findViewById(R.id.tv_titlebar_left);
//        mTimeStampText = (TextView) findViewById(R.id.tv_titlebar_left_timestamp);
//        mHeadImg = (ImageView) findViewById(R.id.header);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyActionBar);
//
//        if (a != null) {
//            String btnText = a.getString(R.styleable.MyActionBar_btnText);
//            mRightText.setVisibility(View.VISIBLE);
//            mRightText.setText(btnText);
//        }
    }

    public ImageView getmHeadImg() {
        return mHeadImg;
    }

    public TextView getmLeftText() {
        return mLeftText;
    }

    public TextView getmTimeStampText() {
        return mTimeStampText;
    }

    public void setLeftButton(int resId, OnClickListener listener) {
        mLeftButton.setVisibility(VISIBLE);
        mLeftButton.setBackgroundDrawable(mContext.getResources().getDrawable(resId));
        mLeftButton.setOnClickListener(listener);
    }

    public void setRightButton(int resId, OnClickListener listener) {
        mRightButton.setVisibility(VISIBLE);
        mRightButton.setBackgroundDrawable(mContext.getResources().getDrawable(resId));
        mRightButton.setOnClickListener(listener);
    }

    public ImageButton getRightButton()
    {
        return mRightButton;
    }

    public void setRightButtonSelected() {
        mRightButton.setSelected(true);
    }

    public void setSearchButton(int resId, OnClickListener listener) {
        mSearchButton.setVisibility(VISIBLE);
        mSearchButton.setBackgroundDrawable(mContext.getResources().getDrawable(resId));
        mSearchButton.setOnClickListener(listener);
    }

    public void hideSearchButton()
    {
        mSearchButton.setVisibility(GONE);
    }

    public void showSearchButton()
    {
        mSearchButton.setVisibility(VISIBLE);
    }

    public void setRightText(String text, int color, OnClickListener listener) {
        mRightText.setVisibility(VISIBLE);
        mRightText.setTextColor(mContext.getResources().getColor(color));
        mRightText.setText(text);
        mRightText.setOnClickListener(listener);
    }

    public void setRightText(String text, int color) {
        mRightText.setVisibility(VISIBLE);
        mRightText.setText(text);
        mRightText.setTextColor(mContext.getResources().getColor(color));
    }

    public void setRightText(String text)
    {
        mRightText.setText(text);
    }

    public void setRightText(int resId, int color, OnClickListener listener) {
        mRightText.setVisibility(VISIBLE);
        mRightText.setTextColor(mContext.getResources().getColor(color));
        mRightText.setText(resId);
        mRightText.setOnClickListener(listener);
    }

    public void setRightText (int resId, int color) {
        mRightText.setVisibility(VISIBLE);
        mRightText.setText(resId);
        mRightText.setTextColor(mContext.getResources().getColor(color));
    }

    public void setRightText(int resId)
    {
        mRightText.setText(resId);
    }

    public void setRightTextOnClickListener (OnClickListener listener) {
        mRightText.setOnClickListener(listener);
    }

}
