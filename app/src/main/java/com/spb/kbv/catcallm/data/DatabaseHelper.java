/*
package com.spb.kbv.catcallm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "testcompaniesBase.sqlite";
    private static final int DATABASE_VERSION = 1;

    private Dao<UserDetails, Integer> userDetailsDao = null;
    private Dao<Message, Integer> messagesDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserDetails.class);
            TableUtils.createTable(connectionSource, Message.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    public Dao<UserDetails, Integer> getUserDetailsDao() {
        if (null == userDetailsDao){
            try {
                userDetailsDao = getDao(UserDetails.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Log.d("myLogs", "userDetailsDao = " + (userDetailsDao == null));
        return userDetailsDao;
    }

    public Dao<Message, Integer> getMessagesDao() {
        if (null == messagesDao){
            try {
                messagesDao = getDao(Message.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messagesDao;
    }

}
*/
