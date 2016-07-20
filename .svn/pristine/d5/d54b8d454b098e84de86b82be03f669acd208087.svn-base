
package com.common.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
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

import com.common.lib.R;

public class EditTextWithClear extends LinearLayout {

    private EditText mEditText;
    private ImageView mImageView;
    private Boolean mIsOpen = true;

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithClear);
        // int inputType = a.getInt(R.styleable.EditTextWithClear_inputType, InputType.TYPE_NULL);
        int maxLength = a.getInt(R.styleable.EditTextWithClear_maxLength, -1);
        String hint = a.getString(R.styleable.EditTextWithClear_hint);
        int defaultTextSize = context.getResources()
                .getDimensionPixelOffset(
                                         R.dimen.text_size_normal);
        int textSize = a.getDimensionPixelOffset(R.styleable.EditTextWithClear_textSize,
                                                 defaultTextSize);
        int textColor = a.getColor(R.styleable.EditTextWithClear_hintTextColor, context
                .getResources().getColor(R.color.gray_light));
        Drawable drawable = a.getDrawable(R.styleable.EditTextWithClear_src);
        a.recycle();

        setGravity(Gravity.CENTER_VERTICAL);
        mEditText = new EditText(context);
        mEditText.setSingleLine(true);
        // mEditText.setInputType(inputType);
        mEditText.setBackgroundColor(Color.TRANSPARENT);
        mEditText.setHint(hint);
        mEditText.setTextColor(context
                .getResources().getColor(R.color.black));
        mEditText.setHintTextColor(textColor);
        int defaultPaddingLedf = context
                .getResources().getDimensionPixelSize(R.dimen.mini_size);
        mEditText.setPadding(defaultPaddingLedf, 0, 0, 0);
        if (maxLength > 0) {
            mEditText.setFilters(new InputFilter[]{
                    new InputFilter.LengthFilter(maxLength)
            });
        }
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mImageView = new ImageView(context);
        mImageView.setScaleType(ScaleType.FIT_CENTER);
        if (drawable == null) {
            mImageView.setImageResource(R.drawable.clear_type);
            addListener();
        } else {
            mImageView.setImageDrawable(drawable);
            mIsOpen = false;
        }
        mImageView.setPadding(
                              context.getResources().getDimensionPixelSize(R.dimen.small_size), 0,
                              context.getResources().getDimensionPixelSize(R.dimen.small_size), 0);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1);

        addView(mEditText, params);

        LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        addView(mImageView, params2);

        fixClearView();
    }

    public String getText() {
        return mEditText.getText().toString();
    }

    public EditText getEditText() {
        return mEditText;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    private void fixClearView() {
        String text = mEditText.getText().toString();
        if (mIsOpen)
        {
            if (TextUtils.isEmpty(text)) {
                mImageView.setVisibility(View.INVISIBLE);
            } else {
                mImageView.setVisibility(View.VISIBLE);
            }
        }

    }

    public void setIconVisibility(int visibility) {
        mIsOpen = false;
        mImageView.setVisibility(visibility);
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
                fixClearView();
            }
        });
    }
}
