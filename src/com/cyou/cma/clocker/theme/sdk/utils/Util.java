
package com.cyou.cma.clocker.theme.sdk.utils;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class Util {
    public static final boolean DEBUG = true;

    // config
    public static final String SAVE_KEY_FILE = "save_key_file";// sharepreference文件名
    public static final String SAVE_KEY_TOOLS_CALL = "save_key_tools_call";// 是否开启未接来电通知
    public static final String SAVE_KEY_TOOLS_SMS = "save_key_tools_sms";// 是否开启未读短信通知
    public static final String SAVE_KEY_TOOLS_VIBRATE = "save_key_enable_unlock_vibrate";// 是否开启解锁振动
    public static final String SAVE_KEY_TOOLS_SOUND = "save_key_enable_unlock_sound";// 是否开启解锁铃声

    public static final String SAVE_KEY_VIEW_WIDTH = "view_width";// 锁屏视图的宽
    public static final String SAVE_KEY_VIEW_HEIGHT = "view_height";// 锁屏视图的高
    public static final String SAVE_KEY_SCREEN_WIDTH = "screen_width";// 屏幕的宽
    public static final String SAVE_KEY_SCREEN_HEIGHT = "screen_height";// 屏幕的高
    public static final String SAVE_KEY_IN_USE_WALLPAPER_TYPE = "in_use_wallpaper_type";// 壁纸使用类型
    public static final int KEY_WALLPAPER_TYPE_GALLERY = 2;// 壁纸使用类型
    public static final int KEY_WALLPAPER_TYPE_SYSTEM = 1;// 壁纸使用类型
    public static final int KEY_WALLPAPER_TYPE_RESTORE = 0;// 壁纸使用类型
    public static final String KEY_WALLPAPER_IN_USE_PATH = "";// 壁纸使用类型

    public static final long VIBRATE_TIME = 100;

    // unlock type
    public static final int TYPE_UNLOCK = 1;
    public static final int TYPE_CALL = 2;
    public static final int TYPE_SMS = 3;
    public static final int TYPE_CAMERA = 4;

    public static void Log(String tag, String msg) {
        if (DEBUG) {
            Log.d("clocker:" + tag, msg);
        }
    }

    /**
     * Get the screen width
     * 
     * @author mapeng_thun
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * Get the screen height
     * 
     * @author mapeng_thun
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 判断字符是否为空
     * 
     * @param content
     * @return
     */
    public static boolean contentIsNull(String content) {
        return null == content || content.toString().trim().equals("");
    }

    /**
     * 判断屏幕是否亮
     * 
     * @return
     */
    public static boolean isScreenOn(Context mContext) {
        boolean screenOn = false;
        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        if (null != pm) {
            screenOn = pm.isScreenOn();
        }
        return screenOn;
    }

    /**
     * 获取偏移量的接近值，小数点后一位大于0.7则+1
     * 
     * @param offset
     * @return
     */
    public static int getOffseByRound(float offset) {
        float offsetTemp = Math.abs(offset);
        if (offsetTemp - (int) offsetTemp > 0.7f) {
            return (int) offset + (offset > 0 ? 1 : -1);
        } else {
            return (int) offset;
        }
    }

    /**
     * 获取状态栏的高度
     * 
     * @param context
     * @return 状态栏的高度，如果无法获取 返回50像素
     */
    public static int getStatusBarHeight(Context context) {
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
            return statusBarHeight;
        } catch (Exception e) {
        }
        return statusBarHeight;
    }

}
