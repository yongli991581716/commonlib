
package com.common.lib.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import java.lang.reflect.Method;

public class PopupWindowUtils {

    /**
     * 创建一个宽高为WRAP_CONTENT的视图区间 紧贴anchorView下方显示，点击视图外自动关闭，也可以自己管理关闭popupWindow.dismiss();
     * popupWindow.showAsDropDown(View anchorView); 在parentView的x,y坐标位置显示
     * popupWindow.showAtLocation(View parentView, int gravity, int x, int y);
     * 
     * @param parent
     * @param view
     * @return
     */
    public static PopupWindow createPopupWindow(Context context, View view) {
        return createPopupWindow(context, view, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    /**
     * 创建一个Dialog弹出风格的背景半透明弹出窗口
     * @param context
     * @param view
     * @return
     */
    public static PopupWindow createPopupWindowWithFull(Context context, View view) {
        FrameLayout layout = new FrameLayout(context);
        layout.addView(view, LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        final PopupWindow popupWindow = createPopupWindow(context, layout,
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        return popupWindow;
    }

    /**
     * @param context
     * @param view
     * @param width
     * @param height:参考值LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
     * @return
     */
    public static PopupWindow createPopupWindow(Context context, View view, int width, int height) {
        return createPopupWindow(context, view, width, height, true);
    }

    /**
     * 紧贴anchorView下方显示，点击视图外自动关闭，也可以自己管理关闭popupWindow.dismiss(); popupWindow.showAsDropDown(View
     * anchorView); 在parentView的x,y坐标位置显示 popupWindow.showAtLocation(View parentView, int gravity,
     * int x, int y);
     * 
     * @param parent
     * @param view
     * @param touchModal: true-点击窗口外会下发事件 false-点击窗口外不会下发事件
     * @return
     */
    public static PopupWindow createPopupWindow(Context context, View view, int width, int height,
            boolean touchModal) {
        PopupWindow popupWindow = new PopupWindow(view, width, height);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x60000000));
        setPopupWindowTouchModal(popupWindow, touchModal);

        return popupWindow;
    }

    public static void setPopupWindowTouchModal(PopupWindow popupWindow,
            boolean touchModal) {
        if (null == popupWindow) {
            return;
        }
        try {
            Method method = PopupWindow.class.getDeclaredMethod("setTouchModal",
                    boolean.class);
            method.setAccessible(true);
            method.invoke(popupWindow, touchModal);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
