
package com.cyou.cma.clocker.theme.sdk.utils;

import android.content.Context;
import android.provider.Settings;

class SettingsHelper {

    /**
     * 使用用户自己裁剪的照片做为锁屏壁纸
     */
    public static final int C_WALLPAPER_GALLERY = 2;

    /**
     * 使用桌面壁纸做为锁屏壁纸
     */
    public static final int C_WALLPAPER_SYSTEM = 1;

    /** 使用锁屏主题自带壁纸做为锁屏壁纸 */
    public static final int C_WALLPAPER_THEME = 0;

    /**
     * 当前锁屏主题所用壁纸的类型
     */
    public static final String C_SAVE_KEY_IN_USE_WALLPAPER_TYPE = "in_use_wallpaper_type";

    /**
     * 锁屏主题壁纸所在路径
     */
    public static final String C_KEY_WALLPAPER_IN_USE_PATH = "";

    public static final String SAVE_KEY_TOOLS_VIBRATE = "save_key_enable_unlock_vibrate";// 是否开启解锁振动
    public static final String SAVE_KEY_TOOLS_SOUND = "save_key_enable_unlock_sound";// 是否开启解锁铃声

    /**
     * 获取setting中的锁屏主题壁纸路径
     * 
     * @param context
     * @return
     */
    public static String getWallpaperPath(Context context) {
        return Settings.System.getString(context.getContentResolver(), C_KEY_WALLPAPER_IN_USE_PATH);
    }

    public static int getWallpaperType(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                C_SAVE_KEY_IN_USE_WALLPAPER_TYPE, C_WALLPAPER_THEME);
    }

    /**
     * 获取锁屏主题中解锁震动是否开启
     * 
     * @return
     */
    public static boolean getVibrateEnable(Context context) {
        return Settings.System.getInt(context.getContentResolver(), SAVE_KEY_TOOLS_VIBRATE, 1) == 1;
    }

    /**
     * 获取锁屏主题中解锁声音是否开启
     * 
     * @return
     */
    public static boolean getSoundEnable(Context context) {
        return Settings.System.getInt(context.getContentResolver(), SAVE_KEY_TOOLS_SOUND, 1) == 1;
    }

}
