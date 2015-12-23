
package com.cyou.cma.clocker.theme.technology.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.util.Random;

public class ScheduleDrawable extends ProxyDrawable {
    private Handler handler = new Handler();
    private boolean started;// 是否已经开始了动画
    private boolean stopped;// 动画是否已经结束了
    private boolean ready;// 准备开始动画
    private long mDelayMillis = 100;
    private int index = 0;
    private final static int M = 5;
    private Random mRandom = new Random();

    public ScheduleDrawable(Drawable target) {
        super(target);
    }

    public void startScheduleAniamtion() {
        if (!started) {
//            started = true;
            handler.postDelayed(mScheduleTask, mRandom.nextInt(3000));
        }
    }

    private Runnable mScheduleTask = new Runnable() {

        @Override
        public void run() {
            index = (index + 1) % M;
            invalidateSelf();
            if (index != M - 1) {
                started=true;
                handler.removeCallbacks(mScheduleTask);
                handler.postDelayed(mScheduleTask, mDelayMillis);
            } else {
                stopped = true;
                started = false;
                handler.removeCallbacks(mScheduleTask);
                handler.postDelayed(mScheduleTask, mRandom.nextInt(5000));
            }
        }
    };

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));

        if (started) {
            if (!(index % 2 == 0)) {
                super.draw(canvas);
            }
        } else {
            super.draw(canvas);
        }
    }

}
