/**
 * 
 */

package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.TextView;

import com.common.lib.R;

/**
 * textView上的小红点
 * 
 * @author luchonghui
 */
public class RedTipTextView extends TextView {
    public static final int RED_TIP_GONE = 0;
    public static final int RED_TIP_VISIBLE = 1;
    /**
     * 红点是否可见
     */
    private int mTipVisibility = RED_TIP_GONE;
    /**
     * 红点直径
     */
    private int mRadius = 8;

    public RedTipTextView(Context context) {
        super(context);
        init(null);
    }

    public RedTipTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RedTipTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.RedTipTextView);
            mTipVisibility = a.getInt(R.styleable.RedTipTextView_redTipsVisibility, 0);
            int defaultRadius;
            if (isInEditMode()) {
                defaultRadius = 8;
            } else {
                defaultRadius = (int) getResources().getDimension(R.dimen.radius);
            }
            mRadius = (int) a.getDimension(R.styleable.RedTipTextView_dotRadius, defaultRadius);
            a.recycle();
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTipVisibility == RED_TIP_VISIBLE) {
            Paint paint = new Paint();
            float width = getWidth();
            float height = getHeight();
            float textWidth = getPaint().measureText(getText().toString());
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Style.FILL_AND_STROKE);
            canvas.drawCircle(width / 2 + textWidth / 2, (height - getTextSize()) / 2, mRadius,
                    paint);
        }
    }

    /**
     * 为0时红点不可见，为1时红点可见
     * 
     * @param visibility
     */
    public void setDotVisibility(int visibility) {
        mTipVisibility = visibility;
        invalidate();
    }
}
