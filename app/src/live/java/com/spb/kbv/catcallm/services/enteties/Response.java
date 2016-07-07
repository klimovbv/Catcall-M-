package com.spb.kbv.catcallm.services.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response extends ApiResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private String message;
    @SerializedName("api_v")
    @Expose
    private String api_v;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String data) {
        this.message = data;
    }

    public String getApiV() {
        return api_v;
    }

    public void setApiV(String api_v) {
        this.api_v = api_v;
    }
}

