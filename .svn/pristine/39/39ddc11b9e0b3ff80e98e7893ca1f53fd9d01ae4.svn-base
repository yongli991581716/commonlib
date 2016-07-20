/**
 * 
 */

package com.common.lib.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author luchonghui
 */
public class TextPriceWatch {
    public static TextWatcher AddWatcher(final EditText mEditText) {
        return new TextWatcher() {

            private boolean isChanged = false;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (isChanged) {// ----->如果字符未改变则返回
                    return;
                }
                String str = s.toString();

                isChanged = true;
                String cuttedStr = str;
                /* 删除字符串中的dot */
                for (int i = str.length() - 1; i >= 0; i--) {
                    char c = str.charAt(i);
                    if ('.' == c) {
                        cuttedStr = str.substring(0, i) + str.substring(i + 1);
                        break;
                    }
                }
                /* 删除前面多余的0 */
                int NUM = cuttedStr.length();
                int zeroIndex = -1;
                for (int i = 0; i < NUM - 2; i++) {
                    char c = cuttedStr.charAt(i);
                    if (c != '0') {
                        zeroIndex = i;
                        break;
                    } else if (i == NUM - 3) {
                        zeroIndex = i;
                        break;
                    }
                }
                if (zeroIndex != -1) {
                    cuttedStr = cuttedStr.substring(zeroIndex);
                }
                /* 不足3位补0 */
                if (cuttedStr.length() < 3) {
                    cuttedStr = "0" + cuttedStr;
                }
                /* 加上dot，以显示小数点后两位 */
                cuttedStr = cuttedStr.substring(0, cuttedStr.length() - 2)
                        + "." + cuttedStr.substring(cuttedStr.length() - 2);

                mEditText.setText(cuttedStr);

                mEditText.setSelection(mEditText.length());
                isChanged = false;
            }
        };
    }
}
