package com.yjh.qinyuan.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sheery on 1/8/15.
 */
public class ColorView extends View {

    Paint paint = new Paint();

    public ColorView(Context context) {
        super(context);
    }

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawCircle(30, 30, 30, paint);
    }
}
