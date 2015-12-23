
package com.cyou.cma.clocker.theme.technology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.cyou.cma.clocker.theme.sdk.measuer.DefaultMeasureBase;

import java.lang.ref.WeakReference;

public class UpSurfaceView extends SurfaceView implements Callback {

    private Paint mPaint;
    private Paint mPaint2;
    private Paint mPaintSmall;

    private float mDensity;
    private Typeface mTypeface;
    private DefaultMeasureBase mMeasureBase;
    private float mWidthRato;
    private boolean mDraw;
    private boolean mForeground;
    private long count;
    private long count2;
    private SurfaceHolder mSurfaceHolder;
    private Drawable mBackgroundDrawable;
    /**
     * 控件大小
     */
    private int measureWidth = 297;
    private int measureHeight = 158;

    private String clocker_1 = "C-LOCKER TECHNOLOGY";
    private String clocker_2 = "CLOCKER PROVIDES CUSTOMIZED";
    private String clocker_3 = "CLOCKER";
    private String clocker_4 = "SLIDE";
    private String clocker_5 = "TOURROS";
    private String clocker_6 = "DIFFERENT";
    private String clocker_7 = "DIRECTIONS";
    private String clocker_8 = "TO ENTER";
    private String clocker_9 = "DIFFERENT";
    private String clocker_10 = "USERS";
    private String clocker_11 = "CAN";
    private String clocker_12 = "CHANGE";
    private String clocker_13 = "SCREEN";
    private String clocker_14 = "LOCKER";
    private String clocker_15 = "1234";

    private String clocker_16 = "CLASSIC UNLOCK";
    private String clocker_17 = "USERS";
    private String clocker_18 = "CAN";
    private String clocker_19 = "CHANGE";
    private String clocker_20 = "SCREEN";
    private String clocker_21 = "LOCKER";

    private float x1 = 18, y1 = 14;
    private float x2 = 17, y2 = 30;
    private float x3 = 22, y3 = 45;
    private float x4 = 22, y4 = 59;
    private float x5 = 22, y5 = 67;
    private float x6 = 22, y6 = 75;
    private float x7 = 22, y7 = 83;
    private float x8 = 22, y8 = 91;
    private float x9 = 22, y9 = 99;

    private float x10 = 78, y10 = 59;
    private float x11 = 78, y11 = 67;
    private float x12 = 78, y12 = 75;
    private float x13 = 78, y13 = 83;
    private float x14 = 78, y14 = 91;

    private float x16 = 206, y16 = 90;
    private float x17 = 206, y17 = 100;
    private float x18 = 206, y18 = 108;
    private float x19 = 206, y19 = 116;
    private float x20 = 206, y20 = 124;
    private float x21 = 206, y21 = 132;

    private float x22 = 36, y22 = 121;
    private float x23 = 36, y23 = 129;
    private float x24 = 36, y24 = 137;
    private float x25 = 36, y25 = 145;
    private float x26 = 36, y26 = 153;

    private float x28 = 57, y28 = 121;
    private float x29 = 57, y29 = 129;
    private float x30 = 57, y30 = 137;
    private float x31 = 57, y31 = 145;
    private float x32 = 57, y32 = 153;

    private float x34 = 86, y34 = 121;
    private float x35 = 86, y35 = 129;
    private float x36 = 86, y36 = 137;
    private float x37 = 86, y37 = 145;
    private float x38 = 86, y38 = 153;

    private float x40 = 113, y40 = 121;
    private float x41 = 113, y41 = 129;
    private float x42 = 113, y42 = 137;
    private float x43 = 113, y43 = 145;
    private float x44 = 113, y44 = 153;

    private float x46 = 137, y46 = 121;
    private float x47 = 137, y47 = 129;
    private float x48 = 137, y48 = 137;
    private float x49 = 137, y49 = 145;
    private float x50 = 137, y50 = 153;

