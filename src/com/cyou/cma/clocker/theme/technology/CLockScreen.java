
package com.cyou.cma.clocker.theme.technology;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cyou.cma.clocker.theme.sdk.KeyguardCallback;
import com.cyou.cma.clocker.theme.sdk.SdkKeyguard;
import com.cyou.cma.clocker.theme.sdk.ViewCompat;
import com.cyou.cma.clocker.theme.sdk.measuer.DefaultMeasureBase;
import com.cyou.cma.clocker.theme.service.AppDownloadServcie;
import com.cyou.cma.clocker.theme.technology.algorithm.AlgorithmUtils;
import com.cyou.cma.clocker.theme.technology.algorithm.Line;
import com.cyou.cma.clocker.theme.technology.algorithm.PointFInQuadrant;
import com.cyou.cma.clocker.theme.technology.algorithm.Quadrant;
import com.cyou.cma.clocker.theme.technology.bitmap.WallpaperBitmapBase;
import com.cyou.cma.clocker.theme.technology.bitmap.WallpaperBitmapFactory;

/**
 * 锁屏主界面
 * 
 * @author jiangbin
 */
public class CLockScreen extends FrameLayout implements SdkKeyguard {
    private static final String TAG = "CLockScreen in technology";

    private int mUnreadCall;
    private int mUnreadMessage;
    private Drawable mBackground;
    private TimeView mTimeView;
    private ImageView mImageViewCall;
    private ImageView mImageViewMessage;

    private SdkImageView mImageViewCallUnder;
    private SdkImageView mImageViewMessageUnder;
    private ImageView mImageViewCallUnder2;
    private ImageView mImageViewMessageUnder2;
    private UnlockImageView mImageViewUnlock;
    private SdkImageView mImageViewArrow;
    private MissedImageView mImageViewMissedMessage;
    private MissedImageView mImageViewMissedCall;

    private UpSurfaceView mUpSurfaceView;
    private DownSurfaceView mDownSurfaceView;

    private Matrix mMatrix;

    private Context mContext;

    private Rect mHitRectCall = new Rect();
    private Rect mHitRectMessage = new Rect();
    private Rect mHitRectTimeView = new Rect();
    private Rect mHitRectCallUnder = new Rect();
    private Rect mHitRectMessageUnder = new Rect();
    private Rect mHitRectImageViewUnlock = new Rect();

    private boolean mCallImageViewPressed = false;
    private boolean mMessageImageViewPressed = false;
    private boolean mCallImageViewUnderPressed = false;
    private boolean mMessageImageViewUnderPressed = false;
    private boolean mUnlockImageViewPressed = false;
    private boolean mRotating = false;// 是否已经旋转了
    private boolean mUnlock = false;// 是否解锁
    private int mUnlockType = -1;
    private static final int UNLOCK_MESSAGE = 1;
    private static final int UNLOCK_CALL = 2;
    private static final int UNLOCK_HOME = 3;
    private Vibrator mVibrator;
    private float mTouchX;
    private float mTouchY;

    private int mCenterXCall;
    private int mCenterYCall;
    private int mCenterXMessage;
    private int mCenterYMessage;
    private int mCenterXTimeView;
    private int mCenterYTimeView;

    private float mDegree;

    private Line mMoveLine = new Line();
    private Line mCallLine = new Line();
    private Line mMessageLine = new Line();

    private Line mLeftTrace = new Line();
    private Line mRightTrace = new Line();
    private PointF mLeftTraceStartPointF = new PointF();
    private PointF mLeftTraceEndPointF = new PointF();
    private PointF mRightTraceStartPointF = new PointF();
    private PointF mRightTraceEndPointF = new PointF();

    private PointF mMovePoint = new PointF();
    private PointF mCallPoint = new PointF();
    private PointF mMessagePoint = new PointF();
    private PointF mTimeViewPoint = new PointF();

    private Quadrant mMovePointQuadrant = new Quadrant(-1);
    private Quadrant mCallPointQuadrant = new Quadrant(-1);
    private Quadrant mMessagePointQuadrant = new Quadrant(-1);
    private PointFInQuadrant mMovePointPointFInQuadrant = new PointFInQuadrant(mMovePoint,
            mMovePointQuadrant);
    private PointFInQuadrant mCallPointPointFInQuadrant = new PointFInQuadrant(mCallPoint,
            mCallPointQuadrant);
    private PointFInQuadrant mMessagePointPointFInQuadrant = new PointFInQuadrant(mMessagePoint,
            mMessagePointQuadrant);
    private PointFInQuadrant mTimeViewPointPointFInQuadrant = new PointFInQuadrant(mTimeViewPoint,
            null);

