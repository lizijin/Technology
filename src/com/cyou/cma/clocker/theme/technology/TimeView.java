
package com.cyou.cma.clocker.theme.technology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.cyou.cma.clocker.theme.sdk.measuer.DefaultMeasureBase;
import com.cyou.cma.clocker.theme.sdk.utils.DateHelper;
import com.cyou.cma.clocker.theme.sdk.utils.DateHelper.TimeFormat;
import com.cyou.cma.clocker.theme.technology.graphics.ScheduleDrawable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeView extends View {
    /**
     * 时间刻度最外面的
     */
    private Drawable mTimeScale;
    private Drawable mTimeOuterRing;
    private Drawable mTimeOuterRingLight;
    private Drawable mTimeScaleLight;

    private ScheduleDrawable mLeftTopYellowScheduleDrawable;
    private ScheduleDrawable mLeftBottomYellowScheduleDrawable;
    private ScheduleDrawable mRightTopYellowScheduleDrawable;
    private ScheduleDrawable mRightBottomYellowScheduleDrawable;
    /**
     * 中间的时间虚线
     */
    private Drawable mTimeDashed;
    private int mMeasureWidth;
    private int mMeasureHeight;
    private boolean mShowHilight = false;

    private int mDashedWidth;
    private int mDashedHeight;

    private Rect mOuterRingRect;
    private Rect mDashedRect;

    private Paint mTimePaint;
    private Paint mDatePaint;
    private Paint mAmOrPmPaint;

    private final float mTimeSize = 24;
    private final float mDateSize = 12;
    private final float mAmOrPmSize = 14;
    private float mTimeSizeInPixel;
    private float mDateSizeInPixel;

    private String mTimeText = "";
    private String mDateText = "06/10/14";
    private String mAmOrPmText = "AM";

    private Typeface mTypeface;
    private FontMetrics mTimeFontMetrics;
    private FontMetrics mDateFontMetrics;
    private float mTimeTextHeight;
    private float mDateTextHeight;
    private float mTimeTextWidth;
    private float mDateTextWidth;
    private float mAmOrPmTextWidth;
    private float mDegree;
    private final float mTimeMargintop = 117 * 2;
    private final float mDateMargintop = 134 * 2;

    private float mTimeMargintopInPixel;
    private float mDateMargintopInPixel;
    private DisplayMetrics mDisplayMetrics;

    private float mTimeTextOriginalX;
    private float mDateTextOriginalX;
    private float mAmOrPmOriginalX;

    private int mAmOrPmMarginTop = 190;
    private int mAmOrPmMarginLeft = 152;

    private final int mDrawableWith = 460;
    private final int mDrawableHeight = 460;
    private final int mDrawableWidthSmaller = 192;
    private final int mDrawableHeightSmaller = 192;
    private int mCornMarginLeft = 44;
    private int mCornMarginTop = 26;

    private DefaultMeasureBase mDefaultMeasureBase;
    private Paint paint;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM/dd/yy");

    private TimeFormat timeFormat = new TimeFormat();

    private Rect mLeftTopCorner = new Rect();
    private Rect mLeftBottomCorner = new Rect();
    private Rect mRightTopCorner = new Rect();
    private Rect mRightBottomCorner = new Rect();

    private int mCornerTop = 160;
    private int mCornerLeft = 180;
    private int mCornerRight = 272;
    private int mCornerBottom = 298;
    private int mCornerWidth = 9;

    private int mCornerLeftDown = 198;
    private int mCornerRightDown = 252;

    private boolean mShouldDraw = true;

    public TimeView(Context context) {
        super(context);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        mDefaultMeasureBase = DefaultMeasureBase.getDefaultMeasureBase();

        mTimeDashed = getResources().getDrawable(R.drawable.time_dashed);
        mTimeScale = getResources().getDrawable(R.drawable.time_scale);
        mTimeOuterRingLight = getResources().getDrawable(R.drawable.time_outer_ring_light);
        mTimeOuterRing = getResources().getDrawable(R.drawable.time_outer_ring);
        mTimeScaleLight = getResources().getDrawable(R.drawable.time_scale_light);

        Drawable mLeftTopYellowDrawable = getResources().getDrawable(R.drawable.left_top_yellow);
        Drawable mLeftBottomYellowDrawable = getResources().getDrawable(
                R.drawable.left_bottom_yellow);
        Drawable mRightTopYellowDrawable = getResources().getDrawable(R.drawable.right_top_yellow);
        Drawable mRightBottomYellowDrawable = getResources().getDrawable(
                R.drawable.right_bottom_yellow);

        mCornerTop = (int) (mCornerTop * mDefaultMeasureBase.getWidthRato(context));
        mCornerLeft = (int) (mCornerLeft * mDefaultMeasureBase.getWidthRato(context));
        mCornerLeftDown = (int) (mCornerLeftDown * mDefaultMeasureBase.getWidthRato(context));
        mCornerRight = (int) (mCornerRight * mDefaultMeasureBase.getWidthRato(context));
        mCornerRightDown = (int) (mCornerRightDown * mDefaultMeasureBase.getWidthRato(context));
        mCornerBottom = (int) (mCornerBottom * mDefaultMeasureBase.getWidthRato(context));
        mCornerWidth = (int) (9 * mDefaultMeasureBase.getWidthRato(context));
        mLeftTopCorner.set(mCornerLeft, mCornerTop, mCornerLeft + mCornerWidth, mCornerTop
                + mCornerWidth);
        mLeftBottomCorner.set(mCornerLeftDown, mCornerBottom, mCornerLeftDown + mCornerWidth,
                mCornerBottom + mCornerWidth);
        mRightTopCorner.set(mCornerRight, mCornerTop, mCornerRight + mCornerWidth, mCornerTop
                + mCornerWidth);
        mRightBottomCorner.set(mCornerRightDown, mCornerBottom, mCornerRightDown + mCornerWidth,
                mCornerBottom + mCornerWidth);

        mLeftTopYellowDrawable.setBounds(mLeftTopCorner);
        mLeftBottomYellowDrawable.setBounds(mLeftBottomCorner);
        mRightTopYellowDrawable.setBounds(mRightTopCorner);
        mRightBottomYellowDrawable.setBounds(mRightBottomCorner);

        mLeftTopYellowScheduleDrawable = new ScheduleDrawable(mLeftTopYellowDrawable);
        mLeftBottomYellowScheduleDrawable = new ScheduleDrawable(mLeftBottomYellowDrawable);
        mRightTopYellowScheduleDrawable = new ScheduleDrawable(mRightTopYellowDrawable);
        mRightBottomYellowScheduleDrawable = new ScheduleDrawable(mRightBottomYellowDrawable);

        mLeftTopYellowScheduleDrawable.startScheduleAniamtion();
        mLeftBottomYellowScheduleDrawable.startScheduleAniamtion();
        mRightTopYellowScheduleDrawable.startScheduleAniamtion();
        mRightBottomYellowScheduleDrawable.startScheduleAniamtion();
        mMeasureWidth = (int) (mDrawableWith * mDefaultMeasureBase.getWidthRato(context));
        mMeasureHeight = (int) (mDrawableHeight * mDefaultMeasureBase.getWidthRato(context));

        mCornMarginLeft = (int) (mCornMarginLeft * mDefaultMeasureBase.getWidthRato(context));
        mCornMarginTop = (int) (mCornMarginTop * mDefaultMeasureBase.getWidthRato(context));

        mDashedWidth = (int) (mDrawableWidthSmaller * mDefaultMeasureBase.getWidthRato(context));
        mDashedHeight = (int) (mDrawableHeightSmaller * mDefaultMeasureBase.getWidthRato(context));

        mAmOrPmMarginTop = (int) (mAmOrPmMarginTop * mDefaultMeasureBase.getWidthRato(context));
        mAmOrPmMarginLeft = (int) (mAmOrPmMarginLeft * mDefaultMeasureBase.getWidthRato(context));

        mDisplayMetrics = getResources().getDisplayMetrics();
        int dashedOriginalX = (mMeasureWidth - mDashedWidth) / 2;
        int dashedOriginalY = (mMeasureHeight - mDashedHeight) / 2;

        mDashedRect = new Rect(dashedOriginalX, dashedOriginalY, dashedOriginalX + mDashedWidth,
                mDashedHeight + dashedOriginalY);
        mOuterRingRect = new Rect(0, 0, mMeasureWidth, mMeasureHeight);
        mTimeScale.setBounds(mOuterRingRect);
        mTimeOuterRing.setBounds(mOuterRingRect);
        mTimeDashed.setBounds(mDashedRect);
        mTimeOuterRingLight.setBounds(mOuterRingRect);
        mTimeScaleLight.setBounds(mOuterRingRect);

        mTimePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimePaint.setColor(Color.YELLOW);
        mDatePaint.setColor(Color.WHITE);
        mAmOrPmPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAmOrPmPaint.setColor(Color.YELLOW);

        mTimeSizeInPixel = mDisplayMetrics.density * mTimeSize;
        mDateSizeInPixel = mDisplayMetrics.density * mDateSize;
        mTimePaint.setTextSize(mTimeSizeInPixel);
        mDatePaint.setTextSize(mDateSizeInPixel);
        mAmOrPmPaint.setTextSize(mAmOrPmSize);

        mTypeface = Typeface.createFromAsset(getResources().getAssets(), "font/7px2bus.ttf");
        mTimePaint.setTypeface(mTypeface);
        mDatePaint.setTypeface(mTypeface);
        mAmOrPmPaint.setTypeface(mTypeface);
        mTimeFontMetrics = mTimePaint.getFontMetrics();
        mDateFontMetrics = mDatePaint.getFontMetrics();
        mTimeTextHeight = (float) (Math.ceil(mTimeFontMetrics.descent - mTimeFontMetrics.top));
        mDateTextHeight = (float) (Math.ceil(mDateFontMetrics.descent - mDateFontMetrics.top));

        mTimeMargintopInPixel = mDefaultMeasureBase.getWidthRato(context) * mTimeMargintop;
        mDateMargintopInPixel = mDefaultMeasureBase.getWidthRato(context) * mDateMargintop;
        postDelayed(mRunnable, 300);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
        }
    };

    public void showHilightDrawable(boolean showHilight) {
        this.mShowHilight = showHilight;
        invalidate();
    }

    public void setRotateDegree(float degree) {
        mDegree = degree;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((mMeasureWidth), (mMeasureHeight));
    }

    public void onTimeChanged() {
        invalidate(mDashedRect);
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {

    }

    public void getTime() { // 重新测量下高度
        DateHelper.getTimeFormat(getContext(), timeFormat);
        mTimeText = timeFormat.toString();
        mAmOrPmText = timeFormat.getAmOrPm();
        mTimeTextWidth = mTimePaint.measureText(mTimeText);
        mAmOrPmTextWidth = mAmOrPmPaint.measureText(mAmOrPmText);
        mTimeTextOriginalX = (mMeasureWidth - mTimeTextWidth) / 2;
        mAmOrPmOriginalX = mMeasureWidth - mAmOrPmTextWidth - mAmOrPmMarginLeft;

    }

    Date now = null;

    public void getDate() {// 重新测量下高度
        now = new Date();// 获取系统当前时间
        mDateText = mDateFormat.format(now);
        mDateTextWidth = mDatePaint.measureText(mDateText);
        mDateTextOriginalX = (mMeasureWidth - mDateTextWidth) / 2;
    }

    Matrix mMatrix;

    @Override
    protected void onDraw(Canvas canvas) {
        if (!mShouldDraw)
            return;
        super.onDraw(canvas);
        // 跟他相同的方向
        mLeftTopYellowScheduleDrawable.draw(canvas);
        mLeftBottomYellowScheduleDrawable.draw(canvas);
        mRightTopYellowScheduleDrawable.draw(canvas);
        mRightBottomYellowScheduleDrawable.draw(canvas);
        mMatrix = canvas.getMatrix();
        if (mShowHilight) {
            mTimeOuterRingLight.draw(canvas);
            mTimeScaleLight.draw(canvas);
        } else {
            canvas.save();

            mMatrix.preRotate(mDegree, mMeasureWidth / 2, mMeasureHeight / 2);
            if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
                canvas.setMatrix(mMatrix);
            } else {
                canvas.concat(mMatrix);
            }

            mTimeScale.draw(canvas);
            canvas.restore();
        }

        // 跟他相反的方向
        canvas.save();
        mMatrix.preRotate(mDegree, mMeasureWidth / 2, mMeasureHeight / 2);
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
            canvas.setMatrix(mMatrix);
        } else {
            canvas.concat(mMatrix);
        }
        mTimeDashed.draw(canvas);
        canvas.restore();

        // 跟他的方向
        canvas.save();
        mMatrix = canvas.getMatrix();

        mMatrix.preRotate(-mDegree, mMeasureWidth / 2, mMeasureHeight / 2);
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
            canvas.setMatrix(mMatrix);
        } else {
            canvas.concat(mMatrix);
        }
        mTimeOuterRing.draw(canvas);
        canvas.restore();
        getDate();
        getTime();// TODO 在这里需要重新测量时间宽度
        canvas.drawText(mTimeText, mTimeTextOriginalX, mTimeMargintopInPixel, mTimePaint);
        canvas.drawText(mDateText, mDateTextOriginalX, mDateMargintopInPixel, mDatePaint);
        canvas.drawText(mAmOrPmText, mAmOrPmOriginalX, mAmOrPmMarginTop, mAmOrPmPaint);
        invalidate();
    }

    public void onResume() {
        mShouldDraw = true;
        invalidate();
    }

    public void onPause() {
        mShouldDraw = false;
        invalidate();
    }
}
