
package com.common.lib.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.common.lib.R;
import com.common.lib.widget.DatePicker;

/**
 * @author qinyu 传入参数 date:yyyy-MM-dd 返回参数也是一样格式 date:yyyy-MM-dd
 */
public class BottomDatePickerActivity extends Activity {

    public static final String MIN_DATE_PARAMS_NAME = "min_date";
    public static final String MAX_DATE_PARAMS_NAME = "max_date";
    private View mRootView;
    private TextView mCancelTextView;
    private TextView mFirmTextView;
    private DatePicker mDatePicker;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = LayoutInflater.from(this).inflate(R.layout.activity_bottom_date_picker, null);
        setContentView(mRootView);

        mDatePicker = (DatePicker) mRootView.findViewById(R.id.date_picker);
        mCancelTextView = (TextView) mRootView.findViewById(R.id.tv_picker_cancel);
        mFirmTextView = (TextView) mRootView.findViewById(R.id.tv_picker_confirm);

        mDatePicker.setMinDate(getIntent().getStringExtra(MIN_DATE_PARAMS_NAME));
        mDatePicker.setMaxDate(getIntent().getStringExtra(MAX_DATE_PARAMS_NAME));
        mDatePicker.setTime(getIntent().getStringExtra("date"));

        rigsterListener();
    }

    private void rigsterListener() {
        mRootView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });
        mCancelTextView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });
        mFirmTextView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String date = mDatePicker.getTime();
                Intent intent = getIntent();
                intent.putExtra("date", date);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        });
    }
}
