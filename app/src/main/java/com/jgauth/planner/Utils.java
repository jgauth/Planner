package com.jgauth.planner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 3/30/18.
 */

public class Utils {

    public static final String DATE_FORMAT = "MM/d";
    public static final String TIME_FORMAT = "h:mm:ss a";

    public static String formatDate(String formatString, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        return simpleDateFormat.format(date);
    }
}