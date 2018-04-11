package com.jgauth.planner.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jgauth.planner.DatePickerFragment;
import com.jgauth.planner.HomeworkItem;
import com.jgauth.planner.R;
import com.jgauth.planner.TimePickerFragment;
import com.jgauth.planner.Utils;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by john gauthier on 3/31/18.
 */

public class AddItemActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = "AddItemActivity";

    private boolean mEdit = false;
    private Intent mIntent;
    private HomeworkItem mHomeworkItem;

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

        mIntent = getIntent();
        Bundle bundle = mIntent.getExtras();
        if (bundle != null && bundle.getInt("requestCode") == MainActivity.EDIT_ITEM_REQUEST) {
            mEdit = true;
            mHomeworkItem = mIntent.getParcelableExtra(MainActivity.EXTRA_HOMEWORKITEM);
            mEditTextName.setText(mHomeworkItem.getItemName());
            mEditTextCourse.setText(mHomeworkItem.getItemCourse());
            mTextViewDate.setText(Utils.formatDate(Utils.DATE_FORMAT, mHomeworkItem.getItemDate()));
            mTextViewTime.setText(Utils.formatDate(Utils.TIME_FORMAT, mHomeworkItem.getItemDate()));
        }
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

        if (mEdit) {
            mHomeworkItem.setItemName(name);
            mHomeworkItem.setItemCourse(course);
            mHomeworkItem.setItemDate(date);

            Log.i(TAG, "sendItem: "+mHomeworkItem.getItemName());
            Log.i(TAG, "sendItem: "+mHomeworkItem.getId());
        } else {
            mHomeworkItem = new HomeworkItem(name, date, course);
            Log.i(TAG, "sendItem: "+mHomeworkItem.getHeaderId());
        }

        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_HOMEWORKITEM, mHomeworkItem);

        setResult(RESULT_OK, intent);
        finish();
    }
}
