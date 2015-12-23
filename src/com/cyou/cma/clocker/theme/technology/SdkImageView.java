
package com.cyou.cma.clocker.theme.technology;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cyou.cma.clocker.theme.sdk.measuer.DefaultMeasureBase;

public class SdkImageView extends ImageView {

    private DefaultMeasureBase mDefaultMeasureBase;

    private float mDensity;

    public SdkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SdkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDefaultMeasureBase = DefaultMeasureBase.getDefaultMeasureBase();
        mDensity = context.getResources().getDisplayMetrics().density;
    }

    public SdkImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int newWidth = (int) (getMeasuredWidth()
                * mDefaultMeasureBase.getTargetPixel2BasePixelRato(mDensity) * mDefaultMeasureBase
                .getWidthRato(getContext()));
        int newHeight = (int) (getMeasuredHeight()
                * mDefaultMeasureBase.getTargetPixel2BasePixelRato(mDensity) * mDefaultMeasureBase
                .getWidthRato(getContext()));
        // getMeasuredHeight();
        setMeasuredDimension(newWidth, newHeight);
    }

    @Override
    public void setAlpha(int alpha) {

        if (this.getDrawable() != null) {
            this.getDrawable().setAlpha(alpha);
        }
        if (this.getBackground() != null)
            this.getBackground().setAlpha(alpha);
    }

}
