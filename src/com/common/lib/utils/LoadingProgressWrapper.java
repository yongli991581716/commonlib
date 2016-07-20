
package com.common.lib.utils;

import android.content.Context;

import com.android.volley.Request;
import com.common.lib.widget.ProgressHud;

public class LoadingProgressWrapper {

    /**
     * 为网络请求对象添加请求对话框
     * 
     * @param context Activity的context对象，application的context会报错
     * @param request
     * @param TAG
     * @param resId 显示在网络请求对话框里的消息对应的资源Id
     */
    public static void wrapRequest(Context context, Request<?> request, String TAG, int resId) {
        wrapRequest(context, request, TAG, context.getString(resId));
    }

    /**
     * 为网络请求对象添加请求对话框
     * 
     * @param context Activity的context对象，application的context会报错
     * @param request
     * @param TAG
     * @param msg 显示在网络请求对话框里的消息
     */
    public static void wrapRequest(Context context, Request<?> request, String TAG, String msg) {
        request.setTag(TAG);
        ProgressHud progressHud = LoadingProgressUtils.create(context, msg, TAG);
        request.setProgressDialog(progressHud);
    }
}
