
package com.cyou.cma.clocker.theme.technology.algorithm;

/**
 * 象限类
 * 
 * @author jiangbin
 */
public class Quadrant {
    public final static int ONE = 1;
    public final static int TWO = 2;
    public final static int THREE = 3;
    public final static int FOUR = 4;
    private int quadrant;

    public Quadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    public void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    /**
     * 是否是下一个象限
     * 
     * @param quadrant
     * @return
     */
    public boolean isNextQuadrant(Quadrant quadrant, double degree) {
        // if(this.quadrant<quadrant.quadrant)
        boolean isNextQuadrant = false;
        switch (this.quadrant) {
            case ONE:
                if (degree > 0 && (quadrant.quadrant == THREE || quadrant.quadrant == TWO)) {
                    isNextQuadrant = true;
                }
                break;
            case TWO:
                if (degree > 0 && (quadrant.quadrant == THREE || quadrant.quadrant == FOUR)) {
                    isNextQuadrant = true;
                }
                break;
            case THREE:
                if (degree > 0 && (quadrant.quadrant == FOUR || quadrant.quadrant == ONE)) {
                    isNextQuadrant = true;
                }
                break;
            case FOUR:
                if (degree > 0 && (quadrant.quadrant == TWO || quadrant.quadrant == ONE)) {
                    isNextQuadrant = true;
                }
                break;
        }

        return isNextQuadrant;
    }

    /**
     * 是否是上一个象限
     * 
     * @param quadrant
     * @return
     */
    public boolean isPreviousQuadrant(Quadrant quadrant, double degree) {
        boolean isPreviousQuadrant = false;
        switch (this.quadrant) {
            case ONE:
                if (degree > 0 && (quadrant.quadrant == ONE || quadrant.quadrant == FOUR)) {
                    isPreviousQuadrant = true;
                }
                break;
            case TWO:
                if (degree > 0 && (quadrant.quadrant == ONE || quadrant.quadrant == TWO)) {
                    isPreviousQuadrant = true;
                }
                break;
            case THREE:
                if (degree > 0 && (quadrant.quadrant == TWO || quadrant.quadrant == THREE)) {
                    isPreviousQuadrant = true;
                }
                break;
            case FOUR:
                if (degree > 0 && (quadrant.quadrant == FOUR || quadrant.quadrant == THREE)) {
                    isPreviousQuadrant = true;
                }
                break;
        }

        return isPreviousQuadrant;
    }

    /**
     * 是否同一个象限
     * 
     * @param quadrant
     * @return
     */
    public boolean isSameQuadrant(Quadrant quadrant, double degree) {
        boolean isSameQuadrant = false;
        switch (this.quadrant) {
            case ONE:
                if (degree < 0 && (quadrant.quadrant == ONE || quadrant.quadrant == TWO)) {
                    isSameQuadrant = true;
                }
                break;
            case TWO:
                if (degree < 0 && (quadrant.quadrant == TWO || quadrant.quadrant == THREE)) {
                    isSameQuadrant = true;
                }
                break;
            case THREE:
                if (degree < 0 && (quadrant.quadrant == THREE || quadrant.quadrant == FOUR)) {
                    isSameQuadrant = true;
                }
                break;
            case FOUR:
                if (degree < 0 && (quadrant.quadrant == FOUR || quadrant.quadrant == ONE)) {
                    isSameQuadrant = true;
                }
                break;
        }

        return isSameQuadrant;
    }

    /**
     * 是否相对的象限
     * 
     * @param quadrant
     * @return
     */
    public boolean isOpposite(Quadrant quadrant, double degree) {
        return !isNextQuadrant(quadrant, degree) && !isSameQuadrant(quadrant, degree)
                && !isPreviousQuadrant(quadrant, degree) && degree != 0;
    }

}
