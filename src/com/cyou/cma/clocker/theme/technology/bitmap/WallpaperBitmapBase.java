
package com.cyou.cma.clocker.theme.technology.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.WindowManager;

import com.cyou.cma.clocker.theme.sdk.measuer.DefaultMeasureBase;

import java.lang.reflect.Field;

public class WallpaperBitmapBase implements WallpaperBitmap {
    private String TAG = "WallpaperBitmapBase";
    private DefaultMeasureBase mDefaultMeasureBase;

    public WallpaperBitmapBase() {
        mDefaultMeasureBase = DefaultMeasureBase.getDefaultMeasureBase();
    }

    /**
     * 获取状态栏的高度
     * 
     * @param context
     * @return 状态栏的高度，如果无法获取 返回50像素
     */
    public int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int resId = 0;
        int statusBarHeight = 50;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            resId = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(resId);
            Log.d(TAG, "get status bar height successfully resid " + resId);
            return statusBarHeight;
        } catch (Exception e) {
            Log.d(TAG, "get status bar height failed ");
        }
        return statusBarHeight;
    }

    /**
     * 获取设备的宽度
     * 
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    // TODO 使用非deprecation的方法读取
    public int getWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取设备的高度
     * 
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public int getHeight(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;

    }

    @Override
    public Options createOptions() {
        Options options = new Options();
        options.inJustDecodeBounds = true;

        // TODO 加上 options.inBitmap等
        return options;
    }

    public Bitmap getBitmap(Context context, Bitmap source) {
        int viewWidth = getWidth(context);
        int viewHeight = getHeight(context) - getStatusBarHeight(context);
        Bitmap bitmap = null;
        try {
            int x, y, width, height;
            x = 135;
            width = 720;
            y = 0;
            height = (int) (viewHeight * 720 / (viewWidth * 1.0f));

            // if (source.getWidth() / (float) source.getHeight() > viewWidth
            // / (float) viewHeight) {// 按照高度裁剪
            // y = 0;
            // height = (int) (source.getHeight() - (source.getHeight() /
            // (viewHeight * 1.0f))
            // * getStatusBarHeight(context));
            // width = height * viewWidth / viewHeight;
            // x = (source.getWidth() - width) / 2;
            // } else {// 按照宽度裁剪
            // x = 0;
            // width = source.getWidth();
            // height = width * viewHeight / viewWidth;
            // y = source.getHeight() - height;
            // }
            //
            // if (x < 0)
            // x = 0;
            // if (y < 0)
            // y = 0;
            // if ((x + width) > source.getWidth() || (y + height) >
            // source.getHeight()) {
            // return source;
            // }
            try {
//                int sourceDensity = source.getDensity();
//                float smaller = mDefaultMeasureBase.getSmallerOne(context);
//                int tartgetDensity = (int) (context.getResources().getDisplayMetrics().densityDpi*smaller);
//                Log.d("jiangbinhello", "sourceDensity:" + sourceDensity + " targetDensity:"
//                        + tartgetDensity);
//                source.setDensity(tartgetDensity);
                bitmap = Bitmap.createBitmap(source, x, y, width, height);
                // bitmap.compress(format, quality, stream)
            } catch (Exception e) {
                e.printStackTrace();
                return source;
            }
            source = null;
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    @Override
    public Bitmap getWallpaper(Bitmap source) {
        // TODO Auto-generated method stub
        return null;
    }
}
