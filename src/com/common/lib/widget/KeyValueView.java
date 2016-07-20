
package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.lib.R;

/**
 * 键值对控件View
 * 
 * @author luchonghui
 */
public class KeyValueView extends LinearLayout {

    private TextView mKeyView;
    private TextView mValueView;

    public KeyValueView(Context context, int keyResId, int valueResId) {
        this(context, keyResId, context.getString(valueResId));
    }

    public KeyValueView(Context context, int keyResId, String value) {
        this(context, context.getString(keyResId), value);
    }

    public KeyValueView(Context context, CharSequence key, CharSequence value) {
        this(context, LinearLayout.HORIZONTAL, key, value);
    }

    public KeyValueView(Context context, int orientation, CharSequence key, CharSequence value) {
        this(context, orientation, Gravity.NORMAL, key, value);
    }

    public KeyValueView(Context context, int orientation, int gravity, CharSequence key,
            CharSequence value) {
        super(context);
        int textPadding;
        if (orientation == LinearLayout.HORIZONTAL) {
            textPadding = context.getResources()
                    .getDimensionPixelSize(R.dimen.default_text_padding_right);
        } else {
            textPadding = context.getResources()
                    .getDimensionPixelSize(R.dimen.default_text_padding_bottom);
        }
        // 使用默认值填充字号和文本显示颜色属性
        Resources resources = context.getResources();
        int defaultTextSize = resources.getDimensionPixelSize(R.dimen.text_size_normal);
        int defaultKeyColor = resources.getColor(R.color.black);
        int defaultValueColor = resources.getColor(R.color.gray);

        initView(context, orientation, textPadding, gravity, key, value, defaultTextSize,
                defaultTextSize,
                defaultKeyColor, defaultValueColor, null);
    }

    public KeyValueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.KeyValueView);
        int orientation = getOrientation();
        int gravity = a.getInt(R.styleable.KeyValueView_gravity, 0);
        String key = a.getString(R.styleable.KeyValueView_key);
        String value = a.getString(R.styleable.KeyValueView_value);
        Resources resources = getResources();
        int defaultTextPadding;
        if (orientation == LinearLayout.HORIZONTAL) {
            setGravity(android.view.Gravity.CENTER_VERTICAL);
            defaultTextPadding = resources
                    .getDimensionPixelSize(R.dimen.default_text_padding_right);
        } else {
            setGravity(android.view.Gravity.CENTER_HORIZONTAL);
            defaultTextPadding = resources
                    .getDimensionPixelSize(R.dimen.default_text_padding_bottom);
        }
        int textPadding = a.getDimensionPixelSize(R.styleable.KeyValueView_textPadding,
                defaultTextPadding);
        Drawable drawable = a.getDrawable(R.styleable.KeyValueView_src);

        // 获取字号和文本显示颜色属性
        int defaultTextSize = resources.getDimensionPixelSize(R.dimen.text_size_normal);
        int keyTextSize = a.getDimensionPixelSize(
                R.styleable.KeyValueView_keyTextSize, defaultTextSize);
        int valueTextSize = a.getDimensionPixelSize(
                R.styleable.KeyValueView_valueTextSize, defaultTextSize);
        int defaultKeyColor = resources.getColor(R.color.black);
        int keyColor = a.getColor(R.styleable.KeyValueView_keyTextColor, defaultKeyColor);
        int defaultvalueColor = resources.getColor(R.color.gray);
        int valueColor = a.getColor(R.styleable.KeyValueView_valueTextColor, defaultvalueColor);
        a.recycle();

        initView(context, orientation, textPadding, gravity, key, value, keyTextSize,
                valueTextSize,
                keyColor, valueColor, drawable);
    }

    public TextView getKeyView() {
        return mKeyView;
    }

    public TextView getValueView() {
        return mValueView;
    }

    public void setValue(CharSequence value) {
        if (!TextUtils.isEmpty(value)) {
            mValueView.setText(value);
        }
    }

    // 初始化xml定义和java里生成对象时共同的属性，避免代码重复
    @SuppressLint("InflateParams")
    private void initView(Context context, int orientation, int textPadding, int gravity,
            CharSequence key,
            CharSequence value, int keyTextSize, int valueTextSize, int keyColor, int valueColor,
            Drawable drawable) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if(isInEditMode()) {
            mKeyView = new TextView(context);
        }else{
            mKeyView = (TextView) layoutInflater.inflate(R.layout.view_key, null);
        }
        if(isInEditMode()) {
            mValueView = new TextView(context);
        }else{
            mValueView = (TextView) layoutInflater.inflate(R.layout.view_value, null);
        }

        if (orientation == LinearLayout.HORIZONTAL) {
            mKeyView.setPadding(0, 0, textPadding, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT);
           // setGravity(android.view.Gravity.BOTTOM);
            addView(mKeyView);
            addView(mValueView, params);
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            mKeyView.setPadding(0, 0, 0, textPadding);
            addView(mKeyView, params);
            addView(mValueView, params);
        }
        if (gravity == Gravity.SIDE) {
            mValueView.setGravity(android.view.Gravity.END | android.view.Gravity.CENTER_VERTICAL);
        }
        mKeyView.setTextSize(TypedValue.COMPLEX_UNIT_PX, keyTextSize);
        mKeyView.setTextColor(keyColor);
        if (!TextUtils.isEmpty(key)) {
            mKeyView.setText(key);
        }
        mValueView.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueTextSize);
        mValueView.setTextColor(valueColor);
        if (!TextUtils.isEmpty(value)) {
            mValueView.setText(value);
        }
        mValueView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    public static class Gravity {
        public static final int NORMAL = 0;
        public static final int SIDE = 1;
    }
}
