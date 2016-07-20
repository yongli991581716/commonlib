/**
 * 
 */

package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.lib.R;

/**
 * 购买自定义控件
 * 
 * @author luchonghui
 */
@SuppressLint("Recycle")
public class BuyModeView extends RelativeLayout implements TextWatcher {

    private TextView mGoodsDisCountTv;
    private TextView mGoodsPriceTv;

    private ImageView mShopCart;

    private ImageView mPlus;

    private ImageView mMinus;
    // 带数字加减
    private ImageView mNumPlus;

    private ImageView mNumMinus;

    private EditText mEditNum;

    private boolean mBuyMode;
    private boolean mBuyNumMode;
    private int mAddOne = 0;
    private LinearLayout mbuyLayout;
    private LinearLayout mbuyWithNumLayout;
    private LinearLayout mPriceLayout;
    // 商品数量
    private int mGoodsNum = 0;

    private OnQuantityChangeListener mOnQuantityChangeListener;

    /**
     * @param context
     */
    public BuyModeView(Context context) {
        super(context);
        init(context, null);
    }

    /**
     * @param context
     * @param attrs
     */
    public BuyModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public BuyModeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        mOnQuantityChangeListener = onQuantityChangeListener;
    }

    private void init(Context context, AttributeSet attrs)
    {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BuyModeView);
        String goodsPrice = a.getString(R.styleable.BuyModeView_textPricevalue);
        String disPrice = a.getString(R.styleable.BuyModeView_textDisvalue);
        Drawable drawable = a.getDrawable(R.styleable.BuyModeView_cartSrc);
        Drawable plus = a.getDrawable(R.styleable.BuyModeView_plusSrc);
        Drawable minus = a.getDrawable(R.styleable.BuyModeView_minusSrc);
        Drawable plusNum = a.getDrawable(R.styleable.BuyModeView_plusNumSrc);
        Drawable minusNum = a.getDrawable(R.styleable.BuyModeView_minusNumSrc);
        int plusBg = a.getColor(R.styleable.BuyModeView_plusColor,
                context.getResources().getColor(R.color.main_color));
        int minusBg = a.getColor(R.styleable.BuyModeView_minusColor, context
                .getResources()
                .getColor(R.color.bg_area));
        int priceColor = a.getColor(R.styleable.BuyModeView_textPriceColor, context
                .getResources()
                .getColor(R.color.red));
        int disPriceColor = a.getColor(R.styleable.BuyModeView_textDisColor, context
                .getResources()
                .getColor(R.color.gray));

        int priceSize = a
                .getDimensionPixelSize(R.styleable.BuyModeView_textPriceSize,
                        context.getResources()
                                .getDimensionPixelSize(R.dimen.text_size_normal));

        int disPriceSize = a
                .getDimensionPixelSize(R.styleable.BuyModeView_textDisSize,
                        context.getResources()
                                .getDimensionPixelSize(R.dimen.text_size_small));
        int numSize = a
                .getDimensionPixelSize(R.styleable.BuyModeView_textPriceSize,
                        context.getResources()
                                .getDimensionPixelSize(R.dimen.text_size_normal));
        mBuyMode = a.getBoolean(R.styleable.BuyModeView_buyMode, false);
        mBuyNumMode = a.getBoolean(R.styleable.BuyModeView_buyNumMode, false);

        mPriceLayout = new LinearLayout(context);
        LinearLayout.LayoutParams priceParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mPriceLayout.setLayoutParams(priceParams);
        mPriceLayout.setOrientation(LinearLayout.HORIZONTAL);
        int textPadding = a
                .getDimensionPixelSize(R.styleable.BuyModeView_pricePadding,
                        context.getResources()
                                .getDimensionPixelSize(R.dimen.mini_size));
        int cartPadding = a
                .getDimensionPixelSize(R.styleable.BuyModeView_cartPadding,
                        context.getResources()
                                .getDimensionPixelSize(R.dimen.normal_size));
        int textPaddingLeft = a
                .getDimensionPixelSize(R.styleable.BuyModeView_textPaddingLeft,
                        context.getResources()
                                .getDimensionPixelSize(R.dimen.normal_size));
        int textNumPadding = a
                .getDimensionPixelSize(R.styleable.BuyModeView_textNumPadding,
                        context.getResources()
                                .getDimensionPixelSize(R.dimen.miniest_size));
        a.recycle();

        LinearLayout.LayoutParams carParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        carParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        LinearLayout.LayoutParams disParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1);
        disParams.gravity = Gravity.CENTER_VERTICAL;
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        textParams.gravity = Gravity.CENTER_VERTICAL;
        mGoodsPriceTv = new TextView(context);
        mGoodsPriceTv.setPadding(textPaddingLeft, textPadding, 0, textPadding);
        mGoodsPriceTv.setText(goodsPrice);
        mGoodsPriceTv.setTextColor(priceColor);
        mGoodsPriceTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, priceSize);
        mGoodsPriceTv.setSingleLine(true);
        mGoodsPriceTv.setLayoutParams(textParams);

        mGoodsDisCountTv = new TextView(context);
        mGoodsDisCountTv.setSingleLine(true);
        mGoodsDisCountTv.setPadding(textPadding, 0, 0, 0);
        mGoodsDisCountTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mGoodsDisCountTv.setText(disPrice);
        mGoodsDisCountTv.setTextColor(disPriceColor);
        mGoodsDisCountTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, disPriceSize);
        mGoodsDisCountTv.setLayoutParams(disParams);

        mShopCart = new ImageView(context);
        if (drawable != null)
        {
            mShopCart.setPadding(0, 0, cartPadding, 0);

            mShopCart.setImageDrawable(drawable);
        }
        mShopCart.setLayoutParams(carParams);

        // 增加priceLayout将装现价和原价，是为了避免价格太长，导致购物车被挤到后面，无法显示出来
        LinearLayout priceLayout = new LinearLayout(context);
        priceLayout.setOrientation(LinearLayout.HORIZONTAL);
        priceLayout.setGravity(Gravity.CENTER_VERTICAL);
        priceLayout.addView(mGoodsPriceTv);
        priceLayout.addView(mGoodsDisCountTv);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        mPriceLayout.addView(priceLayout, params);
        mPriceLayout.addView(mShopCart);
        addView(mPriceLayout);

        mPriceLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mGoodsNum = 1;
                mAddOne = 1;
                mEditNum.setText(mGoodsNum + "");
                mEditNum.setSelection(1);
                setBuyMode(true);
            }
        });

        mbuyLayout = new LinearLayout(context);
        LinearLayout.LayoutParams buyParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mbuyLayout.setLayoutParams(buyParams);
        mbuyLayout.setOrientation(LinearLayout.HORIZONTAL);
        mPlus = new ImageView(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1);
        mPlus.setLayoutParams(params1);
        if (plus != null)
        {
            mPlus.setImageDrawable(plus);
            mPlus.setBackgroundColor(plusBg);
            mPlus.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mGoodsNum++;
                    mAddOne = 1;
                    mEditNum.setText(mGoodsNum + "");
                    mEditNum.setSelection(1);
                }
            });
        }
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1);
        mMinus = new ImageView(context);
        if (minus != null)
        {
            mMinus.setImageDrawable(minus);
            mMinus.setBackgroundColor(minusBg);
            mMinus.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mGoodsNum--;
                    mAddOne = 0;
                    if (mGoodsNum <= 0) {
                        mGoodsNum = 0;
                        setBuyMode(false);
                    }
                    mEditNum.setText(mGoodsNum + "");
                    mEditNum.setSelection(1);
                }
            });
        }
        mMinus.setLayoutParams(params2);
        mbuyLayout.addView(mMinus);
        mbuyLayout.addView(mPlus);

        addView(mbuyLayout);

        mbuyWithNumLayout = new LinearLayout(context);
        LinearLayout.LayoutParams buyWithNumParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        mbuyWithNumLayout.setLayoutParams(buyWithNumParams);
        mbuyWithNumLayout.setOrientation(LinearLayout.HORIZONTAL);
        mNumPlus = new ImageView(context);
        LinearLayout.LayoutParams paramsPlus = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1);
        mNumPlus.setLayoutParams(paramsPlus);
        if (plusNum != null)
        {
            mNumPlus.setImageDrawable(plusNum);
            mNumPlus.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mGoodsNum++;
                    mAddOne = 1;
                    String value = mGoodsNum + "";
                    mEditNum.setText(value);
                    mEditNum.setSelection(mEditNum.getEditableText().toString().length());
                }
            });
        }
        mEditNum = new EditText(context);
        LinearLayout.LayoutParams paramsNum = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT, 1);
        mEditNum.setLayoutParams(paramsNum);
        mEditNum.setBackgroundResource(R.drawable.selector_edit_gray_maincolor);
        mEditNum.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        mEditNum.setSingleLine();
        mEditNum.setTextColor(context.getResources().getColor(R.color.black));
        mEditNum.setPadding(textNumPadding, textNumPadding, textNumPadding, textNumPadding);
        mEditNum.setTextSize(TypedValue.COMPLEX_UNIT_PX, numSize);
        mEditNum.setInputType(InputType.TYPE_CLASS_NUMBER);
        InputFilter[] filters = {
                new InputFilter.LengthFilter(8)
        };
        mEditNum.setFilters(filters);
        mNumMinus = new ImageView(context);
        LinearLayout.LayoutParams paramsMinus = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1);
        mNumMinus.setLayoutParams(paramsMinus);
        if (minusNum != null)
        {
            mNumMinus.setImageDrawable(minusNum);

            mNumMinus.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mGoodsNum--;
                    mAddOne = 0;
                    if (mGoodsNum <= 0) {
                        mGoodsNum = 0;
                        setBuyMode(false);
                    }
                    String value = mGoodsNum + "";
                    mEditNum.setText(value);
                    mEditNum.setSelection(mEditNum.getEditableText().toString().length());
                }
            });
        }
        mbuyWithNumLayout.addView(mNumMinus);
        mbuyWithNumLayout.addView(mEditNum);
        mbuyWithNumLayout.addView(mNumPlus);
        addView(mbuyWithNumLayout);
        if (mBuyMode) {
            if (mBuyNumMode) {
                mbuyWithNumLayout.setVisibility(View.VISIBLE);
                mbuyLayout.setVisibility(View.GONE);
            }
            else {
                mbuyLayout.setVisibility(View.VISIBLE);
                mbuyWithNumLayout.setVisibility(View.GONE);
            }
            mPriceLayout.setVisibility(View.GONE);
        } else
        {
            mbuyLayout.setVisibility(View.GONE);
            mbuyWithNumLayout.setVisibility(View.GONE);
            mPriceLayout.setVisibility(View.VISIBLE);
        }
        mEditNum.addTextChangedListener(this);

    }

    public boolean isBuyMode() {
        return mBuyMode;
    }

    public void setBuyMode(boolean mBuyMode) {
        if (this.mBuyMode == mBuyMode) {
            return;
        }
        this.mBuyMode = mBuyMode;
        if (mBuyMode) {
            if (mBuyNumMode) {
                mbuyWithNumLayout.setVisibility(View.VISIBLE);
                mbuyLayout.setVisibility(View.GONE);
            }
            else {
                mbuyLayout.setVisibility(View.VISIBLE);
                mbuyWithNumLayout.setVisibility(View.GONE);
            }
            mPriceLayout.setVisibility(View.GONE);
        } else
        {
            mbuyLayout.setVisibility(View.GONE);
            mbuyWithNumLayout.setVisibility(View.GONE);
            mPriceLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取打折价View
     * 
     * @return
     */
    public TextView getmGoodsDisCountTv() {
        return mGoodsDisCountTv;
    }

    /**
     * 获取价格View
     * 
     * @return
     */
    public TextView getmGoodsPriceTv() {
        return mGoodsPriceTv;
    }

    /**
     * 获取购物车View
     * 
     * @return
     */
    public ImageView getmShopCart() {
        return mShopCart;
    }

    /**
     * 获取添加View
     * 
     * @return
     */
    public ImageView getmPlus() {
        return mPlus;
    }

    /**
     * 获取减少View
     * 
     * @return
     */
    public ImageView getmMinus() {
        return mMinus;
    }

    /**
     * 调用此方法，不会触发onQuantityChanged回调
     * 
     * @param mGoodsNum
     */
    public void setGoodsNum(int goodsNum) {
        mEditNum.removeTextChangedListener(this);
        boolean isUpdateUi = false;
        // 购买数量从0->有购买 或者 从 有购买->0,此时需要更新View显示
        if (this.mGoodsNum == 0 && goodsNum > 0 || this.mGoodsNum > 0 && goodsNum == 0) {
            isUpdateUi = true;
        }
        mGoodsNum = goodsNum;
        String value = mGoodsNum + "";
        mEditNum.setText(value);
        mEditNum.setSelection(mEditNum.getEditableText().toString().length());
        if (isUpdateUi) {
            if (mGoodsNum == 0) {
                setBuyMode(false);
            } else {
                setBuyMode(true);
            }
        }
        mEditNum.addTextChangedListener(this);
    }

    public int getmGoodsNum() {
        return mGoodsNum;
    }

    public interface OnQuantityChangeListener {

        /** 数量增加类型 */
        public static final int SUB = 0;
        /** 数量减少类型 */
        public static final int ADD = 1;

        /**
         * 添加商品到购物车
         * 
         * @param quantity 商品数量
         * @param addGoodsMode 0 减少 1 增加
         */
        public void onQuantityChanged(int quantity, int addGoodsMode);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String text = mEditNum.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            mGoodsNum = Integer.parseInt(text);
            if (mOnQuantityChangeListener != null) {
                mOnQuantityChangeListener.onQuantityChanged(mGoodsNum, mAddOne);
                mAddOne = 0;
            }
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
