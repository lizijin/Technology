
package com.cyou.cma.clocker.theme.sdk.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;

/**
 * 查询数据库的帮助类
 * 
 * @author jiangbin
 */
class ProviderHelper {
    public static final Uri wallpaper_uri = Uri.parse("content://wallpaper/wallpapers");
    public static final Uri theme_uri = Uri.parse("content://wallpaper/themes");
    public static final Uri vibrate_uri = Uri.parse("content://wallpaper/vibrate");
    public static final Uri sound_uri = Uri.parse("content://wallpaper/sound");
    public final static String KEY_ID = "id";
    public final static String KEY_THUMBNAIL = "thumbnail_path";
    public final static String KEY_WALLPAPER = "wallpaper_path";
    public final static String KEY_DEFAULT = "isdefault";
    public final static String KEY_TIME = "time";
    public final static String KEY_PROVIDE = "isprovide";

    public final static String KEY_PACKAGE = "packageName";
    public final static String KEY_WALLPAPER_TYPE = "wallpaperType";
    public final static String KEY_STATE = "state";
    public static final int KEY_WALLPAPER_TYPE_RESTORE = 0;// 壁纸使用类型
    public static final String KEY_WALLPAPER_IN_USE_PATH = "";// 壁纸使用类型
    public static final String SAVE_KEY_IN_USE_WALLPAPER_TYPE = "in_use_wallpaper_type";// 壁纸使用类型

    public static boolean isSupportBaseThemeSetting(Context context) {
        int versionCode = 1;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        return versionCode >= 21;
    }

    public static void updateWallpaper(Context context, String packageName, int wallpaperType,
            String wallpaperPath) {
        if (isSupportBaseThemeSetting(context)) {
            ContentValues cv = new ContentValues();
            cv.put(KEY_PACKAGE, packageName);
            cv.put(KEY_WALLPAPER_TYPE, wallpaperType);
            cv.put(KEY_WALLPAPER, wallpaperPath);
            context.getContentResolver().update(theme_uri, cv, KEY_PACKAGE + "=?", new String[] {
                packageName
            });
        } else {
            Settings.System.putString(context.getContentResolver(), KEY_WALLPAPER_IN_USE_PATH,
                    wallpaperPath);
            Settings.System.putInt(context.getContentResolver(), SAVE_KEY_IN_USE_WALLPAPER_TYPE,
                    wallpaperType);
        }
    }

    public static boolean getVibrateEnable(Context context, String packageName) {
        if (isSupportBaseThemeSetting(context)) {
            Cursor c = null;
            try {
                c = context.getContentResolver().query(vibrate_uri, null, KEY_PACKAGE + "=?",
                        new String[] {
                            packageName
                        }, null);
                if (c != null && c.getCount() == 1) {
                    c.moveToNext();
                    int state = c.getInt(c.getColumnIndex(KEY_STATE));
                    return state == 1;
                } else {
                    return true;
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        } else {
            return SettingsHelper.getVibrateEnable(context);
        }
    }

    public static boolean getSoundEnable(Context context, String packageName) {
        if (isSupportBaseThemeSetting(context)) {
            Cursor c = null;
            try {
                c = context.getContentResolver().query(vibrate_uri, null, KEY_PACKAGE + "=?",
                        new String[] {
                            packageName
                        }, null);
                if (c != null && c.getCount() == 1) {
                    c.moveToNext();
                    int state = c.getInt(c.getColumnIndex(KEY_STATE));
                    return state == 1;
                } else {
                    return true;
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        } else {
            return SettingsHelper.getSoundEnable(context);
        }
    }

    /**
     * 根据包名获取壁纸的类型
     * 
     * @param context .getContentResolver()
     * @param packageName
     * @return
     */
    public static int getWallpaperType(Context context, String packageName) {
        if (isSupportBaseThemeSetting(context)) {

            Cursor c = null;
            try {
                c = context.getContentResolver().query(theme_uri, null, KEY_PACKAGE + "=?",
                        new String[] {
                            packageName
                        }, null);
                if (c != null && c.getCount() == 1) {
                    c.moveToNext();
                    int type = c.getInt(c.getColumnIndex(KEY_WALLPAPER_TYPE));
                    return type;
                } else {
                    return 0;// 默认使用主题自带的
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        } else {
            return SettingsHelper.getWallpaperType(context);
        }
    }

    /**
     * 根据包名获取壁纸的路径
     * 
     * @param context .getContentResolver()
     * @param packageName
     * @return
     */
    public static String getWallpaperPath(Context context, String packageName) {
        if (isSupportBaseThemeSetting(context)) {
            Cursor c = null;
            try {
                c = context.getContentResolver().query(theme_uri, null, KEY_PACKAGE + "=?",
                        new String[] {
                            packageName
                        }, null);
                if (c != null && c.getCount() == 1) {
                    c.moveToNext();
                    return c.getString(c.getColumnIndex(KEY_WALLPAPER));
                } else {
                    return null;// 默认使用主题自带的
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        } else {
            return SettingsHelper.getWallpaperPath(context);
        }
    }
}
