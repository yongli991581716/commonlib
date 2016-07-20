/**
 * 
 */

package com.common.lib.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * <b>线程工具类</b><br>
 * 主要用于判断当前线程是否为UI线程
 * 
 * @author luchonghui
 */
public final class ThreadUtils {

    /**
     * 判断线程是否为主线程
     * 
     * @param curThreadId
     * @return
     */
    public static final boolean isUIThread(long curThreadId)
    {
        return curThreadId == Looper.getMainLooper().getThread().getId();
    }

    public static final void runOnUIThread(Handler handler, Runnable action)
    {
        if (isUIThread(Thread.currentThread().getId()))
        {
            action.run();
        } else
        {
            handler.post(action);
        }
    }

}
