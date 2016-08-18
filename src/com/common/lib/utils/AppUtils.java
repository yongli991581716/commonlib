
package com.common.lib.utils;

import android.app.Activity;

import com.common.lib.R;

/**
 * 应用级别工具
 * 
 * @author liyong
 */
public class AppUtils {
    /**
     * 底部开启activity
     * 
     * @param activity
     */
    public static void bottomEnterAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.push_bottom_in, 0);
    }

    /**
     * 底部退出activity
     * 
     * @param activity
     */
    public static void bottomExitAnim(Activity activity) {
        activity.overridePendingTransition(0, R.anim.push_bottom_out);
    }
}
