
package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.lib.R;

public class ProgressHud extends Dialog {

    public ProgressHud(Context context) {
        super(context);
    }

    public ProgressHud(Context context, int theme) {
        super(context, theme);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.tv_loading_dialog_message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.tv_loading_dialog_message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    public static ProgressHud create(Context context, CharSequence message) {
        return create(context, message, true, null);
    }

    public static ProgressHud create(Context context, CharSequence message, boolean cancelable,
            OnCancelListener cancelListener) {
        return setAttr(context, message, cancelable, cancelListener);
    }

    public static ProgressHud show(Context context, CharSequence message) {
        return show(context, message, true, null);
    }

    public static ProgressHud show(Context context, CharSequence message, boolean cancelable,
            OnCancelListener cancelListener) {
        ProgressHud progressHud = setAttr(context, message, cancelable, cancelListener);
        progressHud.show();
        return progressHud;
    }

    @SuppressLint("InflateParams")
    private static ProgressHud setAttr(Context context, CharSequence message, boolean cancelable,
            OnCancelListener cancelListener) {
        ProgressHud progressHUD = new ProgressHud(context, R.style.dialog_basic_styles);
        progressHUD.setTitle("");
        progressHUD.setContentView(R.layout.progress_hud);
        if (message == null || message.length() == 0) {
            progressHUD.findViewById(R.id.tv_loading_dialog_message).setVisibility(View.GONE);
        } else {
            TextView txt = (TextView) progressHUD.findViewById(R.id.tv_loading_dialog_message);
            txt.setText(message);
        }
        if (cancelListener != null)
            progressHUD.setOnCancelListener(cancelListener);
        progressHUD.setCancelable(cancelable);
        progressHUD.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = progressHUD.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        progressHUD.getWindow().setAttributes(lp);
        // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        return progressHUD;
    }
}
