package com.spb.kbv.catcallm.services.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDetails implements Parcelable{

    private final long id;
    /*private final boolean isContact;*/
    /*private final String displayName;*/
    private final String username;
    private final String avatarUrl;

    public UserDetails(long id, String username, String avatarUrl) {
        this.id = id;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    private UserDetails(Parcel parcel) {
        id = parcel.readLong();
        username = parcel.readString();
        avatarUrl = parcel.readString();
    }

    public static final Creator<UserDetails> CREATOR = new Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(Parcel parcel) {
            return new UserDetails(parcel);
        }

        @Override
        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeLong(id);
        destination.writeString(username);
        destination.writeString(avatarUrl);
    }
}
