
package com.common.lib.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * 密码输入过滤器，过略掉除字母、数字、_以外的所有字符
 * 
 * @author qinyu
 */

public class PasswordInputFilter implements InputFilter {

    private PasswordInputFilter() {
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
            int dend) {
        for (int i = start; i < end; i++) {
            char c = source.charAt(i);
            if (c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'
                    || c == '_') {
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
        inputFilters[length] = new PasswordInputFilter();
        editText.setFilters(inputFilters);
    }
}
