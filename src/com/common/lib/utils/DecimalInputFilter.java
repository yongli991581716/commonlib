
package com.common.lib.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 限制小数位数
 * 
 * @author liyong
 */
public class DecimalInputFilter implements InputFilter {

    private static final String DOT = ".";
    private static final String DECIMAL_STR = "0123456789.";

    // private static final String DEFAULT_DECIMAL = "00";

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
            int dend) {
        try {
            if (end != 0) {
                // 首轮过滤非数字和点号的字符串
                for (int i = start; i < end; i++) {
                    if (!DECIMAL_STR.contains(source.toString().subSequence(i, i + 1))) {
                        return "";
                    }
                }

                // 输入数字和点字符串时
                for (int i = start; i < end; i++) {
                    if (source.toString().equals(DOT)) {
                        // 包含小数点号时
                        if (dstart == 0 || dest.toString().contains(DOT)) {
                            // 在首位或者原字符串包含小数点号
                            return "";
                        }
                        // else if (dstart == dest.length()) {
                        // source = source.toString() + DEFAULT_DECIMAL;
                        // }
                    }
                    // else {
                    // if (dest.toString().length() == 0) {
                    // source = source.toString() + DOT + DEFAULT_DECIMAL;
                    // }
                    // }

                    // 将输入后的字符串拼接成完整字符串
                    String value = dest.toString().substring(0, dstart) + source.toString();
                    if (dest.length() != dstart) {
                        value += dest.toString().substring(dstart);
                    }

                    // 比较输入大小不得超过99999999
                    if (Double.valueOf(value) <= 99999999) {
                        int index = value.indexOf(".");
                        // 1、不包含小数时；2、若包含小数，则小数位数不得超过2位
                        if (index == -1 || value.substring(index + 1).length() <= 2) {
                            continue;
                        }

                    }
                    return source.subSequence(start, i);
                }
            } else if (!dest.toString().equals("")) {
                // 删除字符时
                if (dstart == dend) {
                    // 无变化
                    return "";
                }
                // 将删除后的字符串拼接成完整字符串
                String value = dest.toString().substring(0, dstart) + source.toString();
                if (dest.length() != (dstart + 1)) {
                    value += dest.toString().substring(dstart + 1);
                }

                if (!TextUtils.isEmpty(value)) {
                    // 比较输入大小超过99999999,则置为删除前的字符
                    // || value.indexOf(DOT) == 0
                    if (Double.valueOf(value) > 99999999) {
                        return dest.subSequence(dstart, dend);
                    }
                    // else if (value.indexOf(DOT) == value.length() - 1) {
                    // source = DEFAULT_DECIMAL;
                    // }
                }
            }

        } catch (Exception e) {
        }

        return source;
    }

    public static void filter(EditText editText) {

        InputFilter[] filters = editText.getFilters();
        int length = filters.length;
        InputFilter[] inputFilters = new InputFilter[length + 1];

        for (int i = 0; i < length; i++) {
            inputFilters[i] = filters[i];
        }
        inputFilters[length] = new DecimalInputFilter();
        editText.setFilters(inputFilters);
    }
}
