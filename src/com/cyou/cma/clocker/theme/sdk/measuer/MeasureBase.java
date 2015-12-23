
package com.cyou.cma.clocker.theme.sdk.measuer;

import android.content.Context;

import com.cyou.cma.clocker.theme.sdk.utils.Util;

public abstract class MeasureBase {

    private float mWidthRato;
    private float mHeightRato;

    protected MeasureBase() {
    }

    public abstract int getBaseWidth();

    public abstract int getBaseHeight();

    /**
     * 设计时的基准屏幕密度
     * 
     * @return
     */
    public abstract float getBaseDensity();

    public float getWidthRato(Context context) {
        if (mWidthRato == 0)
            mWidthRato = Util.getScreenWidth(context) / (getBaseWidth() * 1.0f);
        return mWidthRato;
    }

    public float getHeightRato(Context context) {
        if (mHeightRato == 0)
            mHeightRato = (Util.getScreenHeight(context)) / (getBaseHeight() * 1.0f);
        return mHeightRato;
    }

    public float getSmallerOne(Context context) {
        return getWidthRato(context) > getHeightRato(context) ? mHeightRato : mWidthRato;
    }

    /**
     * 把目标设备的像素转换成设计时的像素的比例
     * 
     * @return
     */
    public float getTargetPixel2BasePixelRato(float targetDensity) {
        return getBaseDensity() / targetDensity;
    }
}
