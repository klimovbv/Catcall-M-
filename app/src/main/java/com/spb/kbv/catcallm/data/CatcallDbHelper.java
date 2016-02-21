package com.spb.kbv.catcallm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.spb.kbv.catcallm.data.MessagesContract.MessagesEntry;
import com.spb.kbv.catcallm.data.MessagesContract.CompaniesEntry;

public class CatcallDbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "catcall.db";

    public CatcallDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MESSAGES_TABLE = "CREATE TABLE " + MessagesEntry.TABLE_NAME + " (" +
                MessagesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MessagesEntry.COLUMN_COMP_KEY + " INTEGER NOT NULL, " +
                MessagesEntry.COLUMN_DATE + " INTEGER NOT NULL, " +
                /*MessagesEntry.COLUMN_COMPANY + " TEXT NOT NULL, " +*/
                MessagesEntry.COLUMN_ISFROMUS + " TEXT NOT NULL, " +
                MessagesEntry.COLUMN_TEXT + " TEXT NOT NULL, " +
                MessagesEntry.COLUMN_FILE + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + MessagesEntry.COLUMN_COMP_KEY + ") REFERENCES " +
                CompaniesEntry.TABLE_NAME + " (" + CompaniesEntry._ID + ")" +
                ");";

        final String SQL_CREATE_COMPANIES_TABLE = "CREATE TABLE " + CompaniesEntry.TABLE_NAME + " (" +
                CompaniesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CompaniesEntry.COLUMN_AVATAR + " TEXT NOT NULL, " +
                CompaniesEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                CompaniesEntry.COLUMN_LATITUDE + " REAL NOT NULL, " +
                CompaniesEntry.COLUMN_LONGITUDE + " REAL NOT NULL" +
                ");";

        db.execSQL(SQL_CREATE_MESSAGES_TABLE);
        db.execSQL(SQL_CREATE_COMPANIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + MessagesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST " + CompaniesEntry.TABLE_NAME);
    }
}
