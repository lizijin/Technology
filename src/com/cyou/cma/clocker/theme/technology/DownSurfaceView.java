
package com.cyou.cma.clocker.theme.technology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.cyou.cma.clocker.theme.sdk.measuer.DefaultMeasureBase;

import java.lang.ref.WeakReference;

public class DownSurfaceView extends SurfaceView implements Callback {

    private Paint mPaint;
    private Paint mPaintSmall;
    private Paint mPaintSmallRed;

    private Paint mPaintLine;
    private float mDensity;
    private Typeface mTypeface;
    private DefaultMeasureBase mMeasureBase;
    private float mWidthRato;

    private boolean mDraw = false;
    private boolean mForeground;
    /**
     * 控件大小
     */
    private int measureWidth = 224;
    private int measureHeight = 184;

    private String clocker_1 = "C-LOCKER TECHNOLOGY";
    private String clocker_2 = "CLOCKER PROVIDES CUSTOMIZED";
    private String clocker_3 = "CLOCKER";
    private String clocker_4 = "SLIDE";
    private String clocker_5 = "TOWARDS";
    private String clocker_6 = "DIFFERENT";
    private String clocker_7 = "DIRECTIONS";
    private String clocker_8 = "TO";
    private String clocker_9 = "ENTER";
    private String clocker_10 = "DIFFERENT";
    private String clocker_11 = "INTERFACES";
    private String clocker_12 = "SCROLL UP UNLOCK";
    private String clocker_13 = "BUBBLE";
    private String clocker_14 = "UNLOCK";
    private String clocker_15 = "USERS";
    private String clocker_16 = "CAN";
    private String clocker_17 = "CHANGE";
    private String clocker_18 = "SCREEN";
    private String clocker_19 = "LOCK";
    private String clocker_20 = "0102583675";
    private String clocker_21 = "0102583675";

    private float x1 = 18, y1 = 13;
    private float x2 = 16, y2 = 30;
    private float x3 = 22, y3 = 46;
    private float x4 = 22, y4 = 59;
    private float x5 = 22, y5 = 67;
    private float x6 = 22, y6 = 75;
    private float x7 = 22, y7 = 83;
    private float x8 = 22, y8 = 91;
    private float x9 = 22, y9 = 99;
    private float x10 = 22, y10 = 106;
    private float x11 = 22, y11 = 114;
    private float x12 = 22, y12 = 129;
    private float x13 = 22, y13 = 142;
    private float x14 = 22, y14 = 150;
    private float x15 = 87, y15 = 59;
    private float x16 = 87, y16 = 67;
    private float x17 = 87, y17 = 75;
    private float x18 = 87, y18 = 83;
    private float x19 = 87, y19 = 91;
    private float x20 = 90, y20 = 142;
    private float x21 = 90, y21 = 150;

    private SurfaceHolder mSurfaceHolder;
    private long count;
    private long count2;

    private Drawable mBackgroundDrawable;

    public DownSurfaceView(Context context) {
        super(context);
    }

    public DownSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = this.getHolder();
        Log.d("DownSurfaceView", "mSurfaceHolder : " + mSurfaceHolder);

        mBackgroundDrawable = context.getResources().getDrawable(R.drawable.down_background);

