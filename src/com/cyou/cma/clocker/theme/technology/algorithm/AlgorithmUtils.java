
package com.cyou.cma.clocker.theme.technology.algorithm;

import android.util.Log;

public class AlgorithmUtils {

    public AlgorithmUtils() {
    }

    // /**
    // * 求直线和圆的交点,小值
    // *
    // * @return
    // */
    // public static void lineAndCircleMin(Line line, Circle circle, PointF
    // point) {
    // // float k = line.getK()
    // float a = line.getK() * line.getK() + 1;
    // float b = 2 * line.getK() * line.getB() - 2 * line.getK() *
    // circle.getOriginalY() - 2
    // * circle.getOriginalX();
    // float c = circle.getOriginalX() * circle.getOriginalX()
    // + (line.getB() - circle.getOriginalY()) * (line.getB() -
    // circle.getOriginalY())
    // - circle.getRadius() * circle.getRadius();
    //
    // float x1 = (float) ((-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a));
    // // result.x2 = (float) ((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
    // float y1 = line.getK() * x1 + line.getB();
    // point.set(x1, y1);
    // }
    //
    // /**
    // * 求直线和圆的交点,大值
    // *
    // * @return
    // */
    // public static void lineAndCircleMax(Line line, Circle circle, PointF
    // point) {
    // float a = line.getK() * line.getK() + 1;
    // float b = 2 * line.getK() * line.getB() - 2 * line.getK() *
    // circle.getOriginalY() - 2
    // * circle.getOriginalX();
    // // float c = circle.getOriginalX() * circle.getOriginalX()
    // // + (line.getB() - circle.getOriginalY()) * (line.getB() -
    // // circle.getOriginalY());
    // float c = circle.getOriginalX() * circle.getOriginalX()
    // + (line.getB() - circle.getOriginalY()) * (line.getB() -
    // circle.getOriginalY())
    // - circle.getRadius() * circle.getRadius();
    // float x1 = (float) ((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
    // float y1 = line.getK() * x1 + line.getB();
    // point.set(x1, y1);
    // }

    public static float lineAndLineDegree(Line baseLine, Line touchLine) {
        float k1 = baseLine.getK();
        Log.d("lineAndLineDegree", "k1---->" + k1);

        float k2 = touchLine.getK();
        Log.d("lineAndLineDegree", "k2---->" + k2);
        double degree = Math.atan((k2 - k1) / (1 + k1 * k2)) * 180 / Math.PI;
        if (Double.isNaN(degree))
            Log.d("", "not a number k1:" + k1 + " k2:" + k2);
        // Log.d("lineAndLineDegree", "degree---->" + degree);
        // baseLine.getBasePoint().
        PointFInQuadrant basePoint = baseLine.getEndPoint();
        PointFInQuadrant touchPoint = touchLine.getEndPoint();
        if (basePoint.getQuadrant().isSameQuadrant(touchPoint.getQuadrant(), degree)) {
        } else if (basePoint.getQuadrant().isNextQuadrant(touchPoint.getQuadrant(), degree)) {
            degree -= 180;
        } else if (basePoint.getQuadrant().isPreviousQuadrant(touchPoint.getQuadrant(), degree)) {
            // degree = degree;
            degree -= 360;
        } else if (basePoint.getQuadrant().isOpposite(touchPoint.getQuadrant(), degree)) {
            degree -= 180;
        }

        return (float) degree;
    }
}
