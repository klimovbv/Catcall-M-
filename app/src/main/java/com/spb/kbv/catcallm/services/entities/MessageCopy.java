package com.spb.kbv.catcallm.services.entities;

import java.util.Calendar;

public class MessageCopy {

    private long id;
    private Calendar cratedAt;
    private String messageText;
    private UserDetails otherUser;
    private boolean isFromUs;
    private boolean isRead;


    public MessageCopy(long id,
                       Calendar cratedAt,
                       String messageText,
                       UserDetails otherUser,
                       boolean isFromUs,
                       boolean isRead) {
        this.id = id;
        this.cratedAt = cratedAt;
        this.messageText = messageText;
        this.otherUser = otherUser;
        this.isFromUs = isFromUs;
        this.isRead = isRead;
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
}
