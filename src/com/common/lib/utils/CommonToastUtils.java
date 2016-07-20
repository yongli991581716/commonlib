
package com.common.lib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.common.lib.R;

/**
 * @ClassName: ToastUtil
 * @Description:带声音的提示框
 * @author: liyong
 * @date: 2013-8-27 上午11:11:32
 */
public class CommonToastUtils extends Toast {

    private MediaPlayer mPlayer;
    private boolean isSound;
    private static Toast toast;
    private static Context mContext;

    public CommonToastUtils(Context context, int id) {

        this(context, id, false);
        mContext = context;
    }

    public CommonToastUtils(Context context, int id, boolean isSound) {
        super(context);

        this.isSound = isSound;

        mPlayer = MediaPlayer.create(context, id);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mp != null) {
                    mp.release();
                }
            }
        });

    }

    /**
     * @Title: show
     * @Description:显示提示框（声音作处理）
     * @author : liyong
     * @see android.widget.Toast#show()
     */
    @Override
    public void show() {
        super.show();

        if (isSound) {
            mPlayer.start();
        }
    }

    /**
     * 设置是否播放声音
     */
    public void setIsSound(boolean isSound) {
        this.isSound = isSound;
    }

    /**
     * 获取控件实例
     * 
     * @param context
     * @param text 提示消息
     * @param isSound 是否播放声音
     * @return
     */
    @SuppressLint("InflateParams")
    public static CommonToastUtils makeTextWithSound(Context context,
            CharSequence text, int resId, boolean isSound, int location) {

        resId = (resId == 0 ? R.raw.newdatatoast : resId);
        CommonToastUtils result = new CommonToastUtils(context, resId, isSound);

        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        View v = inflate.inflate(R.layout.common_toast, null);
        v.setMinimumWidth(dm.widthPixels);// 设置控件最小宽度为手机屏幕宽度
        TextView tv = (TextView) v.findViewById(R.id.tv_common_toast);
        tv.getBackground().setAlpha(100);
        tv.setText(text);

        result.setView(v);
        result.setDuration(Toast.LENGTH_LONG);
        result.setGravity(location, 0, 0);
        return result;
    }

    /**
     * @Title: makeText
     * @Description: 文本提示 （字符串传输）
     * @author:liyong
     * @param: @param text
     * @param: @param location
     * @return: void
     * @throws
     */
    @SuppressLint("ShowToast")
    public static Toast makeText(Context context, CharSequence text,
            int location) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(location, 0, 0);
        return toast;
    }

    /**
     * @Title: makeText
     * @Description: 文本提示（资源id传输）
     * @author:liyong
     * @param: @param textId
     * @param: @param location
     * @return: void
     * @throws
     */
    @SuppressLint("ShowToast")
    public static Toast makeText(Context context, int textId, int location) {
        Toast toast = Toast.makeText(context, textId, 600);
        toast.setGravity(location, 0, 0);
        return toast;
    }

    /**
     * @Title: showCenterText
     * @Description: 在页面中间显示提示信息
     * @author:lixingjiang
     * @param: @param textId
     * @return: void
     * @throws
     */
    public static void showCenterText(int textId) {
        if (toast == null) {
            toast = Toast.makeText(mContext, textId,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(textId);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @Title: showCenterText
     * @Description: 在页面中间显示提示信息
     * @author:lixingjiang
     * @param: msg
     * @return: void
     * @throws
     */
    public static void showCenterText(String msg) {
        if (toast == null) {
            toast = Toast.makeText(mContext, msg,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @Title: showBottomText
     * @Description: 在页面底部显示提示信息
     * @author:liyong
     * @param: @param msg
     * @return: void
     * @throws
     */
    public static void showBottomText(String msg) {
        if (toast == null) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    /**
     * @Title: showBottomText
     * @Description: 在页面底部显示提示信息
     * @author:lixingjiang
     * @param: @param textId
     * @return: void
     * @throws
     */
    public static void showBottomText(int textId) {
        if (toast == null) {
            toast = Toast.makeText(mContext, textId,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(textId);
        }
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