        mSurfaceHolder.addCallback(this);
        mDensity = context.getResources().getDisplayMetrics().density;
        mMeasureBase = DefaultMeasureBase.getDefaultMeasureBase();
        mWidthRato = mMeasureBase.getWidthRato(context);
        mBackgroundDrawable.setBounds(0, 0, (int) (measureWidth * mWidthRato),
                (int) (measureHeight * mWidthRato));
        measureWidth = (int) (mWidthRato * measureWidth);
        measureHeight = (int) (measureHeight * mWidthRato);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(225, 17, 73, 153));
        mPaint.setTextSize(9.f * mDensity);
        mTypeface = Typeface.createFromAsset(getResources().getAssets(), "font/04B_03B.ttf");
        mPaint.setTypeface(mTypeface);

        mPaintSmall = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSmall.setColor(Color.argb(225, 17, 73, 153));
        mPaintSmall.setTextSize(4.f * mDensity);
        mPaintSmall.setTypeface(mTypeface);

        mPaintSmallRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSmallRed.setColor(Color.argb(225, 99, 14, 28));
        mPaintSmallRed.setTextSize(4.f * mDensity);
        mPaintSmallRed.setTypeface(mTypeface);
        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(Color.BLUE);
        mPaintLine.setStrokeWidth(2.0f);
        // setZOrderOnTop(true); // 这句不能少
        // setZOrderMediaOverlay(false);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        x1 = x1 * mWidthRato;
        x2 = x2 * mWidthRato;
        x3 = x3 * mWidthRato;
        x4 = x4 * mWidthRato;
        x5 = x5 * mWidthRato;
        x6 = x6 * mWidthRato;
        x7 = x7 * mWidthRato;
        x8 = x8 * mWidthRato;
        x9 = x9 * mWidthRato;
        x10 = x10 * mWidthRato;
        x11 = x11 * mWidthRato;
        x12 = x12 * mWidthRato;
        x13 = x13 * mWidthRato;
        x14 = x14 * mWidthRato;
        x15 = x15 * mWidthRato;
        x16 = x16 * mWidthRato;
        x17 = x17 * mWidthRato;
        x18 = x18 * mWidthRato;
        x19 = x19 * mWidthRato;
        x20 = x20 * mWidthRato;
        x21 = x21 * mWidthRato;

        y1 = y1 * mWidthRato;
        y2 = y2 * mWidthRato;
        y3 = y3 * mWidthRato;
        y4 = y4 * mWidthRato;
        y5 = y5 * mWidthRato;
        y6 = y6 * mWidthRato;
        y7 = y7 * mWidthRato;
        y8 = y8 * mWidthRato;
        y9 = y9 * mWidthRato;
        y10 = y10 * mWidthRato;
        y11 = y11 * mWidthRato;
        y12 = y12 * mWidthRato;
        y13 = y13 * mWidthRato;
        y14 = y14 * mWidthRato;
        y15 = y15 * mWidthRato;
        y16 = y16 * mWidthRato;
        y17 = y17 * mWidthRato;
        y18 = y18 * mWidthRato;
        y19 = y19 * mWidthRato;
        y20 = y20 * mWidthRato;
        y21 = y21 * mWidthRato;

    }

    public DownSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        // mBackgroundDrawable.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
    }

    public void drawText(Canvas canvas) {
        count++;
        count2++;
        mBackgroundDrawable.draw(canvas);
        drawText(canvas, clocker_1, x1, y1, mPaint);
        drawText(canvas, clocker_2, x2, y2, mPaintSmall);
        drawText(canvas, clocker_3, x3, y3, mPaintSmall);
        drawText(canvas, clocker_4, x4, y4, mPaintSmall);
        drawText(canvas, clocker_5, x5, y5, mPaintSmall);
        drawText(canvas, clocker_6, x6, y6, mPaintSmall);
        drawText(canvas, clocker_7, x7, y7, mPaintSmall);
        drawText(canvas, clocker_8, x8, y8, mPaintSmall);
        drawText(canvas, clocker_9, x9, y9, mPaintSmall);
        drawText(canvas, clocker_10, x10, y10, mPaintSmall);
        drawText(canvas, clocker_11, x11, y11, mPaintSmall);
        drawText(canvas, clocker_12, x12, y12, mPaintSmall);
        drawText(canvas, clocker_13, x13, y13, mPaintSmall);
        drawText(canvas, clocker_14, x14, y14, mPaintSmall);
        drawText(canvas, clocker_15, x15, y15, mPaintSmall);
        drawText(canvas, clocker_16, x16, y16, mPaintSmall);
        drawText(canvas, clocker_17, x17, y17, mPaintSmall);
        drawText(canvas, clocker_18, x18, y18, mPaintSmall);
        drawText(canvas, clocker_19, x19, y19, mPaintSmall);
        drawText(canvas, clocker_20, x20, y20, mPaintSmall);
        drawText(canvas, clocker_21, x21, y21, mPaintSmallRed);

    }

    private void drawText(Canvas canvas, String text, float x, float y, Paint paint) {
        String newText = null;
        if (text.equals(clocker_1)) {
            if (count2 < text.length()) {
                newText = text.substring(0, (int) (count2 % text.length() + 1));
            } else {
                newText = text;
            }
        } else {
            newText = text.substring(0, (int) (count % text.length() + 1));
        }
        canvas.save();
        canvas.translate(x, y);
        canvas.drawText(newText, 0, 0, paint);
        canvas.restore();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // mSurfaceHolder = holder;
        mDraw = true;
        new DrawThread(mSurfaceHolder, this).start();

        Log.d("DownSurfaceView", "mSurfaceHolder surfaceCreated: " + mSurfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void onResume() {
        mForeground = true;
    }

    public void onPause() {
        mForeground = false;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("", "surfaceDestroyed");
        mDraw = false;
        mSurfaceHolder.removeCallback(this);
    }

    static class DrawThread extends Thread {
        private WeakReference<SurfaceHolder> mSurfaceHolder;

        private WeakReference<DownSurfaceView> mDownSurfaceView;

        // boolean

        public DrawThread(SurfaceHolder surfaceHolder, DownSurfaceView downSurfaceView) {
            mSurfaceHolder = new WeakReference<SurfaceHolder>(surfaceHolder);
            mDownSurfaceView = new WeakReference<DownSurfaceView>(downSurfaceView);
        }

        long count = 0;

        @Override
        public void run() {
            try {
                if (mDownSurfaceView.get() != null && mSurfaceHolder.get() != null)
                    while (mDownSurfaceView.get().mDraw) {
                        if (mDownSurfaceView.get().mForeground) {
                            Canvas canvas = mSurfaceHolder.get().lockCanvas(null);// 获取画布
                            Log.d("canvas", "canvas run " + canvas);
                            canvas.drawColor(Color.BLACK);
                            mDownSurfaceView.get().drawText(canvas);
                            mSurfaceHolder.get().unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
            } catch (Exception e) {

            }
        }
    }
}
