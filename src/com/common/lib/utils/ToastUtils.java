
package com.common.lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;
    private static Context context;

    private static Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.obj != null) {
                show(msg.obj.toString());
            }
        };
    };

    public static Toast getToast() {
        return toast;
    }

    @SuppressLint("ShowToast")
    public static void init(Context context) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_LONG);
            ToastUtils.context = context;
        }
    }

    public static void sendMessage(int stringId) {
        sendMessage(context.getString(stringId));
    }

    public static void sendMessage(String msg) {
        Message message = Message.obtain(mHandler);
        message.obj = msg;
        mHandler.sendMessage(message);
    }

    public static void show(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            show(msg, Toast.LENGTH_LONG);
        }
    }

    public static void show(String msg, int duration) {
        toast.setDuration(duration);
        toast.setText(msg);
        toast.show();
    }

    public static void show(int stringId) {
        show(context.getString(stringId));
    }

    public static void show(int stringId, String msg) {
        show(context.getString(stringId, msg));
    }

    public static void show(int stringId, int duration) {
        show(context.getString(stringId), duration);
    }

    public static void show(int stringId, String msg, int duration) {
        show(context.getString(stringId, msg), duration);
    }
}