    private Paint mPaint;
    private Path mLeftPath = new Path();
    private Path mRightPath = new Path();

    private float mLeftStartPointX = 144;
    private float mUpY = 880;
    private float mLeftMiddlePointX = 216;
    private float mLeftEndPointX = 334;
    private float mDownY = 1048;
    private float mRightStartPointX = 576;
    private float mRightMiddlePointX = 504;
    private float mRightEndPointX = 386;

    private float mUnderCallStartX = 100;
    private float mUnderCallStartY = 794;
    private float mUnderCallEndY = 1048;
    private float mUnderCallEndX = 278;

    private float mUnderMessageStartX = 620;
    private float mUnderMessageStartY = 794;
    private float mUnderMessageEndX = 442;
    private float mUnderMessageEndY = 1048;

    private float mArrowMarginTop = 870;
    private float mArrowMarginLeft = 352;

    private float mWidthRato;

    private float mMissedCallMarginLeft = 80;
    private float mMissedCallMarginTop = 375;

    private float mMissedMessageMarginRight = 64;
    private float mMissedMessageMarginTop = 424;

    private KeyguardCallback mKeyguardCallback;
    private DefaultMeasureBase mMeasureBase;
    private float mDensity;

    public CLockScreen(Context context) {
        super(context);
    }

    @SuppressWarnings("deprecation")
    public CLockScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mKeyguardCallback = new KeyguardCallback(mContext);
        mMeasureBase = DefaultMeasureBase.getDefaultMeasureBase();
        mWidthRato = mMeasureBase.getWidthRato(context);
        mBackground = new BitmapDrawable(new WallpaperBitmapBase().getBitmap(context,
                WallpaperBitmapFactory.getBitmap(context, R.drawable.background)));
        ViewCompat.setBackground(this, mBackground);

        mDensity = context.getResources().getDisplayMetrics().density;
        mUnderCallStartX = 100 * mWidthRato;
        mUnderCallStartY = 794 * mWidthRato;
        mUnderCallEndY = 1048 * mWidthRato;
        mUnderCallEndX = 278 * mWidthRato;

        mArrowMarginTop = mArrowMarginTop * mWidthRato;
        mArrowMarginLeft = mArrowMarginLeft * mWidthRato;

        mUnderMessageStartX = 620 * mWidthRato;
        mUnderMessageStartY = 794 * mWidthRato;
        mUnderMessageEndX = 442 * mWidthRato;
        mUnderMessageEndY = 1048 * mWidthRato;

        mLeftStartPointX = mLeftStartPointX * mWidthRato;
        mUpY = mUpY * mWidthRato;
        mLeftMiddlePointX = mLeftMiddlePointX * mWidthRato;
        mLeftEndPointX = mLeftEndPointX * mWidthRato;
        mDownY = mDownY * mWidthRato;
        mRightStartPointX = mRightStartPointX * mWidthRato;
        mRightMiddlePointX = mRightMiddlePointX * mWidthRato;
        mRightEndPointX = mRightEndPointX * mWidthRato;

        mLeftTraceStartPointF.set(mUnderCallStartX, mUnderCallStartY);
        mLeftTraceEndPointF.set(mUnderCallEndX, mUnderCallEndY);
        mLeftTrace.setPoints(mLeftTraceStartPointF, mLeftTraceEndPointF);

        mRightTraceStartPointF.set(mUnderMessageStartX, mUnderMessageStartY);
        mRightTraceEndPointF.set(mUnderMessageEndX, mUnderMessageEndY);
        mRightTrace.setPoints(mRightTraceStartPointF, mRightTraceEndPointF);

        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0x3300FF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(2 * mWidthRato);
        mLeftPath.moveTo(mLeftStartPointX, mUpY);
        mLeftPath.lineTo(mLeftMiddlePointX, mUpY);
        mLeftPath.lineTo(mLeftEndPointX, mDownY);
        mLeftPath.close();

