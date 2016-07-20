
package com.common.lib.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.lib.R;

/**
 * 共用对话框
 * 
 * @author liyong
 */
public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    public static CommonDialog show(Context context, CharSequence message) {

        return show(context, message, true);
    }

    public static CommonDialog show(Context context, CharSequence message, boolean cancelable) {

        return show(context, message, cancelable, null);
    }

    public static CommonDialog show(Context context, CharSequence message, boolean cancelable,
            OnCancelListener cancelListener) {

        return setAttr(context, message, cancelable, cancelListener);
    }

    private static CommonDialog setAttr(Context context, CharSequence message, boolean cancelable,
            OnCancelListener cancelListener) {
        final CommonDialog dialog = new CommonDialog(context, R.style.dialog_basic_styles);
        dialog.setTitle("");
        dialog.setContentView(R.layout.common_dialog);

        // 初始化

        TextView mTextView = (TextView) dialog.findViewById(R.id.tv_dialog_mes);
        mTextView.setText(message);

        Button mGiveUPBtn = (Button) dialog.findViewById(R.id.btn_dialog_give_up);
        mGiveUPBtn.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Button mConfirmBtn = (Button) dialog.findViewById(R.id.btn_dialog_firm);
        mConfirmBtn.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (cancelListener != null)
            dialog.setOnCancelListener(cancelListener);

        dialog.setCancelable(cancelable);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.show();
        return dialog;
    }

    /**
     * 获取文本控件
     * 
     * @return
     */
    public TextView getTextView()
    {
        return (TextView) this.findViewById(R.id.tv_dialog_mes);
    }

    /**
     * 获取图片控件
     * 
     * @return
     */
    public ImageView getImageView()
    {
        return (ImageView) this.findViewById(R.id.iv_dialog_icon);

    }

    /**
     * 获取放弃按钮控件
     * 
     * @return
     */
    public Button getGiveUpButton()
    {
        return (Button) this.findViewById(R.id.btn_dialog_give_up);
    }

    /**
     * 获取确定按钮控件
     * 
     * @return
     */
    public Button getFirmButton()
    {
        return (Button) this.findViewById(R.id.btn_dialog_firm);
    }
}
