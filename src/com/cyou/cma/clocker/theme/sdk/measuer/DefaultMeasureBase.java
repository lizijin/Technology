
package com.cyou.cma.clocker.theme.sdk.measuer;

public class DefaultMeasureBase extends MeasureBase {
    private static DefaultMeasureBase defaultMeasureBase = null;

    private DefaultMeasureBase() {

    }

    @Override
    public int getBaseWidth() {
        return 720;
    }

    @Override
    public int getBaseHeight() {
        return 1280;
    }

    @Override
    public float getBaseDensity() {
        return 2.0f;
    }

    public synchronized static DefaultMeasureBase getDefaultMeasureBase() {
        if (defaultMeasureBase == null) {
            defaultMeasureBase = new DefaultMeasureBase();
        }
        return defaultMeasureBase;
    }
}
