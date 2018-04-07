package com.jgauth.planner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by john on 3/30/18.
 */

public class Utils {

    public static final String DATE_FORMAT = "MM/d";
    public static final String TIME_FORMAT = "h:mm:ss a";
    public static final int DUE_PAST = 1;
    public static final int DUE_TODAY = 2;
    public static final int DUE_TOMORROW = 3;
    public static final int DUE_THIS_WEEK = 4;
    public static final int DUE_NEXT_WEEK = 5;
    public static final int NEEDS_IMPLEMENTATION = 6;

    public static String formatDate(String formatString, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        return simpleDateFormat.format(date);
    }

    public static int getHeaderIdFromDate(Date date) {
        Calendar currentTime = Calendar.getInstance();
        Calendar dueTime = Calendar.getInstance();
        dueTime.setTime(date);
        int currentYear = currentTime.get(Calendar.YEAR);
        int currentDayOfYear = currentTime.get(Calendar.DAY_OF_YEAR);
        int dueYear = dueTime.get(Calendar.YEAR);
        int dueDayOfYear = dueTime.get(Calendar.DAY_OF_YEAR);
        boolean sameYear = currentYear == dueYear;


        if (currentTime.compareTo(dueTime) > 0)
            return DUE_PAST;
        else if (sameYear && currentDayOfYear == dueDayOfYear)
            return DUE_TODAY;
        else if (sameYear && currentDayOfYear == dueDayOfYear-1)
            return DUE_TOMORROW;
        else if (sameYear && (currentDayOfYear + 7 - dueDayOfYear > 0))
            return DUE_THIS_WEEK;
        else if (sameYear && (currentDayOfYear + 14 - dueDayOfYear > 0))
            return DUE_NEXT_WEEK;
        else
            return NEEDS_IMPLEMENTATION; //FINISH THIS
    }
}