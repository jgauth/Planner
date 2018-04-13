package com.jgauth.planner;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by john on 4/3/18.
 */

public class TimePickerFragment extends DialogFragment {

    private static final String TAG = "TimePickerFragment";

    private Activity mActivity;
    private TimePickerDialog.OnTimeSetListener mListener;

    private int mHour;
    private int mMinute;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mListener = (TimePickerDialog.OnTimeSetListener) activity;

        Bundle args = getArguments();
        if (args != null) {
            mHour = args.getInt("hour");
            mMinute = args.getInt("minute");
        } else {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(mActivity, mListener,mHour, mMinute, DateFormat.is24HourFormat(mActivity));
    }
}
