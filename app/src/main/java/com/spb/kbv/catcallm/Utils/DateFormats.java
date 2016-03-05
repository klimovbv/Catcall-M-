package com.spb.kbv.catcallm.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateFormats {

    public static String showTimeFormat (Calendar calendar) {
        SimpleDateFormat dateFormat  = new SimpleDateFormat("HH:mm");
        return dateFormat.format(calendar.getTime());
    }

    public static String showTimeAndDayFormat (Calendar calendar) {
        SimpleDateFormat dateFormat  = new SimpleDateFormat("E dd HH:mm");
        return dateFormat.format(calendar.getTime());
    }



}
