package com.spb.kbv.catcallm.services.entities;

import android.os.Parcel;
import android.os.Parcelable;


public class UserDetailsCopy/* extends SugarRecord*/ implements Parcelable{

    private long id;
    /*private final boolean isContact;*/
    /*private final String displayName;*/
    private final String username;
    private final String address;
    private final String avatarUrl;
    private final double latitude;
    private final double longitude;


    public UserDetailsCopy(long id, String username, String address, String avatarUrl, double latitude, double longitude) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    private UserDetailsCopy(Parcel parcel) {
        id = parcel.readLong();
        username = parcel.readString();
        address = parcel.readString();
        avatarUrl = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
    }

    public static final Creator<UserDetailsCopy> CREATOR = new Creator<UserDetailsCopy>() {
        @Override
        public UserDetailsCopy createFromParcel(Parcel parcel) {
            return new UserDetailsCopy(parcel);
        }

        @Override
        public UserDetailsCopy[] newArray(int size) {
            return new UserDetailsCopy[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeLong(id);
        destination.writeString(username);
        destination.writeString(address);
        destination.writeString(avatarUrl);
        destination.writeDouble(latitude);
        destination.writeDouble(longitude);
    }
}
