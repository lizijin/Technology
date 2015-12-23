
package com.cyou.cma.clocker.theme.technology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.cyou.cma.clocker.theme.sdk.measuer.DefaultMeasureBase;
import com.cyou.cma.clocker.theme.sdk.measuer.MeasureBase;
import com.cyou.cma.clocker.theme.technology.graphics.AnimateDrawable;

public class UnlockImageView extends ImageView {
    private String TAG = "UnlockImageView";
    private AnimateDrawable mAnimateDrawableUnlock;
    private AnimateDrawable mAnimateDrawableUnlock1;
    private AnimateDrawable mAnimateDrawableUnlock2;
    private AnimateDrawable mAnimateDrawableUnlock3;
    private AnimateDrawable mAnimateDrawableUnlock4;
    private AnimateDrawable mAnimateDrawableUnlock5;
    private AnimateDrawable mAnimateDrawableUnlock6;
    private Drawable mDrawableUnlockLight;
    private boolean mShouldDraw = true;

    private MeasureBase mDefaultMeasureBase;
    /**
     * 顺时针
     */
    private RotateAnimation mClockwiseAnimation;
    /**
     * 逆时针
     */
    private RotateAnimation mAnticlockwiseAnimation;

    private float mDensity;

    private int mMeasureWidth;
    private int mMeasureHeight;

    private Rect mRect = new Rect();

    private Paint mPaint;
    private String text = "";
    private float textWidth;
    private float textHeight;

    public UnlockImageView(Context context) {
        super(context);
    }

    public UnlockImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDensity = context.getResources().getDisplayMetrics().density;
        mDefaultMeasureBase = DefaultMeasureBase.getDefaultMeasureBase();
        Drawable mDrawableUnlock = getResources().getDrawable(R.drawable.unlock_ring);
        Drawable mDrawableUnlock1 = getResources().getDrawable(R.drawable.unlock_1);
        Drawable mDrawableUnlock2 = getResources().getDrawable(R.drawable.unlock_2);
        Drawable mDrawableUnlock3 = getResources().getDrawable(R.drawable.unlock_3);
        Drawable mDrawableUnlock4 = getResources().getDrawable(R.drawable.unlock_4);
        Drawable mDrawableUnlock5 = getResources().getDrawable(R.drawable.unlock_5);
        Drawable mDrawableUnlock6 = getResources().getDrawable(R.drawable.unlock_6);
        mDrawableUnlockLight = getResources().getDrawable(R.drawable.unlock_ring_light);

        mMeasureWidth = (int) (mDrawableUnlock.getIntrinsicWidth()
                * mDefaultMeasureBase.getWidthRato(context) * mDefaultMeasureBase
                .getTargetPixel2BasePixelRato(mDensity));
        mMeasureHeight = (int) (mDrawableUnlock.getIntrinsicHeight()
                * mDefaultMeasureBase.getWidthRato(context) * mDefaultMeasureBase
                .getTargetPixel2BasePixelRato(mDensity));
        mRect.set(0, 0, mMeasureWidth, mMeasureHeight);
        mDrawableUnlock.setBounds(mRect);
        mDrawableUnlock1.setBounds(mRect);
        mDrawableUnlock2.setBounds(mRect);
        mDrawableUnlock3.setBounds(mRect);
        mDrawableUnlock4.setBounds(mRect);
        mDrawableUnlock5.setBounds(mRect);
        mDrawableUnlock6.setBounds(mRect);
        mDrawableUnlockLight.setBounds(mRect);
        mClockwiseAnimation = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mClockwiseAnimation.setDuration(1000);
        mClockwiseAnimation.setInterpolator(new LinearInterpolator());
        mClockwiseAnimation.setRepeatMode(Animation.RESTART);
        mClockwiseAnimation.setRepeatCount(Animation.INFINITE);
        mClockwiseAnimation.initialize(mMeasureWidth, mMeasureHeight, 0, 0);
        mClockwiseAnimation.cancel();
        mAnticlockwiseAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mAnticlockwiseAnimation.setDuration(1000);
        mAnticlockwiseAnimation.setInterpolator(new LinearInterpolator());
        mAnticlockwiseAnimation.setRepeatMode(Animation.RESTART);
        mAnticlockwiseAnimation.setRepeatCount(Animation.INFINITE);
        mAnticlockwiseAnimation.initialize(mMeasureWidth, mMeasureHeight, 0, 0);
        mAnticlockwiseAnimation.cancel();

        mAnimateDrawableUnlock = new AnimateDrawable(mDrawableUnlock, mClockwiseAnimation);
        mAnimateDrawableUnlock1 = new AnimateDrawable(mDrawableUnlock1, mAnticlockwiseAnimation);
        mAnimateDrawableUnlock2 = new AnimateDrawable(mDrawableUnlock2, mClockwiseAnimation);
        mAnimateDrawableUnlock3 = new AnimateDrawable(mDrawableUnlock3, mAnticlockwiseAnimation);
        mAnimateDrawableUnlock4 = new AnimateDrawable(mDrawableUnlock4, mClockwiseAnimation);
        mAnimateDrawableUnlock5 = new AnimateDrawable(mDrawableUnlock5, mAnticlockwiseAnimation);
        mAnimateDrawableUnlock6 = new AnimateDrawable(mDrawableUnlock6, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(10 * mDensity);
        textHeight = 10 * mDensity;
        mPaint.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "font/7px2bus.ttf"));


    }

    public UnlockImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mShouldDraw) {
            super.onDraw(canvas);

            mAnimateDrawableUnlock1.draw(canvas);
            mAnimateDrawableUnlock2.draw(canvas);
            mAnimateDrawableUnlock3.draw(canvas);
            mAnimateDrawableUnlock4.draw(canvas);
            if (mPluggin) {

                canvas.drawText(+mLevel + "%", (getWidth() - textWidth) / 2, getHeight() / 2,
                        mPaint);
            } else {
                mAnimateDrawableUnlock5.draw(canvas);
            }
            mAnimateDrawableUnlock6.draw(canvas);

            if (mHighlight) {
                mDrawableUnlockLight.draw(canvas);
            } else {
                mAnimateDrawableUnlock.draw(canvas);
            }
            invalidate();
        }
    }

    private boolean mHighlight;

    public void setHighlight(boolean highlight) {
        this.mHighlight = highlight;
        invalidate();
    }

    private boolean mPluggin = false;
    private int mLevel;

    public void setPluggin(boolean pluggin, int level) {
        if (mPluggin != pluggin) {
            if (pluggin) {
                mLevel = level;
                text = mLevel + "%";
                textWidth = mPaint.measureText(text);
                startAnimation();
            } else {
                cancleAnimation();
            }
        }
        this.mPluggin = pluggin;
    }

    public void startAnimation() {
        if (mClockwiseAnimation != null) {
            mClockwiseAnimation.cancel();
            mClockwiseAnimation.start();
        }
        if (mAnticlockwiseAnimation != null) {
            mAnticlockwiseAnimation.cancel();
            mAnticlockwiseAnimation.start();
        }
    }

    public void cancleAnimation() {

        if (mClockwiseAnimation != null) {
            mClockwiseAnimation.cancel();
        }
        if (mAnticlockwiseAnimation != null) {
            mAnticlockwiseAnimation.cancel();
        }

    }

    public void onResume() {
        mShouldDraw = true;
        if (mPluggin) {
            cancleAnimation();
        }
        invalidate();

    }

    public void onPause() {
        mShouldDraw = false;
        if (mPluggin) {
            startAnimation();
        }
        invalidate();
    }
}
