package com.spb.kbv.catcallm.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

public class MessagesContract {
    public static final String CONTENT_AUTHORITY = "com.spb.kbv.catcallm";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MESSAGE = "message";
    public static final String PATH_COMPANY = "company";

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }
    public static final class MessagesEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MESSAGE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MESSAGE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MESSAGE;

        public static final String TABLE_NAME = "message";

        public static final String COLUMN_COMP_KEY = "comp_id";
        public static final String COLUMN_DATE = "date";
        /*public static final String COLUMN_COMPANY = "company";*/
        public static final String COLUMN_ISFROMUS = "isfromus";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_FILE = "file";

        public static Uri buildMessageUri (long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class CompaniesEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMPANY).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMPANY;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMPANY;

        public static final String TABLE_NAME = "company";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_AVATAR = "avatar";

        public static Uri buildCompanyUri (long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
