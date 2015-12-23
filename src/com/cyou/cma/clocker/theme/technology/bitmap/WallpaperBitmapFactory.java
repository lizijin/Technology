
package com.cyou.cma.clocker.theme.technology.bitmap;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.IOException;
import java.io.InputStream;

public class WallpaperBitmapFactory {
    // private WallpaperBitmap mWallpaperBitmap;

    public WallpaperBitmapFactory() {
    }

    /**
     * 根据resource id 获取Bitmap
     * 
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap getBitmap(Context context, int resId) {
        // TODO 三个getBitmap方法都需要做图片大小判断
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        try {
            return BitmapFactory.decodeStream(is, null, opt);
        } catch (Exception e) {

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取系统壁纸
     * 
     * @param context
     * @return
     */
    public static Bitmap getBitmap(Context context) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        Bitmap systemWallpaper = ((BitmapDrawable) wallpaperManager.getDrawable()).getBitmap();
        return systemWallpaper;
    }

    /**
     * 根据路径获取Bitmap
     * 
     * @param path
     * @return
     */
    public static Bitmap getBitmap(String path) {
        return BitmapFactory.decodeFile(path);
    }
}
