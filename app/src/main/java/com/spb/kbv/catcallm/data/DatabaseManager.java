/*
package com.spb.kbv.catcallm.data;

import android.content.Context;
import android.util.Log;

import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;

import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {
    private static DatabaseManager instance;

    public static void init (Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper helper;
    private DatabaseManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    public List<UserDetails> getAllUsers() {
        Log.d("myLogs", "in getAllUsers");
        List<UserDetails> users = null;
        try {
            users = getHelper().getUserDetailsDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Message> getMessages() {
        Log.d("myLogs", "inGetMessages ");
        List<Message> messages = null;
        try {
            messages = getHelper().getMessagesDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }


    public void addUser(UserDetails company) {
        try {
            getHelper().getUserDetailsDao().create(company);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(Message message) {
        try {
            getHelper().getMessagesDao().create(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/
