package com.yjh.qinyuan.widget.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class OptionItem extends RelativeLayout {

    private static final int TYPE_ARROW = 0;
    private static final int TYPE_SWITCH = 1;
    private static final int TYPE_CHECKBOX = 2;
    private static final int TYPE_LOAD = 3;
    private static final int TYPE_ADD = 4;
    private static final int TYPE_EDIT = 5;

    private HelveticaTextView mTitleTextView;
    private HelveticaTextView mRightTextView;
    private ImageView mRightImage;
    private Switch mSwitchView;
    private HelveticaEditText mEditText;
    private CheckBox mCheckBox;
    private int mRightType;

    public OptionItem(Context context) {
        super(context);
        init(context, null, 0);
    }

    public OptionItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public OptionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (isInEditMode() || attrs == null) return;

//        View rootView = LayoutInflater.from(context).inflate(R.layout.item_single_chat_room_option, this, true);
//        mTitleTextView = (HelveticaTextView) rootView.findViewById(R.id.title);
//        mRightTextView = (HelveticaTextView) rootView.findViewById(R.id.right_text);
//        mRightImage = (ImageView) rootView.findViewById(R.id.arrow);
//        mSwitchView = (Switch) rootView.findViewById(R.id.switch_view);
//        mEditText = (HelveticaEditText) rootView.findViewById(R.id.et_option);
//        mCheckBox = (CheckBox) rootView.findViewById(R.id.checkbox);
//
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OptionItem, defStyleAttr, 0);
//
//        try {
//            String titleText = a.getString(R.styleable.OptionItem_left_text);
//            String rightText = a.getString(R.styleable.OptionItem_right_text);
//            mRightType = a.getInteger(R.styleable.OptionItem_right_type, -1);
//
//            if (titleText != null) {
//                mTitleTextView.setVisibility(VISIBLE);
//                mTitleTextView.setText(titleText);
//            }
//
//            if (rightText != null) {
//                mRightTextView.setVisibility(VISIBLE);
//                mRightTextView.setText(rightText);
//            }
//
//            switch (mRightType) {
//                case TYPE_ARROW:
//                    setRightImageButton(R.drawable.arrow_list);
//                    break;
//                case TYPE_SWITCH:
//                    mSwitchView.setVisibility(VISIBLE);
//                    break;
//                case TYPE_CHECKBOX:
//                    mCheckBox.setVisibility(VISIBLE);
//                    break;
//                case TYPE_LOAD:
//                    setRightImageButton(R.drawable.btn_refresh);
//                    break;
//                case TYPE_ADD:
//                    setRightImageButton(R.drawable.btn_add);
//                    break;
//                case TYPE_EDIT:
//                    mEditText.setVisibility(VISIBLE);
//                    break;
//            }
//        } finally {
//            a.recycle();
//        }
    }

    public void setRightText(CharSequence text) {
        mRightTextView.setVisibility(VISIBLE);
        mRightTextView.setText(text);
    }

    public String getRightEditText() {
        return mEditText.getText().toString();
    }

    public void setRightImageBackground(Drawable background) {
        mRightImage.setVisibility(VISIBLE);
        mRightImage.setBackgroundDrawable(background);
    }

    private void setRightImageButton(int resId) {
        mRightImage.setVisibility(VISIBLE);
        mRightImage.setBackgroundDrawable(getResources().getDrawable(resId));
    }

    public ImageView getRightImage() {
        return mRightImage;
    }

    public Switch getSwitchView() {
        return mSwitchView;
    }

//    public void setText(String text){
//        mTitleTextView.setText(text);
//        mTitleTextView.setTextColor(getResources().getColor(R.color.dark_blue));
//        mTitleTextView.setVisibility(VISIBLE);
//    }

    public void setListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightType == TYPE_CHECKBOX) {
                    mCheckBox.performClick();
                }
            }
        });
    }
}
