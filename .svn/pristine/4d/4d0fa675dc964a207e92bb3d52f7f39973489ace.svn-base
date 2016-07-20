
package com.common.lib;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class ZhangmaiHandler {

    private static ZhangmaiHandler instance = new ZhangmaiHandler();

    private static Handler sHandler = new Handler() {
        @Override
        public final void handleMessage(Message msg) {
            if (msg.obj instanceof WeakReference) {
                @SuppressWarnings("unchecked")
                WeakReference<MessageHandler> wf = (WeakReference<MessageHandler>) msg.obj;
                MessageHandler msgHandler = wf.get();
                if (msgHandler != null) {
                    msgHandler.handleMessage(msg);
                }
            }
        }
    };

    private ZhangmaiHandler() {

    }

    public static ZhangmaiHandler getInstance() {
        return instance;
    }

    public boolean sendEmptyMessage(Message message, MessageHandler msgHandler) {
        WeakReference<MessageHandler> wf = new WeakReference<MessageHandler>(msgHandler);
        message.obj = wf;
        return sHandler.sendMessage(message);
    }

    public boolean postDelayed(Runnable r, long delayMillis) {
        return sHandler.postDelayed(r, delayMillis);
    }

    public boolean sendEmptyMessage(int what, MessageHandler msgHandler) {
        Message message = Message.obtain();
        message.what = what;
        WeakReference<MessageHandler> wf = new WeakReference<MessageHandler>(msgHandler);
        message.obj = wf;
        return sHandler.sendMessage(message);
    }

    public final boolean sendEmptyMessageDelayed(int what, long delayMillis,
            MessageHandler msgHandler) {
        Message message = Message.obtain();
        message.what = what;
        WeakReference<MessageHandler> wf = new WeakReference<MessageHandler>(msgHandler);
        message.obj = wf;
        return sHandler.sendMessageDelayed(message, delayMillis);
    }

    public void removeMessage(int what) {
        sHandler.removeMessages(what);
    }
}
