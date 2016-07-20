
package com.common.lib.utils;

import android.util.Log;

import java.util.Locale;

/**
 * 一个较好的开发实践是，在程序的Application的onCreate()里
 * 调用CommonLog.setTag(TAG)，将应用级的TAG设置进入Log系统。
 * @author qinyu
 *
 */
public class CommonLog {

    private static String TAG = "CommonLib";
    /**
     * VERBOSE:2 DEBUG:3 INFO:4 WARN:5 ERROR:6 adb shell setprop log.tag.<Tag> "value" eg: adb shell
     * setprop log.tag.logistics VERBOSE
     */
    public static int LOG_LEVEL;

    /**
     * 设置全局的log tag，对应上Log.x(LogTag, str)
     * 
     * @param tag
     */
    public static void setTag(String tag) {
        TAG = tag;
        init();
    }

    private static void init() {
        boolean isDebug = false;
        int logLevel = Log.ERROR;
        for (int level = Log.VERBOSE; level <= Log.ERROR; level++) {
            isDebug = Log.isLoggable(TAG, level);
            if (isDebug) {
                logLevel = level;
                break;
            }
        }
        LOG_LEVEL = logLevel;
    }

    public static void d(String format, Object... args) {
        if (LOG_LEVEL <= Log.DEBUG) {
            Log.d(TAG, buildMessage(format, args));
        }
    }

    public static void i(String format, Object... args) {
        if (LOG_LEVEL <= Log.INFO) {
            Log.i(TAG, buildMessage(format, args));
        }
    }

    public static void w(String format, Object... args) {
        if (LOG_LEVEL <= Log.WARN) {
            Log.w(TAG, buildMessage(format, args));
        }
    }

    public static void e(String format, Object... args) {
        if (LOG_LEVEL <= Log.ERROR) {
            Log.e(TAG, buildMessage(format, args));
        }
    }

    private static String buildMessage(String format, Object... args) {
        String msg = (args == null || args.length == 0) ? format : String.format(Locale.CHINA,
                format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();

        StringBuilder info = new StringBuilder();
        if (trace.length >= 3) {
            String callingClass = trace[2].getClassName();
            info.append(callingClass.substring(callingClass.lastIndexOf('.') + 1));
            info.append('.');
            info.append(trace[2].getMethodName());
            info.append(':');
            info.append(trace[2].getLineNumber());
        }
        info.append(": ");
        info.append(msg);

        return info.toString();
    }
}