        mRightPath.moveTo(mRightStartPointX, mUpY);
        mRightPath.lineTo(mRightMiddlePointX, mUpY);
        mRightPath.lineTo(mRightEndPointX, mDownY);
        mRightPath.setFillType(FillType.WINDING);
        mRightPath.close();
        context.startService(new Intent(context, AppDownloadServcie.class));
    }

    public CLockScreen(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mImageViewCall.layout((int) (70 * mWidthRato), (int) (440 * mWidthRato),
                (int) (mImageViewCall.getWidth() + 70 * mWidthRato),
                (int) (440 * mWidthRato + mImageViewCall.getHeight()));
        mImageViewCall.getHitRect(mHitRectCall);

        mImageViewMessage.layout(
                (int) (getWidth() - 98 * mWidthRato - mImageViewMessage.getWidth()),
                (int) (300 * mWidthRato), (int) (getWidth() - 98 * mWidthRato),
                (int) (300 * mWidthRato + mImageViewMessage.getHeight()));
        mImageViewMessage.getHitRect(mHitRectMessage);

        mImageViewMissedCall.layout((int) (mMissedCallMarginLeft * mWidthRato),
                (int) (mMissedCallMarginTop * mWidthRato),
                (int) (mImageViewMissedCall.getWidth() + mMissedCallMarginLeft * mWidthRato),
                (int) (mMissedCallMarginTop * mWidthRato + mImageViewMissedCall.getHeight()));

        mImageViewMissedMessage
                .layout((int) (getWidth() - mMissedMessageMarginRight * mWidthRato - mImageViewMissedMessage
                        .getWidth()), (int) (mMissedMessageMarginTop * mWidthRato),
                        (int) (getWidth() - mMissedMessageMarginRight * mWidthRato),
                        (int) (mMissedMessageMarginTop * mWidthRato + mImageViewMissedMessage
                                .getHeight()));

        mTimeView.layout((getWidth() - mTimeView.getWidth()) / 2, (int) (300 * mWidthRato),
                (getWidth() + mTimeView.getWidth()) / 2,
                (int) (300 * mWidthRato) + mTimeView.getHeight());
        mTimeView.getHitRect(mHitRectTimeView);

        mImageViewCallUnder.layout((int) (100 * mWidthRato), (int) (794 * mWidthRato),
                (int) (100 * mWidthRato + mImageViewCallUnder.getWidth()),
                (int) (794 * mWidthRato + mImageViewCallUnder.getHeight()));
        mImageViewCallUnder2.layout((int) (100 * mWidthRato), (int) (-200 * mWidthRato),
                (int) (100 * mWidthRato + mImageViewCallUnder.getWidth()),
                (int) (-200 * mWidthRato + mImageViewCallUnder.getHeight()));

        mImageViewCallUnder.getHitRect(mHitRectCallUnder);

        mImageViewArrow.layout(mImageViewArrow.getLeft(), (int) (870 * mWidthRato),
                mImageViewArrow.getRight(), (int) (870 * mWidthRato + mImageViewArrow.getHeight()));

        mImageViewMessageUnder.layout(
                (int) (getWidth() - mImageViewMessageUnder.getWidth() - 100 * mWidthRato),
                (int) (794 * mWidthRato), (int) (getWidth() - 100 * mWidthRato),
                (int) (794 * mWidthRato + mImageViewMessageUnder.getHeight()));

        mImageViewMessageUnder2.layout(
                (int) (getWidth() - mImageViewMessageUnder.getWidth() - 100 * mWidthRato),
                (int) (-200 * mWidthRato), (int) (getWidth() - 100 * mWidthRato), (int) (-200
                        * mWidthRato + mImageViewMessageUnder.getHeight()));
        mImageViewMessageUnder.getHitRect(mHitRectMessageUnder);

        mImageViewUnlock.layout((getWidth() - mImageViewUnlock.getWidth()) / 2,
                (int) (964 * mWidthRato), (getWidth() + mImageViewUnlock.getWidth()) / 2,
                (int) (964 * mWidthRato + mImageViewUnlock.getHeight()));
        mImageViewUnlock.getHitRect(mHitRectImageViewUnlock);
        mUpSurfaceView.layout((int) (260 * mWidthRato), (int) (54 * mWidthRato),
                (int) (260 * mWidthRato + mUpSurfaceView.getWidth()),
                (int) (54 * mWidthRato + mUpSurfaceView.getHeight()));
        mDownSurfaceView.layout((int) (7 * mWidthRato), (int) (960 * mWidthRato),
                (int) (7 * mWidthRato + mDownSurfaceView.getWidth()),
                (int) (960 * mWidthRato + mDownSurfaceView.getHeight()));
        mCenterXCall = mHitRectCall.centerX();
        mCenterYCall = mHitRectCall.centerY();
        mCenterXMessage = mHitRectMessage.centerX();
        mCenterYMessage = mHitRectMessage.centerY();
        mCenterXTimeView = mHitRectTimeView.centerX();
        mCenterYTimeView = mHitRectTimeView.centerY();

        mCallPoint.set(mCenterXCall, mCenterYCall);
        mMessagePoint.set(mCenterXMessage, mCenterYMessage);
        mTimeViewPoint.set(mCenterXTimeView, mCenterYTimeView);

        mCallLine.setPoints(mTimeViewPointPointFInQuadrant, mCallPointPointFInQuadrant);
        mMessageLine.setPoints(mTimeViewPointPointFInQuadrant, mMessagePointPointFInQuadrant);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTimeView = (TimeView) findViewById(R.id.timeview);
        mImageViewCall = (ImageView) findViewById(R.id.call_imageview);
        mImageViewMessage = (ImageView) findViewById(R.id.message_imageview);
        mImageViewCallUnder = (SdkImageView) findViewById(R.id.call_under_imageview);
        mImageViewMessageUnder = (SdkImageView) findViewById(R.id.message_under_imageview);
        mImageViewUnlock = (UnlockImageView) findViewById(R.id.unlock_imageview);
        mImageViewMessageUnder2 = (ImageView) findViewById(R.id.message_under_imageview2);
        mImageViewCallUnder2 = (ImageView) findViewById(R.id.call_under_imageview2);
        mImageViewArrow = (SdkImageView) findViewById(R.id.arrow_imageview);
        ((AnimationDrawable) mImageViewArrow.getBackground()).start();
        mImageViewMissedMessage = (MissedImageView) findViewById(R.id.message_missed);
        mImageViewMissedCall = (MissedImageView) findViewById(R.id.call_missed);
        // mImageViewMissedCall.setMissedCount(8);

        mUpSurfaceView = (UpSurfaceView) findViewById(R.id.up_surfaceview);
        mDownSurfaceView = (DownSurfaceView) findViewById(R.id.down_surfaceview);
        mUpSurfaceView.setZOrderOnTop(true); // 这句不能少
        // mUpSurfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
        mDownSurfaceView.setZOrderOnTop(true); // 这句不能少
        // mDownSurfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        canvas.drawPath(mLeftPath, mPaint);
        canvas.drawPath(mRightPath, mPaint);

        if (mCallImageViewPressed) {
            onDrawWhenCallPressed(canvas);

        }
        if (mMessageImageViewPressed) {
            onDrawWhenMessagePressed(canvas);

        }
        if (mCallImageViewUnderPressed) {

            onDrawWhenCallUnderPressed(canvas);
        }
        if (mMessageImageViewUnderPressed) {
            onDrawWhenMessageUnderPressed(canvas);
        }
        if (mUnlockImageViewPressed) {
            onDrawWhenUnlockPressed(canvas);
        }

    }

    /**
     * 当解锁图标被按下的时候
     * 
     * @param canvas
     */
    private void onDrawWhenUnlockPressed(Canvas canvas) {

        canvas.save();

        float y = Math.min(
                Math.max(mHitRectTimeView.bottom + mImageViewUnlock.getHeight() / 2 - 11.5f
                        * mDensity, mTouchY), mHitRectImageViewUnlock.centerY());
        float rato = 1
                - (y - (mHitRectImageViewUnlock.centerY()))
                / (mHitRectTimeView.bottom + mImageViewUnlock.getHeight() / 2 - 11.5f * mDensity - mHitRectImageViewUnlock
                        .centerY());
        mImageViewCallUnder.setAlpha((int) (127 * rato));
        mImageViewMessageUnder.setAlpha((int) (127 * rato));
        mPaint.setAlpha((int) (51 * rato));
        mImageViewArrow.setAlpha((int) (127 * rato));
        canvas.translate(getWidth() / 2 - mImageViewUnlock.getWidth() / 2,
                y - mImageViewUnlock.getHeight() / 2);
        mImageViewUnlock.draw(canvas);
        canvas.restore();
        invalidate();
    }

    /**
     * 当下面的短信图标被按下的时候
     * 
     * @param canvas
     */
    private void onDrawWhenMessageUnderPressed(Canvas canvas) {
        mImageViewMessageUnder.setVisibility(View.GONE);
        canvas.save();
        canvas.translate(mPointF.x - mHitRectMessageUnder.width(), mPointF.y);
        mImageViewMessageUnder2.draw(canvas);
        canvas.restore();
        invalidate();
    }

    /**
     * 当下面的电话图标被按下的时候
     * 
     * @param canvas
     */
    private void onDrawWhenCallUnderPressed(Canvas canvas) {
        mImageViewCallUnder.setVisibility(View.GONE);
        canvas.save();
        canvas.translate(mPointF.x, mPointF.y);
        mImageViewCallUnder2.draw(canvas);
        canvas.restore();
        invalidate();
    }

    /**
     * 当上面的短信图标被按下的时候
     * 
     * @param canvas
     */
    @SuppressWarnings("deprecation")
    private void onDrawWhenMessagePressed(Canvas canvas) {
        mImageViewMissedMessage.setVisibility(View.GONE);
        mImageViewMessage.setVisibility(View.GONE);
        canvas.save();
        // float degree = AlgorithmUtils.lineAndLineDegree(mMessageLine,
        // mMoveLine);
        mMatrix = canvas.getMatrix();
        mMatrix.preRotate(mDegree, mCenterXTimeView, mCenterYTimeView);
        mMatrix.preTranslate(mHitRectMessage.left, mHitRectMessage.top);
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
            canvas.setMatrix(mMatrix);
        } else {
            canvas.concat(mMatrix);
        }
        mImageViewMessage.draw(canvas);
        canvas.restore();

        canvas.save();
        mMatrix = canvas.getMatrix();
        mMatrix.preRotate(mDegree, mCenterXTimeView, mCenterYTimeView);
        mMatrix.preTranslate(mImageViewMissedMessage.getLeft(), mImageViewMissedMessage.getTop());
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
            canvas.setMatrix(mMatrix);
        } else {
            canvas.concat(mMatrix);
        }
        mImageViewMissedMessage.draw(canvas);
        canvas.restore();
    }

    /**
     * 当上面的电话图标被按下的时候
     * 
     * @param canvas
     */
    @SuppressWarnings("deprecation")
    private void onDrawWhenCallPressed(Canvas canvas) {
        mImageViewCall.setVisibility(View.GONE);
        mImageViewMissedCall.setVisibility(View.GONE);
        canvas.save();
        mMatrix = canvas.getMatrix();
        mMatrix.preRotate(mDegree, mCenterXTimeView, mCenterYTimeView);

        mMatrix.preTranslate(mHitRectCall.left, mHitRectCall.top);
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
            canvas.setMatrix(mMatrix);
        } else {
            canvas.concat(mMatrix);
        }
        mImageViewCall.draw(canvas);
        canvas.restore();
        canvas.save();
        mMatrix = canvas.getMatrix();
        mMatrix.preRotate(mDegree, mCenterXTimeView, mCenterYTimeView);
        mMatrix.preTranslate(mImageViewMissedCall.getLeft(), mImageViewMissedCall.getTop());
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
            canvas.setMatrix(mMatrix);
        } else {
            canvas.concat(mMatrix);
        }
        mImageViewMissedCall.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float eventX = event.getX();
        float eventY = event.getY();
        mTouchX = eventX;
        mTouchY = eventY;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MotionEvent.ACTION_DOWN");
                if (mMessageImageViewPressed || mCallImageViewPressed) {
                    mMovePoint.set(eventX, eventY);
                    mMoveLine.setPoints(mTimeViewPointPointFInQuadrant, mMovePointPointFInQuadrant);
                    invalidate(mHitRectTimeView);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                /*
                 * 处理短信移动事件
                 */
                if (mMessageImageViewPressed) {
                    try {
                        handleMessageMove(eventX, eventY);
                    } catch (Exception e) {
                        return true;
                    }
                }
                /*
                 * 处理电话移动事件
                 */
                if (mCallImageViewPressed) {
                    try {
                        handleCallMove(eventX, eventY);
                    } catch (Exception e) {
                        return true;
                    }
                }
                if (mCallImageViewUnderPressed) {
                    calculateUnderCallPointF();
                    invalidate();
                }

                if (mMessageImageViewUnderPressed) {
                    calculateUnderMessagePointF();
                    invalidate();
                }
                if (mUnlockImageViewPressed) {
                    calculateUnlock();
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
                mMessageImageViewUnderPressed = false;
                if (mUnlockImageViewPressed) {
                    mUnlockImageViewPressed = false;
                    mImageViewUnlock.setHighlight(false);
                    mImageViewCallUnder.setAlpha(255);
                    mPaint.setAlpha(51);
                    mImageViewCallUnder.invalidate();
                    mImageViewMessageUnder.setAlpha(255);
                    mImageViewMessageUnder.invalidate();
                }
                mCallImageViewUnderPressed = false;

                mImageViewMessageUnder2.setPressed(mMessageImageViewUnderPressed);
                mImageViewCallUnder2.setPressed(mCallImageViewUnderPressed);
                if (mMessageImageViewPressed) {
                    mMessageImageViewPressed = false;
                    mImageViewMessage.setPressed(mMessageImageViewPressed);
                    mImageViewMessage.setVisibility(View.VISIBLE);
                    mImageViewMissedMessage.setVisibility(View.VISIBLE);
                }
                if (mCallImageViewPressed) {
                    mCallImageViewPressed = false;
                    mImageViewCall.setPressed(mCallImageViewPressed);
                    mImageViewCall.setVisibility(View.VISIBLE);
                    mImageViewMissedCall.setVisibility(View.VISIBLE);
                }

                mImageViewMessageUnder.setVisibility(View.VISIBLE);
                mImageViewCallUnder.setVisibility(View.VISIBLE);
                mImageViewUnlock.setVisibility(View.VISIBLE);
                mPointF.set(0, 0);
                if (mRotating) {
                    mRotating = false;
                }
                if (mViarated) {
                    mViarated = false;
                }// TODO 这里是不是高亮需要
                 // invalidate();
                mDegree = 0;
                mTimeView.setRotateDegree(mDegree);
                mImageViewMissedCall.setRotateDegree(mDegree);
                mImageViewMissedMessage.setRotateDegree(mDegree);
                if (mUnlock) {
                    mUnlock = false;
                    mTimeView.showHilightDrawable(false);
                    unlock(mUnlockType);
                }

            default:
                Log.d(TAG, "MotionEvent.ACTION_UP " + action);
                return true;
        }
    }

    private void handleCallMove(float eventX, float eventY) throws Exception {
        // 只在左半球做判断
        if (!(eventX > mCenterXTimeView)) {
            eventX = Math.min(eventX, mCenterXTimeView);
            mMovePoint.set(eventX, eventY);
            mMoveLine.setPoints(mTimeViewPointPointFInQuadrant, mMovePointPointFInQuadrant);
            mDegree = AlgorithmUtils.lineAndLineDegree(mCallLine, mMoveLine);
            if (Double.isNaN(mDegree))
                throw new Exception();
            if (!(mDegree >= -360 && mDegree <= -270)) {
                mRotating = true;
                mTimeView.setRotateDegree(mDegree);
                mImageViewMissedCall.setRotateDegree(mDegree);
                mImageViewMissedMessage.setRotateDegree(mDegree);
                invalidate();
                if (mUnlock) {
                    Log.d(TAG, "the degree ---------> " + mDegree);
                    mUnlock = false;
                    mTimeView.showHilightDrawable(false);
                }
            } else {
                mRotating = false;
            }

        } else {
            if (mRotating) {
                mUnlockType = UNLOCK_CALL;
                mRotating = false;
                mVibrator.vibrate(100);
                mUnlock = true;
                mTimeView.showHilightDrawable(true);
            }
        }
    }

    private void handleMessageMove(float eventX, float eventY) throws Exception {
        // 只在右半球做判断
        if (!(eventX < mCenterXTimeView)) {
            eventX = Math.max(eventX, mCenterXTimeView);
            mMovePoint.set(eventX, eventY);
            mMoveLine.setPoints(mTimeViewPointPointFInQuadrant, mMovePointPointFInQuadrant);

            mDegree = AlgorithmUtils.lineAndLineDegree(mMessageLine, mMoveLine);
            if (Double.isNaN(mDegree))
                throw new Exception();

            if (!(mDegree >= -90 && mDegree <= 0)) {
                mRotating = true;
                invalidate();
                mTimeView.setRotateDegree(mDegree);
                mImageViewMissedCall.setRotateDegree(mDegree);
                mImageViewMissedMessage.setRotateDegree(mDegree);
                if (mUnlock) {
                    Log.d(TAG, "the degree ---------> " + mDegree);
                    mUnlock = false;
                    mTimeView.showHilightDrawable(false);
                }
            } else {
                mRotating = false;
            }

        } else {
            if (mRotating) {
                mUnlockType = UNLOCK_MESSAGE;
                mRotating = false;
                mVibrator.vibrate(100);
                mUnlock = true;
                mTimeView.showHilightDrawable(true);
            }
        }
    }

    /**
     * 解锁方法
     * 
     * @param type
     */
    private void unlock(int type) {
        switch (type) {
            case UNLOCK_CALL:
                mKeyguardCallback.unlockCall(0);
                break;
            case UNLOCK_MESSAGE:
                mKeyguardCallback.unlockMessage(0);
                break;
            default:
                mKeyguardCallback.unlockScreen();
                break;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float eventX = ev.getX();
        float eventY = ev.getY();
        mTouchY = eventY;
        mTouchX = eventX;
        boolean result = super.onInterceptTouchEvent(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN: // 处理ACTION_DOWN 事件
                /* 当上面的电话被按住了 */
                if (mHitRectCall.contains((int) eventX, (int) eventY) && mUnreadCall > 0) {
                    result = true;
                    mCallImageViewPressed = true;
                    mImageViewCall.setPressed(mCallImageViewPressed);
                }
                /* 当上面的短信被按住了 */
                if (mHitRectMessage.contains((int) eventX, (int) eventY) && mUnreadMessage > 0) {
                    result = true;
                    mMessageImageViewPressed = true;
                    mImageViewMessage.setPressed(mMessageImageViewPressed);
                }
                /* 当下面的电话被按住了 */
                if (mHitRectCallUnder.contains((int) eventX, (int) eventY)) {
                    result = true;

                    mCallImageViewUnderPressed = true;
                    mImageViewCallUnder2.setPressed(mCallImageViewUnderPressed);
                }
                /* 当下面的短信被按住了 */
                if (mHitRectMessageUnder.contains((int) eventX, (int) eventY)) {
                    result = true;
                    mMessageImageViewUnderPressed = true;
                    mImageViewMessageUnder2.setPressed(mMessageImageViewUnderPressed);
                }

                /* 当解锁圆圈被按住了 */
                if (mHitRectImageViewUnlock.contains((int) eventX, (int) eventY)) {
                    result = true;
                    mUnlockImageViewPressed = true;
                    calculateUnlock();
                    mImageViewUnlock.setHighlight(true);

                    mImageViewUnlock.setVisibility(View.GONE);

                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

            default:
                break;
        }
        return result;
    }

    @Override
    public void cleanUp() {

    }

    @Override
    public void onCityChanged(String arg0, String arg1) {

    }

    @Override
    public void onCityInit(String arg0, String arg1, String arg2, int arg3, String arg4) {

    }

    @Override
    public void onPause() {
        mImageViewUnlock.onPause();
        mTimeView.onPause();
        mUpSurfaceView.onPause();
        mDownSurfaceView.onPause();
    }

    @Override
    public void onRefreshBatteryInfo(boolean pluggedIn, int batteryLevel) {
        mImageViewUnlock.setPluggin(pluggedIn, batteryLevel);
    }

    @Override
    public void onResume() {
        mImageViewUnlock.onResume();
        mTimeView.onResume();
        mUpSurfaceView.onResume();
        mDownSurfaceView.onResume();
    }

    @Override
    public void onTimeChanged() {

    }

    @Override
    public void onUnreadSMSChanged(int count, long lastTime, String lastMsg) {
        checkMessageImaegView(count);
    }

    @Override
    public void onMissedCallChanged(int count, long lastTime, String lastMsg) {
        checkCallImageView(count);
    }

    private void checkCallImageView(int count) {
        this.mUnreadCall = count;
        if (this.mUnreadCall > 0) {
            if (!mCallImageViewPressed) {
                mImageViewCall.setVisibility(View.VISIBLE);
                mImageViewMissedCall.setVisibility(View.VISIBLE);
            }
            mImageViewMissedCall.setMissedCount(count);
        } else {
            mImageViewCall.setVisibility(View.GONE);
            mImageViewMissedCall.setVisibility(View.GONE);
        }
    }

    private void checkMessageImaegView(int count) {
        this.mUnreadMessage = count;
        if (this.mUnreadMessage > 0) {
            if (!mMessageImageViewPressed) {// 如果没有被按住
                mImageViewMessage.setVisibility(View.VISIBLE);
                mImageViewMissedMessage.setVisibility(View.VISIBLE);
            }
            mImageViewMissedMessage.setMissedCount(count);
        } else {
            mImageViewMessage.setVisibility(View.GONE);
            mImageViewMissedMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onUpdateTemperature(int arg0, String arg1, String arg2) {

    }

    private boolean mViarated;// 是否已经震动

    /**
     * 触发到了打开电话的边界
     */
    private void triggerCallOpen() {
        if (!mViarated) {
            mVibrator.vibrate(100);
            mViarated = true;
            mUnlock = true;
            mUnlockType = UNLOCK_CALL;
            mImageViewUnlock.setHighlight(true);
        }
    }

    /**
     * 触发到了打开短信的边界
     */
    private void triggerMessageOpen() {
        if (!mViarated) {
            mVibrator.vibrate(100);
            mViarated = true;
            mUnlock = true;
            mUnlockType = UNLOCK_MESSAGE;
            mImageViewUnlock.setHighlight(true);
        }
    }

    /**
     * 触发到了解锁的边界
     */
    private void triggerUnlock() {
        if (!mViarated) {
            mVibrator.vibrate(100);
            mViarated = true;
            mUnlock = true;
            mUnlockType = UNLOCK_HOME;
            mTimeView.showHilightDrawable(true);
        }
    }

    PointF mPointF = new PointF();

    private void calculateUnderCallPointF() {
        mTouchY -= mImageViewCallUnder.getHeight();
        mTouchY = Math.min(Math.max(mUnderCallStartY, mTouchY), mUnderCallEndY);
        if (mTouchY >= mUnderCallEndY - 80 * mWidthRato) {
            triggerCallOpen();
        } else {
            if (mViarated) {
                mViarated = false;
                mUnlock = false;
                mUnlockType = -1;
                mImageViewUnlock.setHighlight(false);
            }
        }
        mPointF.set((mTouchY - mLeftTrace.getB()) / mLeftTrace.getK(), mTouchY);
    }

    private void calculateUnderMessagePointF() {
        mTouchY -= mImageViewMessageUnder.getHeight();
        mTouchY = Math.min(Math.max(mUnderMessageStartY, mTouchY), mUnderCallEndY);
        if (mTouchY >= mUnderCallEndY - 80 * mWidthRato) {
            triggerMessageOpen();
        } else {
            if (mViarated) {
                mViarated = false;
                mUnlock = false;
                mUnlockType = -1;
                mImageViewUnlock.setHighlight(false);
            }
        }
        mPointF.set((mTouchY - mRightTrace.getB()) / mRightTrace.getK(), mTouchY);

    }

    private void calculateUnlock() {
        mTouchY = Math.min(
                Math.max(mHitRectTimeView.bottom + mImageViewUnlock.getHeight() / 2 - 11.5f
                        * mDensity, mTouchY), mHitRectImageViewUnlock.centerY());
        mTouchX = getWidth() / 2;
        if (mTouchY == mHitRectTimeView.bottom + mImageViewUnlock.getHeight() / 2 - 11.5f
                * mDensity) {
            triggerUnlock();
        } else {

            if (mViarated) {
                mViarated = false;
                mUnlock = false;
                mUnlockType = -1;
                mTimeView.showHilightDrawable(false);
            }

        }
    }

	@Override
	public Bitmap getBlurBitmap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setKeyguardCallback(
			com.cyou.cma.clocker.apf.KeyguardCallback arg0) {
		// TODO Auto-generated method stub
		
	}
}
