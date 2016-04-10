package com.spb.kbv.catcallm.services.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.orm.SugarRecord;

import java.util.List;

/*@DatabaseTable(tableName = "companyTest")*/
public class UserDetails extends SugarRecord implements Parcelable{

    /*@DatabaseField(generatedId = true)*/
    private Long id;
    /*private final boolean isContact;*/
    /*private final String displayName;*/
    /*@DatabaseField(columnName = "name")*/
    private String username;
    /*@DatabaseField*/
    private String address;
    /*@DatabaseField(columnName = "avatar")*/
    private String avatarUrl;
    /*@DatabaseField*/
    private double latitude;
    /*@DatabaseField*/
    private double longitude;

    public List<Message> getMessages () {
        Log.d("myLogs", " in get Messages id = " + Long.toString(getId()));
        return Message.find(Message.class, "userdetails = ?", Long.toString(getId()));
    }
    /*@ForeignCollectionField(eager = true)*/
    /*private Collection<Message> messages;*/


    public UserDetails() {}


    public UserDetails(long id, String username, String address, String avatarUrl, double latitude,  double longitude) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    private UserDetails(Parcel parcel) {
        id = parcel.readLong();
        username = parcel.readString();
        address = parcel.readString();
        avatarUrl = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

   /* public Collection<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
*/


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

    @Override
    public String toString() {
        return "id = " + id +
                ", username = " + username +
                ", address = " + address +
                ", avatarUrl = " + avatarUrl +
                ", lat = " + latitude +
                ", long = " + longitude;
    }

    public void deleteCompany() {
        Message.deleteAll(Message.class, "userdetails = ?", Long.toString(getId()));
        delete();
        /*List<Message> messages = getMessages();
        for (Message ms : messages){
            ms.delete();
        }*/

    }
}
