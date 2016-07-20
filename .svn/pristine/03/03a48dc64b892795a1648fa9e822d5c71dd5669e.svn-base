
package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义的圆角矩形ImageView，可以直接当组件在布局中使用。
 * 
 * @author qinyu
 */
public class RoundRectImageView extends ImageView {

    private Paint paint;

    public RoundRectImageView(Context context) {
        this(context, null);
    }

    public RoundRectImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
    }

    /**
     * 绘制圆角矩形图片
     * 
     * @author caizhiming
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if (null != drawable) {
            paint.setAntiAlias(true);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            // 以宽高填满的方式拉伸图片
            Matrix matrix = new Matrix();
            matrix.postScale((float)getWidth() / bitmap.getWidth(), (float)getHeight() / bitmap.getHeight());
            Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            // 绘制圆角图片
            Shader shader = new BitmapShader(resizeBmp, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            paint.setShader(shader);
            RectF rectF = new RectF(new Rect(0, 0, getWidth(), getHeight()));
            canvas.drawRoundRect(rectF, 20, 20, paint);
        } else {
            super.onDraw(canvas);
        }
    }
}
