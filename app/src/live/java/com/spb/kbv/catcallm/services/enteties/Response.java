package com.spb.kbv.catcallm.services.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response extends ApiResponse {

//------------------------------------------------------------------//
    //Register Account Response
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_device")
    @Expose
    private String userDevice;

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The userDevice
     */
    public String getUserDevice() {
        return userDevice;
    }

    /**
     *
     * @param userDevice
     * The user_device
     */
    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }
//-------------------------------------------------------//
//
}

