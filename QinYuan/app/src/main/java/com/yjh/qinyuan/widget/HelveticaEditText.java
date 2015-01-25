package com.yjh.qinyuan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.yjh.qinyuan.R;

public class HelveticaEditText extends EditText {

    private static final int NORMAL = 0;
    private static final int BOLD = 1;
    private static final int ITALICS = 2;

    public HelveticaEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public HelveticaEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public HelveticaEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (isInEditMode()) return;

        String fontName = "Helvetica.ttf";

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().
                    obtainStyledAttributes(attrs, R.styleable.Helvetica, defStyleAttr, 0);
            int fontType = typedArray.getInteger(R.styleable.Helvetica_text_type, 0);

            switch (fontType) {
                case NORMAL:
                    fontName = "Helvetica.ttf";
                    break;
                case BOLD:
                    fontName = "Helvetica-Bold.ttf";
                    break;
                case ITALICS:
                    fontName = "Helvetica-Oblique.ttf";
                    break;
            }
        }

        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName), defStyleAttr);
    }
}
