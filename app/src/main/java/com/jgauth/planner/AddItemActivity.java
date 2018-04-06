package com.jgauth.planner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by john gauthier on 3/31/18.
 */

public class AddItemActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = "AddItemActivity";

    private EditText mEditTextName;
    private EditText mEditTextCourse;

    private TextView mTextViewDate;
    private TextView mTextViewTime;

    private int mYear;
    private int mMonth;
    private int mDayOfMonth;
    private int mHourOfDay;
    private int mMinute;

    private View.OnClickListener dateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showDatePickerDialog();
        }
    };

    private View.OnClickListener timeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showTimePickerDialog(view);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mEditTextName = (EditText) findViewById(R.id.editText_name);
        mEditTextCourse = (EditText) findViewById(R.id.editText_course);

        mTextViewDate = (TextView) findViewById(R.id.textView_date);
        mTextViewDate.setOnClickListener(dateOnClickListener);

        mTextViewTime = (TextView) findViewById(R.id.textView_time);
        mTextViewTime.setOnClickListener(timeOnClickListener);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        mHourOfDay = hourOfDay;
        mMinute = minute;
        mTextViewTime.setText(String.format("%d:%d",hourOfDay, minute));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        mYear = year;
        mMonth = month;
        mDayOfMonth = dayOfMonth;
        mTextViewDate.setText(String.format("%d/%d/%d", year, month, dayOfMonth));
    }

    public void showTimePickerDialog(View view) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    // Called when button pressed
    // Grab user-inputted data, create new HomeworkItem object with it, send it back to MainActivity
    // as an intent extra
    public void sendItem(View view) {
        String name = mEditTextName.getText().toString();
        String course = mEditTextCourse.getText().toString();

        GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDayOfMonth, mHourOfDay, mMinute);
        Date date = calendar.getTime();

        HomeworkItem homeworkItem = new HomeworkItem(name, date, course);

        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_HOMEWORKITEM, homeworkItem);

        setResult(RESULT_OK, intent);
        finish();
    }
}
