
package com.cyou.cma.clocker.theme.technology.algorithm;

import android.graphics.PointF;

/**
 * @author jiangbin
 */
public class PointFInQuadrant {
    private PointF mPointF;

    private Quadrant mQuadrant;

    public PointFInQuadrant(PointF pointF, Quadrant quadrant) {
        this.mPointF = pointF;
        this.mQuadrant = quadrant;
    }

    public PointF getPointF() {
        return mPointF;
    }

    public void setPointF(PointF pointF) {
        this.mPointF = pointF;
    }

    public Quadrant getQuadrant() {
        return mQuadrant;
    }

    public void setQuadrant(Quadrant quadrant) {
        this.mQuadrant = quadrant;
    }
}
