
package com.cyou.cma.clocker.theme.technology.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;

/**
 * 获取壁纸的接口
 * 
 * @author jiangbin
 */
public interface WallpaperBitmap {
    /**
     * 获取壁纸
     * @param source 原图
     * @return
     */
    public Bitmap getWallpaper(Bitmap source);

    public Options createOptions();
}