    private String clocker_34 = "110";
    private String clocker_35 = "111";
    private String clocker_36 = "112";
    private String clocker_37 = "113";
    private String clocker_38 = "114";

    private String clocker_22_27 = "1234";
    private String clocker_28_33 = "000";

    // private String c

    public UpSurfaceView(Context context) {
        super(context);
    }

    public UpSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDensity = context.getResources().getDisplayMetrics().density;
        mMeasureBase = DefaultMeasureBase.getDefaultMeasureBase();
        mWidthRato = mMeasureBase.getWidthRato(context);
        // mWidthRato = 1;
        measureWidth = (int) (mWidthRato * measureWidth);
        measureHeight = (int) (measureHeight * mWidthRato);
        mBackgroundDrawable = getResources().getDrawable(R.drawable.top_background);
        mBackgroundDrawable.setBounds(0, 0, (measureWidth), (measureHeight));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(225, 17, 73, 153));
        mPaint.setTextSize(9.f * mDensity);
        mTypeface = Typeface.createFromAsset(getResources().getAssets(), "font/04B_03B.ttf");
        mPaint.setTypeface(mTypeface);
        mPaintSmall = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSmall.setColor(Color.argb(225, 17, 73, 153));
        mPaintSmall.setTextSize(4.f * mDensity);
        mPaintSmall.setTypeface(mTypeface);

        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(Color.RED);
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
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
        x16 = x16 * mWidthRato;
        x17 = x17 * mWidthRato;
        x18 = x18 * mWidthRato;
        x19 = x19 * mWidthRato;
        x20 = x20 * mWidthRato;
        x21 = x21 * mWidthRato;
        x22 = x22 * mWidthRato;
        x23 = x23 * mWidthRato;
        x24 = x24 * mWidthRato;
        x25 = x25 * mWidthRato;
        x26 = x26 * mWidthRato;
        x28 = x28 * mWidthRato;
        x29 = x29 * mWidthRato;
        x30 = x30 * mWidthRato;
        x31 = x31 * mWidthRato;
        x32 = x32 * mWidthRato;
        x34 = x34 * mWidthRato;
        x35 = x35 * mWidthRato;
        x36 = x36 * mWidthRato;
        x37 = x37 * mWidthRato;
        x38 = x38 * mWidthRato;
        x40 = x40 * mWidthRato;
        x41 = x41 * mWidthRato;
        x42 = x42 * mWidthRato;
        x43 = x43 * mWidthRato;
        x44 = x44 * mWidthRato;

        x46 = x46 * mWidthRato;
        x47 = x47 * mWidthRato;
        x48 = x48 * mWidthRato;
        x49 = x49 * mWidthRato;
        x50 = x50 * mWidthRato;

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
        y16 = y16 * mWidthRato;
        y17 = y17 * mWidthRato;
        y18 = y18 * mWidthRato;
        y19 = y19 * mWidthRato;
        y20 = y20 * mWidthRato;
        y21 = y21 * mWidthRato;
        y22 = y22 * mWidthRato;
        y23 = y23 * mWidthRato;
        y24 = y24 * mWidthRato;
        y25 = y25 * mWidthRato;
        y26 = y26 * mWidthRato;
        y28 = y28 * mWidthRato;
        y29 = y29 * mWidthRato;
        y30 = y30 * mWidthRato;
        y31 = y31 * mWidthRato;
        y32 = y32 * mWidthRato;
        y34 = y34 * mWidthRato;
        y35 = y35 * mWidthRato;
        y36 = y36 * mWidthRato;
        y37 = y37 * mWidthRato;
        y38 = y38 * mWidthRato;
        y40 = y40 * mWidthRato;
        y41 = y41 * mWidthRato;
        y42 = y42 * mWidthRato;
        y43 = y43 * mWidthRato;
        y44 = y44 * mWidthRato;
        y46 = y46 * mWidthRato;
        y47 = y47 * mWidthRato;
        y48 = y48 * mWidthRato;
        y49 = y49 * mWidthRato;
        y50 = y50 * mWidthRato;

    }

    public UpSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void onResume() {
        mForeground = true;
    }

    public void onPause() {
        mForeground = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth, measureHeight);
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
        drawText(canvas, clocker_16, x16, y16, mPaintSmall);
        drawText(canvas, clocker_17, x17, y17, mPaintSmall);
        drawText(canvas, clocker_18, x18, y18, mPaintSmall);
        drawText(canvas, clocker_19, x19, y19, mPaintSmall);
        drawText(canvas, clocker_20, x20, y20, mPaintSmall);
        drawText(canvas, clocker_21, x21, y21, mPaintSmall);
        drawText(canvas, clocker_22_27, x22, y22, mPaintSmall);
        drawText(canvas, clocker_22_27, x23, y23, mPaintSmall);
        drawText(canvas, clocker_22_27, x24, y24, mPaintSmall);
        drawText(canvas, clocker_22_27, x25, y25, mPaintSmall);
        drawText(canvas, clocker_22_27, x26, y26, mPaintSmall);
        drawText(canvas, clocker_28_33, x28, y28, mPaintSmall);
        drawText(canvas, clocker_28_33, x29, y29, mPaintSmall);
        drawText(canvas, clocker_28_33, x30, y30, mPaintSmall);
        drawText(canvas, clocker_28_33, x31, y31, mPaintSmall);
        drawText(canvas, clocker_28_33, x32, y32, mPaintSmall);
        drawText(canvas, clocker_34, x34, y34, mPaintSmall);
        drawText(canvas, clocker_35, x35, y35, mPaintSmall);
        drawText(canvas, clocker_36, x36, y36, mPaintSmall);
        drawText(canvas, clocker_37, x37, y37, mPaintSmall);
        drawText(canvas, clocker_38, x38, y38, mPaintSmall);
        drawText(canvas, clocker_34, x40, y40, mPaintSmall);
        drawText(canvas, clocker_35, x41, y41, mPaintSmall);
        drawText(canvas, clocker_36, x42, y42, mPaintSmall);
        drawText(canvas, clocker_37, x43, y43, mPaintSmall);
        drawText(canvas, clocker_38, x44, y44, mPaintSmall);
        drawText(canvas, clocker_34, x46, y46, mPaintSmall);
        drawText(canvas, clocker_35, x47, y47, mPaintSmall);
        drawText(canvas, clocker_36, x48, y48, mPaintSmall);
        drawText(canvas, clocker_37, x49, y49, mPaintSmall);
        drawText(canvas, clocker_38, x50, y50, mPaintSmall);

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
        new DrawThread(holder, this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("", "surfaceDestroyed");
        mDraw = false;
        mSurfaceHolder.removeCallback(this);
    }

    static class DrawThread extends Thread {
        private WeakReference<SurfaceHolder> mSurfaceHolder;

        private WeakReference<UpSurfaceView> mDownSurfaceView;

        // boolean

        public DrawThread(SurfaceHolder surfaceHolder, UpSurfaceView downSurfaceView) {
            mSurfaceHolder = new WeakReference<SurfaceHolder>(surfaceHolder);
            mDownSurfaceView = new WeakReference<UpSurfaceView>(downSurfaceView);

        }

        long count = 0;

        @Override
        public void run() {
            try {

                if (mDownSurfaceView.get() != null && mSurfaceHolder.get() != null)
                    while (mDownSurfaceView.get().mDraw) {
                        if (mDownSurfaceView.get().mForeground) {

                            Canvas canvas = mSurfaceHolder.get().lockCanvas(null);// 获取画布
                            // canvas.save
                            canvas.drawColor(Color.BLACK);
                            // canvas.drawre
                            mDownSurfaceView.get().drawText(canvas);
                            mSurfaceHolder.get().unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}
