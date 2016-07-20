
package com.common.lib.utils;

import android.content.Context;

import com.common.lib.widget.ProgressHud;

/**
 * 加载框工具类
 * 
 * @author liyong
 */
public class LoadingProgressUtils {

    public static ProgressHud create(final Context context, int resId, final String tag) {
        String msg = context.getString(resId);
        return create(context, msg, tag);
    }

    public static ProgressHud create(final Context context, String msg, final String tag) {
        ProgressHud progressHud = ProgressHud.create(context, msg);
        // cancel网络加载对话框时，不必将网络请求取消掉
        // progressHud.setOnCancelListener(new OnCancelListener() {
        //
        // @Override
        // public void onCancel(DialogInterface dialog) {
        // // 停止tag对应的网络请求
        // if (!TextUtils.isEmpty(tag))
        // Volley.getRequestQueue(context).cancelAll(tag);
        //
        // }
        // });
        return progressHud;
    }

}
