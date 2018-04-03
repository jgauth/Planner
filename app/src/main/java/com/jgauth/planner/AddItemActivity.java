package com.jgauth.planner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by john gauthier on 3/31/18.
 */

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";

    private EditText mEditTextName;
    private EditText mEditTextCourse;

    private TextView mTextViewDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView mTextViewTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mEditTextName = (EditText) findViewById(R.id.editText_name);
        mEditTextCourse = (EditText) findViewById(R.id.editText_course);


        final Calendar c = Calendar.getInstance(); // used to get default values for timePicker and datePicker
        mTextViewDate = (TextView) findViewById(R.id.textView_date);
        mTextViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(AddItemActivity.this, mDateSetListener, year, month, day);
                datePicker.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = String.format("%d/%d/%d",month,dayOfMonth,year);
                mTextViewDate.setText(date);
            }
        };

        mTextViewTime = (TextView) findViewById(R.id.textView_time);
        mTextViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(AddItemActivity.this, mTimeSetListener, hourOfDay, minute, DateFormat.is24HourFormat(AddItemActivity.this));
                timePicker.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time= String.format("%d:%d", hourOfDay, minute);
                mTextViewTime.setText(time);
            }
        };
    }

    // Grab user-inputted data, create new HomeworkItem object with it, send it back to MainActivity
    // as an intent extra
    public void sendItem(View view) {
        String name = mEditTextName.getText().toString();
        String course = mEditTextCourse.getText().toString();

        HomeworkItem homeworkItem = new HomeworkItem(name, new Date(), course);

        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_HOMEWORKITEM, homeworkItem);

        setResult(RESULT_OK, intent);
        finish();
    }
}
