
package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.lib.R;

/**
 * <b>自定义弹窗</b><br>
 * 
 * @author luchonghui
 */
public class ZmAlertDialog extends Dialog implements DialogInterface
{

    @Override
    public void show()
    {
        super.show();
    }

    public ZmAlertDialog(Context context, int theme)
    {
        super(context, theme);
    }

    public ZmAlertDialog(Context context)
    {
        super(context);
    }

    /**
     * Gets one of the buttons used in the dialog.
     * <p>
     * If a button does not exist in the dialog, null will be returned.
     * 
     * @param whichButton The identifier of the button that should be returned. For example, this
     *            can be {@link DialogInterface#BUTTON_POSITIVE}.
     * @return The button from the dialog, or null if a button does not exist.
     */
    public Button getButton(int whichButton)
    {
        Button resultButton = null;
        switch (whichButton)
        {
            case DialogInterface.BUTTON_POSITIVE: {
                resultButton = (Button) this.findViewById(R.id.positiveButton);
            }
                break;
            case DialogInterface.BUTTON_NEGATIVE: {
                resultButton = (Button) this.findViewById(R.id.negativeButton);
            }
                break;
            case DialogInterface.BUTTON_NEUTRAL: {
            }

                break;
            default:
                break;
        }

        return resultButton;
    }

    public void setMessage(int resource)
    {
        setMessage(getContext().getString(resource));
    }

    public void setMessage(CharSequence message)
    {
        ((TextView) this.findViewById(R.id.message)).setText(message);
    }

    /**
     * Helper class for creating a custom dialog
     */
    @SuppressLint("InflateParams")
    public static class Builder
    {

        private Context context;

        private String title;

        private String message;

        private String positiveButtonText;

        private String negativeButtonText;

        private View contentView;

        @SuppressWarnings("unused")
        private DialogInterface.OnClickListener onItemClickListener;

        private DialogInterface.OnClickListener positiveButtonClickListener,
                negativeButtonClickListener;

        /**
         * 点击任何按钮，是否自动关闭，默认True
         */
        private boolean isAutoDismiss = true;

        public Builder(Context context)
        {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         * 
         * @param title
         * @return
         */
        public Builder setMessage(String message)
        {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         * 
         * @param title
         * @return
         */
        public Builder setMessage(int message)
        {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         * 
         * @param title
         * @return
         */
        public Builder setTitle(int title)
        {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         * 
         * @param title
         * @return
         */
        public Builder setTitle(String title)
        {
            this.title = title;
            return this;
        }

        /**
         * Set a custom content view for the Dialog. If a message is set, the contentView is not
         * added to the Dialog...
         * 
         * @param v
         * @return
         */
        public Builder setContentView(View v)
        {
            this.contentView = v;
            return this;
        }

        public boolean isAutoDismiss()
        {
            return isAutoDismiss;
        }

        public Builder setAutoDismiss(boolean isAutoDismiss)
        {
            this.isAutoDismiss = isAutoDismiss;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         * 
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                DialogInterface.OnClickListener listener)
        {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         * 
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                DialogInterface.OnClickListener listener)
        {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         * 
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                DialogInterface.OnClickListener listener)
        {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         * 
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                DialogInterface.OnClickListener listener)
        {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items,
                OnClickListener onClickListener)
        {
            this.onItemClickListener = onClickListener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        @SuppressWarnings("deprecation")
        public ZmAlertDialog create()
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final ZmAlertDialog dialog = new ZmAlertDialog(context,
                    R.style.dialog_basic_styles);
            View layout = inflater.inflate(R.layout.zm_dialog, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            try
            {

                DisplayMetrics d = context.getResources().getDisplayMetrics();
                WindowManager.LayoutParams params = dialog.getWindow()
                        .getAttributes();
                // 获取对话框当前的参数值
                params.x = 0; // 设置位置 默认为居中
                params.y = 0; // 设置位置 默认为居中

                // 设置dialog的宽度
                params.width = (int) (d.widthPixels * 0.9);

                dialog.getWindow().setAttributes(params);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            if (null == title)
            {
                layout.findViewById(R.id.titleLayout).setVisibility(View.GONE);
            }
            else
            {
                ((TextView) layout.findViewById(R.id.title)).setText(title);

                layout.findViewById(R.id.titleLayout).setVisibility(
                        View.VISIBLE);
            }

            // set the confirm button
            if (positiveButtonText != null)
            {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);

                if (negativeButtonText == null)
                {
                    Button button = (Button) layout
                            .findViewById(R.id.positiveButton);
                    button.setPadding(0, button.getPaddingTop(), 0,
                            button.getPaddingBottom());
                }

                if (positiveButtonClickListener != null)
                {
                    ((Button) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener()
                            {
                                public void onClick(View v)
                                {
                                    if (isAutoDismiss && null != dialog
                                            && dialog.isShowing())
                                    {
                                        dialog.dismiss();
                                    }
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            }
            else
            {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null)
            {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);

                if (positiveButtonText == null)
                {
                    Button button = (Button) layout
                            .findViewById(R.id.negativeButton);
                    button.setPadding(0, button.getPaddingTop(), 0,
                            button.getPaddingBottom());
                }

                if (negativeButtonClickListener != null)
                {
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener()
                            {
                                public void onClick(View v)
                                {

                                    if (isAutoDismiss && null != dialog
                                            && dialog.isShowing())
                                    {
                                        dialog.dismiss();
                                    }
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            }
            else
            {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            if (message != null)
            {
                ((TextView) layout.findViewById(R.id.message))
                        .setText(message);
            }
            else if (contentView != null)
            {
                // if no message set
                // add the contentView to the dialog body
                ((RelativeLayout) layout.findViewById(R.id.content))
                        .removeAllViews();
                ((RelativeLayout) layout.findViewById(R.id.content))
                        .addView(contentView, new LayoutParams(
                                LayoutParams.FILL_PARENT,
                                LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);
            // 默认不允许点击其他地方取消dialog
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }

}
