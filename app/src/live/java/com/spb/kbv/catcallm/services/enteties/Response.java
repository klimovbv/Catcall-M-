package com.spb.kbv.catcallm.services.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response extends ApiResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("api_v")
    @Expose
    private String apiV;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getApiV() {
        return apiV;
    }

    public void setApiV(String apiV) {
        this.apiV = apiV;
    }
}

