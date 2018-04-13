package com.jgauth.planner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by john on 4/3/18.
 */

public class DatePickerFragment extends DialogFragment {

    private Activity mActivity;
    private DatePickerDialog.OnDateSetListener mListener;

    private int mYear;
    private int mMonth;
    private int mDayOfMonth;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mListener = (DatePickerDialog.OnDateSetListener) activity;

        Bundle args = getArguments();
        if (args != null) {
            mYear = args.getInt("year");
            mMonth = args.getInt("month");
            mDayOfMonth = args.getInt("day");
        } else {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new DatePickerDialog(mActivity, mListener, mYear, mMonth, mDayOfMonth);
    }
}
