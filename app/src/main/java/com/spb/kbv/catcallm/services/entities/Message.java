package com.spb.kbv.catcallm.services.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

/*/@DatabaseTable(tableName = "messages")*/
public class Message extends SugarRecord implements Parcelable {

    /*@DatabaseField(generatedId =  true)*/
    private Long id;
    /*@DatabaseField
    private Calendar cratedAt;*/
    /*@DatabaseField*/
    private String messageText;
    /*@DatabaseField
    private long otherUserId;*/
    /*@DatabaseField(columnName = "id_c", foreign = true, foreignAutoRefresh = true)*/
    private UserDetails userdetails;
    /*@DatabaseField*/
    private boolean isFromUs;
    @Ignore
    private boolean isRead;

    public Message(){}

    public Message(long id,
                   /*Calendar cratedAt,*/
                   String messageText,
                   /*UserDetails otherUser,*/
                   boolean isFromUs
                   /*boolean isRead*/) {
        this.id = id;
        /*this.cratedAt = cratedAt;*/
        this.messageText = messageText;
        /*this.otherUser = otherUser;*/
        this.isFromUs = isFromUs;
        /*this.isRead = isRead;*/
    }

    protected Message(Parcel in) {
        id = in.readLong();
        messageText = in.readString();
        /*otherUser = in.readParcelable(UserDetails.class.getClassLoader());*/
       /* isFromUs = in.readByte() != 0;
        isRead = in.readByte() != 0;*/
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public Long getId() {
        return id;
    }

    /*public Calendar getCratedAt() {
        return cratedAt;
    }

    public void setCratedAt(Calendar cratedAt) {
        this.cratedAt = cratedAt;
    }*/

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public UserDetails getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(UserDetails userdetails) {
        this.userdetails = userdetails;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(messageText);
        /*dest.writeParcelable(otherUser, flags);*/
        dest.writeByte((byte) (isFromUs ? 1 : 0));
        /*dest.writeByte((byte) (isRead ? 1 : 0));*/
    }

    /*public long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(int otherUserId) {
        this.otherUserId = otherUserId;
    }*/

    @Override
    public String toString() {
        return "id = " + id + " text = " + messageText /*+ " user = " + otherUser.getUsername()*/ ;
    }
}
