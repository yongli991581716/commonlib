
package com.common.lib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.common.lib.R;
import com.common.lib.utils.StringUtils;
import com.common.lib.widget.NumberPicker.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePicker extends FrameLayout {

    private NumberPicker mYearPicker;
    private NumberPicker mMonthPicker;
    private NumberPicker mDayPicker;
    private OnDateChangedListener mOnDateChangedListener;
    private Calendar mCalendar = Calendar.getInstance();

    private String mMinDate = "1970-01-01";
    private String mMaxDate = "2030-12-31";

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
            int year = mYearPicker.getValue();
            int month = mMonthPicker.getValue();
            int day = mDayPicker.getValue();

            // 比较选择日期和最小可选日期
            if (!TextUtils.isEmpty(mMinDate)) {
                String date = year + "-" + mFormatter.format(month) + "-" + mFormatter.format(day);

                if (date.compareTo(mMinDate) < 0) {
                    if (picker == mYearPicker) {
                        mYearPicker.setValue(oldVal);
                        year = oldVal;
                    } else if (picker == mMonthPicker) {
                        mMonthPicker.setValue(oldVal);
                        month = oldVal;
                    } else if (picker == mDayPicker) {
                        mDayPicker.setValue(oldVal);
                        day = oldVal;
                    }
                }
            }

            // 比较选择日期和最大可选日期
            if (!TextUtils.isEmpty(mMaxDate)) {
                String date = year + "-" + mFormatter.format(month) + "-" + mFormatter.format(day);
                if (date.compareTo(mMaxDate) > 0) {
                    if (picker == mYearPicker) {
                        mYearPicker.setValue(oldVal);
                        year = oldVal;
                    } else if (picker == mMonthPicker) {
                        mMonthPicker.setValue(oldVal);
                        month = oldVal;
                    } else if (picker == mDayPicker) {
                        mDayPicker.setValue(oldVal);
                        day = oldVal;
                    }
                }
            }

            mCalendar.set(Calendar.DAY_OF_MONTH, 1);
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, month - 1);
            int field = Integer.parseInt(picker.getTag().toString());
            if (field != Calendar.DAY_OF_MONTH) {
                int lastDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                mDayPicker.setMaxValue(lastDay);
            }

            mCalendar.set(Calendar.DAY_OF_MONTH, day);
            if (mOnDateChangedListener != null) {
                mOnDateChangedListener.onDateChanged(DatePicker.this, year, month, day);
            }
        }

    };

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_date_picker, this, true);
        mYearPicker = (NumberPicker) findViewById(R.id.year);
        mMonthPicker = (NumberPicker) findViewById(R.id.month);
        mDayPicker = (NumberPicker) findViewById(R.id.day);
        mYearPicker.setTag(Calendar.YEAR);

        mYearPicker.setMinValue(Integer.parseInt(mMinDate.substring(0, 4)));
        mYearPicker.setMaxValue(Integer.parseInt(mMaxDate.substring(0, 4)));
        mYearPicker.setValue(mCalendar.get(Calendar.YEAR));
        mYearPicker.setOnValueChangedListener(mListener);

        mMonthPicker.setTag(Calendar.MONTH);
        mMonthPicker.setMinValue(1);
        mMonthPicker.setMaxValue(12);
        mMonthPicker.setValue(mCalendar.get(Calendar.MONTH) + 1);
        mMonthPicker.setFormatter(mFormatter);
        mMonthPicker.setOnValueChangedListener(mListener);

        mDayPicker.setTag(Calendar.DAY_OF_MONTH);
        mDayPicker.setMinValue(1);
        mDayPicker.setMaxValue(31);
        mDayPicker.setValue(mCalendar.get(Calendar.DAY_OF_MONTH));
        mDayPicker.setFormatter(mFormatter);
        mDayPicker.setOnValueChangedListener(mListener);
    }

    /**
     * @return yyyy-MM-dd 如:2016-03-03
     */
    public String getTime() {
        clearFocus();
        String timeStr = String.format("%s-%s-%s", mYearPicker.getValue(),
                mFormatter.format(mMonthPicker.getValue()),
                mFormatter.format(mDayPicker.getValue()));
        return timeStr;
    }

    /**
     * @param timeStr 支持格式yyyy-MM-dd yyyy-M-d 如:2016-03-03 2016-3-3
     */
    @SuppressLint("SimpleDateFormat")
    public void setTime(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) {
            return;
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeStr);
            mCalendar.setTime(date);
            mYearPicker.setValue(mCalendar.get(Calendar.YEAR));
            mMonthPicker.setValue(mCalendar.get(Calendar.MONTH) + 1);
            mDayPicker.setValue(mCalendar.get(Calendar.DAY_OF_MONTH));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getYear() {
        mYearPicker.clearFocus();
        return mYearPicker.getValue();
    }

    public void setYear(int year) {
        mYearPicker.setValue(year);
    }

    public int getMonth() {
        mMonthPicker.clearFocus();
        return mMonthPicker.getValue();
    }

    public void setMonth(int month) {
        mMonthPicker.setValue(month);
    }

    public int getDay() {
        mDayPicker.clearFocus();
        return mDayPicker.getValue();
    }

    public void setDay(int day) {
        mDayPicker.setValue(day);
    }

    public void setMinDate(String minDate) {
        if (!StringUtils.isEmpty(minDate)) {
            this.mMinDate = minDate;
            mYearPicker.setMinValue(Integer.parseInt(mMinDate.substring(0, 4)));
        }
    }

    public void setMaxDate(String maxDate) {
        if (!StringUtils.isEmpty(maxDate)) {
            this.mMaxDate = maxDate;
            mYearPicker.setMaxValue(Integer.parseInt(mMaxDate.substring(0, 4)));
        }
    }

    public void SetOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        mOnDateChangedListener = onDateChangedListener;
    }

    public static interface OnDateChangedListener {
        public void onDateChanged(DatePicker datePicker, int year, int month, int day);
    }

}
