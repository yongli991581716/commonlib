
package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.common.lib.R;
import com.common.lib.widget.NumberPicker.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePicker extends FrameLayout {

    private NumberPicker mHourPicker;
    private NumberPicker mMinutePicker;
    private OnTimeChangedListener mOnTimeChangedListener;

    private Formatter mFormatter = new Formatter() {

        @Override
        public String format(int value) {
            String str;
            if (value < 10) {
                str = "0" + value;
            } else {
                str = "" + value;
            }
            return str;
        }

    };

    private NumberPicker.OnValueChangeListener mListener = new NumberPicker.OnValueChangeListener() {

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            if (mOnTimeChangedListener != null) {
                mOnTimeChangedListener.onTimeChanged(TimePicker.this, mHourPicker.getValue(),
                        mMinutePicker.getValue());
            }
        }

    };

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_time_picker, this, true);
        mHourPicker = (NumberPicker) findViewById(R.id.hour);
        mMinutePicker = (NumberPicker) findViewById(R.id.minute);

        mHourPicker.setMinValue(0);
        mHourPicker.setMaxValue(23);
        mHourPicker.setFormatter(mFormatter);
        mHourPicker.setOnValueChangedListener(mListener);

        mMinutePicker.setMinValue(0);
        mMinutePicker.setMaxValue(59);
        mMinutePicker.setFormatter(mFormatter);
        mMinutePicker.setOnValueChangedListener(mListener);
    }

    public String getTime() {
        clearFocus();
        String timeStr = mFormatter.format(mHourPicker.getValue()) + ":"
                + mFormatter.format(mMinutePicker.getValue());
        return timeStr;
    }

    /**
     * @param timeStr 支持格式HH:mm 如:9:5 09:05 9:05 09:5
     */
    @SuppressLint("SimpleDateFormat")
    public void setTime(String timeStr) {
        if(TextUtils.isEmpty(timeStr)) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = new SimpleDateFormat("HH:mm").parse(timeStr);
            calendar.setTime(date);
            setHourOfDay(calendar.get(Calendar.HOUR_OF_DAY));
            setMinute(calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setHourOfDay(int hourOfDay) {
        int hour = hourOfDay % 24;
        mHourPicker.setValue(hour);
    }

    public int getHourOfDay() {
        mHourPicker.clearFocus();
        return mHourPicker.getValue();
    }

    public void setMinute(int minute) {
        int m = minute % 60;
        mMinutePicker.setValue(m);
    }

    public int getMinute() {
        mMinutePicker.clearFocus();
        return mMinutePicker.getValue();
    }

    public void SetOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        mOnTimeChangedListener = onTimeChangedListener;
    }

    public static interface OnTimeChangedListener {
        public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute);
    }

}
