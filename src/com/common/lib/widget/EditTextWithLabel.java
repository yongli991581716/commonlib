
package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.lib.R;

/**
 * 登录/改密码 那类典型视图 Label: type clear_icon
 * 
 * @author qinyu
 */
public class EditTextWithLabel extends LinearLayout {

    private TextView mLabelView;
    private EditText mEditText;
    // 清空输入内容图标，当有输入内容时，会显示出来
    private ImageView mImageView;

    private String mHint;
    private String mLabel;
    private Drawable mDrawable;
    private int mInputType;
    private boolean mHiddenIcon;
    // 对输入框最大输入长度的限制
    private int mMaxLength;
    private int mGravity;

    private int mLabelTextColor;
    private int mEditTextColor;

    public EditTextWithLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
        initView();
    }

    /**
     * 通过getLabelView()直接对TextView操作
     */
    @Deprecated
    public void setLabel(String label) {
        mLabel = label;
        mLabelView.setText(mLabel);
    }

    /**
     * 通过getLabelView()直接对TextView操作
     */
    @Deprecated
    public String getLabel() {
        return mLabel;
    }
    
    public TextView getLabelView() {
        return mLabelView;
    }

    /**
     * 获取用户输入的值
     * 
     * @return
     */
    public String getText() {
        return mEditText.getText().toString();
    }

    public void setText(String text) {
        mEditText.setText(text);
        fixImageView();
    }

    public EditText getEditText() {
        return mEditText;
    }

    private void setIconVisibility(int visibility) {
        mHiddenIcon = visibility != View.VISIBLE;
        mImageView.setVisibility(visibility);
        fixImageView();
    }

    public ImageView getImageView() {
        return mImageView;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("Recycle")
    private void initData(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithLabel);
        mLabel = a.getString(R.styleable.EditTextWithLabel_label);
        mHint = a.getString(R.styleable.EditTextWithLabel_hint);
        mMaxLength = a.getInt(R.styleable.EditTextWithLabel_maxLength, -1);
        mInputType = a.getInt(R.styleable.EditTextWithLabel_inputType, InputType.TYPE_CLASS_TEXT);
        mDrawable = a.getDrawable(R.styleable.EditTextWithLabel_src);
        mHiddenIcon = a.getBoolean(R.styleable.EditTextWithLabel_hideIcon, false);
        mGravity = a.getInt(R.styleable.EditTextWithLabel_gravity, 0);
        
        int defaultLabelColor;
        int defaultEditColor;
        if(isInEditMode()) {
            defaultLabelColor = 0x999999;
            defaultEditColor = 0x575757;
        }else{
            defaultLabelColor = context.getResources().getColor(R.color.gray);
            defaultEditColor = context.getResources().getColor(R.color.black);
        }
        mLabelTextColor = a.getColor(R.styleable.EditTextWithLabel_labelTextColor, defaultLabelColor);
        mEditTextColor = a.getColor(R.styleable.EditTextWithLabel_editTextLabelColor, defaultEditColor);

        if (mLabel == null) {
            mLabel = "";
        }
        if (mHint == null) {
            mHint = "";
        }
        if (mDrawable == null && !isInEditMode()) {
            mDrawable = context.getResources().getDrawable(R.drawable.clear_type);
        }
    }

    private void initView() {
        // 构造view对象
        mLabelView = new TextView(getContext());
        mEditText = new EditText(getContext());
        mImageView = new ImageView(getContext());

        // 初始化值
        Resources res = getContext().getResources();
        int textSize;
        int hintColor;
        if(isInEditMode()) {
            textSize = (int)getResources().getDisplayMetrics().density * 15;
            hintColor = 0xCDCDCD;
        }else{
            textSize = res.getDimensionPixelSize(R.dimen.text_size_normal);
            hintColor = res.getColor(R.color.gray_light);
        }

        mLabelView.setText(mLabel);
        mLabelView.setSingleLine();
        mLabelView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mLabelView.setTextColor(mLabelTextColor);

        mEditText.setHint(mHint);
        mEditText.setSingleLine();
        mEditText.setInputType(mInputType);
        mEditText.setBackgroundColor(Color.TRANSPARENT);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mEditText.setTextColor(mEditTextColor);
        mEditText.setHintTextColor(hintColor);
        if (mMaxLength > 0) {
            mEditText.setFilters(new InputFilter[] {
                    new InputFilter.LengthFilter(mMaxLength)
            });
        }
        if (mGravity == 1) { // app:gravity="side"
            mEditText.setGravity(android.view.Gravity.END | android.view.Gravity.CENTER_VERTICAL);
        }

        mImageView.setScaleType(ScaleType.FIT_CENTER);
        mImageView.setImageDrawable(mDrawable);
        
        int padding;
        if(isInEditMode()) {
            padding = (int)getResources().getDisplayMetrics().density * 10;
        }else{
            padding = res.getDimensionPixelSize(R.dimen.small_size);
        }
        mImageView.setPadding(padding, 0, padding, 0);
        
        if (mHiddenIcon) {
            setIconVisibility(View.GONE);
        } else {
            setIconVisibility(View.VISIBLE);
        }

        // 添加到Layout
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        addView(mLabelView);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT, 1);
        addView(mEditText, params);
        LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        addView(mImageView, params2);
        fixImageView();
        addListener();
    }

    private void fixImageView() {
        if (!mHiddenIcon) {
            String text = mEditText.getText().toString();
            if (TextUtils.isEmpty(text)) {
                mImageView.setVisibility(View.INVISIBLE);
            } else {
                mImageView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void addListener() {
        mImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mEditText.setText("");
            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                fixImageView();
            }
        });
    }
}
