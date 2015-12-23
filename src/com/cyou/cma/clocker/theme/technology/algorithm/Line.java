
package com.cyou.cma.clocker.theme.technology.algorithm;

import android.graphics.PointF;
import android.util.Log;

/**
 * 直线的定义 y=kx+b
 * 
 * @author jiangbin
 */
public class Line {
    private float k;
    private float b;
    private PointFInQuadrant startPoint;

    private PointFInQuadrant endPoint;

    public Line() {
    }

    public void setPoints(PointFInQuadrant startPoint, PointFInQuadrant endPoint) {
        k = (startPoint.getPointF().y - endPoint.getPointF().y)
                / (startPoint.getPointF().x - endPoint.getPointF().x);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        // 设置象限
        PointF startPointF = startPoint.getPointF();
        PointF endPointF = endPoint.getPointF();
        Quadrant endPointQuadrant = endPoint.getQuadrant();
        // TODO 判断是否给力
        if (endPointF.x >= startPointF.x && endPointF.y <= startPointF.y) {
            endPointQuadrant.setQuadrant(Quadrant.ONE);
        } else if (endPointF.x <= startPointF.x && endPointF.y <= startPointF.y) {
            endPointQuadrant.setQuadrant(Quadrant.TWO);
        } else if (endPointF.x <= startPointF.x && endPointF.y >= startPointF.y) {
            endPointQuadrant.setQuadrant(Quadrant.THREE);
        } else if (endPointF.x >= startPointF.x && endPointF.y >= startPointF.y) {
            endPointQuadrant.setQuadrant(Quadrant.FOUR);
        }

    }

    public void setPoints(PointF startPointF, PointF endPointF) {
        k = (startPointF.y - endPointF.y) / (startPointF.x - endPointF.x);
        b = startPointF.y - k * startPointF.x;
//        int newb = 
        float x  = 254/k+100;
        Log.d("", "x:"+x);
    }

    public float getK() {
        return k;
    }

    // public void setK(float k) {
    // this.k = k;
    // }

    public PointFInQuadrant getStartPoint() {
        return startPoint;
    }

    public PointFInQuadrant getEndPoint() {
        return endPoint;
    }

    public float getB() {
        return b;
    }

    // public void setB(float b) {
    // this.b = b;
    // }

    // public int getQuadrant() {
    // return quadrant;
    // }

    /**
     * 获取直线两点的距离
     * 
     * @return
     */
    // public static float distance(PointF point1, PointF point2) {
    // return (float) Math.sqrt((Math.pow((point1.x - point2.x), 2) + Math.pow(
    // (point1.y - point2.y), 2)));
    //
    // }
}
