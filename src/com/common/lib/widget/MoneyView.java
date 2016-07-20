
package com.common.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.lib.R;

import java.text.DecimalFormat;

/**
 * 整数部分size大于小数部分size控件
 * 
 * @author liyong
 */
public class MoneyView extends LinearLayout {

    private String mIntValue = "0";
    private String mDoubleValue = ".00";
    private String mUnitValue = "元";

    private TextView mIntMoneyTv;
    private TextView mDoubleMoneyTv;
    private TextView mUnitMoneyTv;

    private Context mContext;

    public MoneyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        setOrientation(HORIZONTAL);
        setGravity(Gravity.BOTTOM);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.moneyView);

        String value = a.getString(R.styleable.moneyView_moneyValue);
        // 更新值
        updateMoneyValue(value);

        String unitValue = a.getString(R.styleable.moneyView_unitValue);
        if (TextUtils.isEmpty(unitValue))
            unitValue = mUnitValue;

        int intMoneyColor = a.getColor(R.styleable.moneyView_intTextColor, context.getResources()
                .getColor(R.color.red));
        int intMoneyDimmison = a.getDimensionPixelSize(R.styleable.moneyView_intTextSize,
                context.getResources()
                        .getDimensionPixelSize(R.dimen.large_size));

        int doubleMoneyColor = a.getColor(R.styleable.moneyView_doubleTextColor, context
                .getResources()
                .getColor(R.color.red));
        int doubleMoneyDimmison = a.getDimensionPixelSize(R.styleable.moneyView_doubleTextSize,
                context.getResources()
                        .getDimensionPixelSize(R.dimen.normal_size));

        int unitMoneyColor = a.getColor(R.styleable.moneyView_unitTextColor, context
                .getResources()
                .getColor(R.color.red));
        int unitMoneyDimmison = a.getDimensionPixelSize(R.styleable.moneyView_unitTextSize,
                context.getResources()
                        .getDimensionPixelSize(R.dimen.text_size_small));
        a.recycle();

        // 初始化控件
        mIntMoneyTv = new TextView(context);
        LayoutParams intParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        addView(mIntMoneyTv, intParams);

        mDoubleMoneyTv = new TextView(context);
        LayoutParams doubleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        addView(mDoubleMoneyTv, doubleParams);

        mUnitMoneyTv = new TextView(context);

        LayoutParams unitParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        addView(mUnitMoneyTv, unitParams);

        // 初始化控件数据
        mIntMoneyTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, intMoneyDimmison);

        mDoubleMoneyTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, doubleMoneyDimmison);

        mUnitMoneyTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, unitMoneyDimmison);

        // 初始化文本颜色
        initTextColor(intMoneyColor, doubleMoneyColor, unitMoneyColor);

        initData(mIntValue, mDoubleValue, unitValue);

    }

    /**
     * 初始化文本颜色
     * 
     * @param intMoneyColor
     * @param doubleMoneyColor
     * @param unitMoneyColor
     */
    private void initTextColor(int intMoneyColor, int doubleMoneyColor, int unitMoneyColor) {
        mIntMoneyTv.setTextColor(intMoneyColor);
        mDoubleMoneyTv.setTextColor(doubleMoneyColor);
        mUnitMoneyTv.setTextColor(unitMoneyColor);
    }

    /**
     * 更新值
     * 
     * @param value
     */
    private void updateMoneyValue(String value) {
        if (!TextUtils.isEmpty(value))
        {
            if (value.contains("."))
            {
                String[] strArray = value.split("\\.");
                mIntValue = strArray[0];
                mDoubleValue = strArray[1];
                mDoubleValue = "." + mDoubleValue;
            } else {
                mIntValue = value;
            }

        }
    }

    /**
     * 更新数据
     * 
     * @param intValue
     * @param doubleValue
     * @param unitValue
     */
    private void initData(String intValue, String doubleValue, String unitValue) {
        mIntMoneyTv.setText(intValue);
        mDoubleMoneyTv.setText(doubleValue);
        mUnitMoneyTv.setText(unitValue);
    }

    public TextView getIntTextView()
    {
        return mIntMoneyTv;
    }

    public TextView getDoubleTextView()
    {
        return mDoubleMoneyTv;
    }

    public TextView getUnitTextView()
    {
        return mUnitMoneyTv;
    }

    /**
     * 改变价钱
     * 
     * @param str
     */
    public void setValue(String str)
    {
        if (!TextUtils.isEmpty(str)) {
            updateMoneyValue(str);

            initData(mIntValue, mDoubleValue, mUnitValue);
        }
    }

    public void setMoney(double money) {
        DecimalFormat df=new DecimalFormat("0.00");
        setValue(df.format(money));
    }

    /**
     * 改变单位
     * 
     * @param str
     */
    public void setUnitValue(String unitValue) {
        initData(mIntValue, mDoubleValue, unitValue);
    }

    /**
     * 统一设置文本颜色
     * 
     * @param resId
     */
    public void setTextColor(int resId)
    {
        int color = mContext.getResources().getColor(resId);
        initTextColor(color, color, color);
    }
}
