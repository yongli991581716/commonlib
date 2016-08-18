
package com.common.lib.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * 密码输入过滤器，过略掉除字母、数字、_以外的所有字符
 * 
 * @author qinyu
 */

public class BasicTextInputFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
            int dend) {
        for (int i = start; i < end; i++) {
            char c = source.charAt(i);
            // 一般中文 4e00-9fa5
            // CJK标点符号 3000-303F
            // 中文竖排标点 FE10-FE1F
            // CJK兼容符号（竖排变体、下划线、顿号）FE30-FE4F
            // 全角ASCII、全角中英文标点、半宽片假名、半宽平假名、半宽韩文字母：FF00-FFEF
            if (c >= 0x4e00 && c <= 0x9fa5 ||
                    c >= 0x3000 && c <= 0x303f ||
                    c >= 0xff00 && c <= 0xffef ||
                    c >= 0 && c <= 127) {
                continue;
            }
            return source.subSequence(start, i);
        }
        return source;
    }

    /**
     * 对editText添加输入过滤器，过略掉除字母、数字、_以外的所有字符
     * 
     * @param editText
     */
    public static void filter(EditText editText) {
        InputFilter[] filters = editText.getFilters();
        int length = filters.length;
        InputFilter[] inputFilters = new InputFilter[length + 1];
        for (int i = 0; i < length; i++) {
            inputFilters[i] = filters[i];
        }
        inputFilters[length] = new BasicTextInputFilter();
        editText.setFilters(inputFilters);
    }

}
