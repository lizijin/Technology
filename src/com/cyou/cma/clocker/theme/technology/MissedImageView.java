
package com.cyou.cma.clocker.theme.technology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.Log;

public class MissedImageView extends SdkImageView {
    private Paint mPaint;
    // private
    private int mMissedCount = 968;
    private float mTextSize = 10;
    private int mTextColor;
    private float mDensity;
    private float mTextWidth;
    private float mTextHeight;
    private FontMetrics mFontMetrics;
    private Typeface mTypeface;
    private float mDegree;

    public MissedImageView(Context context) {
        super(context);
    }

    public MissedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextColor = Color.YELLOW;
        mTypeface = Typeface.createFromAsset(getResources().getAssets(), "font/7px2bus.ttf");
        mPaint.setTypeface(mTypeface);
        mDensity = context.getResources().getDisplayMetrics().density;
        mPaint.setTextSize(mTextSize * mDensity);
        mPaint.setColor(mTextColor);
        mFontMetrics = mPaint.getFontMetrics();
        setMissedCount(mMissedCount);
        mTextHeight = mDensity * mTextSize;

    }

    public MissedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    Matrix mMatrix;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.save();
        
        mMatrix = canvas.getMatrix();
        Log.d("mMatrix", "mMatrix------>"+mMatrix);
//        mMatrix.preRotate(mDegree, getWidth() / 2, getHeight() / 2);
//        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
//            canvas.setMatrix(mMatrix);
//        } else {
//            canvas.concat(mMatrix);
//        }

        canvas.drawText("" + mMissedCount, (getWidth() - mTextWidth) / 2,
                (getHeight() + mTextHeight) / 2, mPaint);
        canvas.restore();

        // canvas.drawText("" + mMissedCount, 20, 20, mPaint);
    }

    public void setMissedCount(int missedCount) {
        mMissedCount = missedCount;
        measureText("" + missedCount);
        invalidate();
    }

    public void setRotateDegree(float degree) {
        mDegree = degree;
//        invalidate();
    }

    public void measureText(String text) {

        // mTextHeight = (float) (Math.ceil(mFontMetrics.descent -
        // mFontMetrics.top));
        mTextWidth = mPaint.measureText(text);
    }
}
