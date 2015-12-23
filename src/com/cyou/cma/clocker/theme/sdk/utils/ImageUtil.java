
package com.cyou.cma.clocker.theme.sdk.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

public class ImageUtil {
    private final static String TAG = "ImageUtil";

    public static Bitmap getWallpaper(Context context, int defaultId, String packageName) {
        // int wallpaperType =
        // Settings.System.getInt(context.getContentResolver(),
        // Util.SAVE_KEY_IN_USE_WALLPAPER_TYPE,
        // Util.KEY_WALLPAPER_TYPE_RESTORE);
        int wallpaperType = ProviderHelper.getWallpaperType(context, packageName);
        switch (wallpaperType) {
            case Util.KEY_WALLPAPER_TYPE_RESTORE:
                int viewHeight = Util.getScreenHeight(context) - getStatusBarHeight(context);
                int viewWidth = Util.getScreenWidth(context);
                try {
                    return getCoreBitmapWithSize(context,
                            ImageUtil.readBitmapBySream(context, defaultId), viewWidth, viewHeight);
                } catch (OutOfMemoryError error) {
                    error.printStackTrace();
                }
                break;
            case Util.KEY_WALLPAPER_TYPE_SYSTEM:
                return getSystemWallpaperCore(context);
            case Util.KEY_WALLPAPER_TYPE_GALLERY:
                // String path =
                // Settings.System.getString(context.getContentResolver(),
                // Util.KEY_WALLPAPER_IN_USE_PATH);
                String path = ProviderHelper.getWallpaperPath(context, packageName);
                File file = new File(path);
                if (file.exists()) {
                    try {
                        // imageView.setImageURI(Uri.fromFile(file));
                        return readBitmapWithDensityByPath(context, path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                    }
                } else {
                    // Settings.System.putString(context.getContentResolver(),
                    // Util.KEY_WALLPAPER_IN_USE_PATH, "");
                    // Settings.System.putInt(context.getContentResolver(),
                    // Util.SAVE_KEY_IN_USE_WALLPAPER_TYPE,
                    // Util.KEY_WALLPAPER_TYPE_RESTORE);
                    ProviderHelper.updateWallpaper(context, packageName,
                            Util.KEY_WALLPAPER_TYPE_RESTORE, "");
                    int viewHeight1 = Util.getScreenHeight(context) - getStatusBarHeight(context);
                    int viewWidth1 = Util.getScreenWidth(context);
                    try {
                        return getCoreBitmapWithSize(context,
                                ImageUtil.readBitmapBySream(context, defaultId), viewWidth1,
                                viewHeight1);
                    } catch (OutOfMemoryError error) {
                        error.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
        return null;
    }

    /**
     * 按照系统Density来获取本地bitmap， 会根据不同密度返回不同大小的bitmap
     * 
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitmapWithDensity(Context context, int resId) {
        // return BitmapFactory.decodeResource(context.getResources(), resId);
        try {
            return BitmapFactory.decodeResource(context.getResources(), resId);
        } catch (Exception e) {
            return null;
        } catch (Error e) {
            return null;
        }
    }

    /**
     * 按照系统Density来获取文件中系统中的bitmap， 会根据不同密度返回不同大小的bitmap
     * 
     * @param context
     * @param path
     * @return
     */
    public static Bitmap readBitmapWithDensityByPath(Context context, String path) {
        return BitmapFactory.decodeFile(path);
    }

    /**
     * 以最省内存的方式读取本地资源
     * 
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitmapBySream(Context context, int resId) {
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

    public static Bitmap getSystemWallpaperCore(Context context) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        Bitmap tempBitmap = ((BitmapDrawable) wallpaperManager.getDrawable()).getBitmap();
        return getCoreBitmapWithSize(context, tempBitmap, Util.getScreenWidth(context),
                Util.getScreenHeight(context));
    }

    public static Bitmap getCoreBitmapWithSize(Context mContext, Bitmap tempBitmap, int viewWidth,
            int viewHeight) {
        Bitmap bitmap = null;
        try {
            int x, y, width, height;

            Util.Log(
                    TAG,
                    "getWidth-->" + tempBitmap.getWidth() + " getHeight-->"
                            + tempBitmap.getHeight() + " getScreenWidth-->" + viewWidth
                            + " getScreenHeight-->" + viewHeight);
            Util.Log(TAG, "tempScale-->" + (tempBitmap.getWidth() / (float) tempBitmap.getHeight())
                    + " UtilScale-->" + (viewWidth / (float) viewHeight));
            if (tempBitmap.getWidth() / (float) tempBitmap.getHeight() > viewWidth
                    / (float) viewHeight) {// 按照高度裁剪
                y = 0;
                height = tempBitmap.getHeight();
                width = height * viewWidth / viewHeight;
                x = (tempBitmap.getWidth() - width) / 2;
            } else {// 按照宽度裁剪
                x = 0;
                width = tempBitmap.getWidth();
                height = width * viewHeight / viewWidth;
                y = tempBitmap.getHeight() - height;
            }
            // Util.Log(TAG, "x-->" + x + " y-->" + y + " width-->" + width +
            // " height-->" + height);
            if (x < 0)
                x = 0;
            if (y < 0)
                y = 0;
            if ((x + width) > tempBitmap.getWidth() || (y + height) > tempBitmap.getHeight()) {
                return tempBitmap;
            }
            try {
                bitmap = Bitmap.createBitmap(tempBitmap, x, y, width, height);
            } catch (Exception e) {
                e.printStackTrace();
                
                return tempBitmap;
            }
            tempBitmap = null;
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    public static Bitmap getCoreBitmap(Context mContext, int imageRes) {
        int viewHeight = Util.getScreenHeight(mContext) - getStatusBarHeight(mContext);
        int viewWidth = Util.getScreenWidth(mContext);
        return getCoreBitmapWithSize(mContext, imageRes, viewWidth, viewHeight);
    }

    /**
     * 获取状态栏高度
     * 
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 50;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    public static Bitmap getCoreBitmapWithSize(Context mContext, int imageRes, int viewWidth,
            int viewHeight) {
        Bitmap bitmap = null;
        try {
            int x, y, width, height;
            Bitmap tempBitmap = ImageUtil.readBitmapBySream(mContext, imageRes);

            if (tempBitmap.getWidth() / (float) tempBitmap.getHeight() > viewWidth
                    / (float) viewHeight) {// 按照高度裁剪
                y = 0;
                height = tempBitmap.getHeight();
                width = height * viewWidth / viewHeight;
                x = (tempBitmap.getWidth() - width) / 2;
            } else {// 按照宽度裁剪
                x = 0;
                width = tempBitmap.getWidth();
                height = width * viewHeight / viewWidth;
                y = tempBitmap.getHeight() - height;
            }
            if (x < 0)
                x = 0;
            if (y < 0)
                y = 0;
            if ((x + width) > tempBitmap.getWidth() || (y + height) > tempBitmap.getHeight()) {
                return tempBitmap;
            }
            try {
                bitmap = Bitmap.createBitmap(tempBitmap, x, y, width, height);
            } catch (Exception e) {
                e.printStackTrace();
                return tempBitmap;
            }
            tempBitmap = null;
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    public static boolean isValidBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        } else {
            return true;
        }
    }

    public static void recycleBitmap(Bitmap bitmap) {
        try {
            if (bitmap != null) {
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                bitmap = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
