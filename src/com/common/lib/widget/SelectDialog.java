
package com.common.lib.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.common.lib.R;

public class SelectDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Button btnTopOneButton, btnTopTwoButton, btnButtomButton;
    private LinearLayout contentLayout;
    private SelectDialogListener listener;

    public SelectDialog(Context context, int theme,
            SelectDialogListener listener) {
        super(context, theme);
        this.listener = listener;
    }

    public interface SelectDialogListener {
        public void onClick(View view);
    }

    public void setTopOneText(String number) {
        btnButtomButton.setText(number);
    }

    public void setTopTwoText(String number) {
        btnTopTwoButton.setText(number);
    }

    public LinearLayout getContentLayout() {
        return contentLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_dialog);
        initViews();
    }

    private void initViews() {
        btnButtomButton = (Button) findViewById(R.id.btn_top_one);
        btnTopTwoButton = (Button) findViewById(R.id.btn_top_two);
        btnTopOneButton = (Button) findViewById(R.id.btn_cancel);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
        btnButtomButton.setOnClickListener(this);
        btnTopTwoButton.setOnClickListener(this);
        btnTopOneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public Button getLocalBtn()
    {
        return btnTopTwoButton;
    }
}
