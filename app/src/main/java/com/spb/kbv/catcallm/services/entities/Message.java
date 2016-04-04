package com.spb.kbv.catcallm.services.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;

@DatabaseTable(tableName = "messages")
public class Message {

    @DatabaseField(generatedId =  true)
    private long id;
    @DatabaseField
    private Calendar cratedAt;
    @DatabaseField
    private String messageText;
    /*@DatabaseField
    private long otherUserId;*/
    @DatabaseField(columnName = "id_c", foreign = true)
    private UserDetails otherUser;
    @DatabaseField
    private boolean isFromUs;

    private boolean isRead;

    public Message(){}

    public Message(long id,
                   Calendar cratedAt,
                   String messageText,
                   UserDetails otherUser,
                   boolean isFromUs
                   /*boolean isRead*/) {
        this.id = id;
        this.cratedAt = cratedAt;
        this.messageText = messageText;
        this.otherUser = otherUser;
        this.isFromUs = isFromUs;
        /*this.isRead = isRead;*/
    }

    public long getId() {
        return id;
    }

    public Calendar getCratedAt() {
        return cratedAt;
    }

    public void setCratedAt(Calendar cratedAt) {
        this.cratedAt = cratedAt;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public UserDetails getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(UserDetails otherUser) {
        this.otherUser = otherUser;
    }

    public boolean isFromUs() {
        return isFromUs;
    }

    public void setIsFromUs(boolean isFromUs) {
        this.isFromUs = isFromUs;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    /*public long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(int otherUserId) {
        this.otherUserId = otherUserId;
    }*/
}
