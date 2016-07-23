package com.spb.kbv.catcallm.services.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spb.kbv.catcallm.services.Account;

public class RegisterApiResponse extends BaseApiResponse {

    @SerializedName("response")
    @Expose
    private Account.RegisterWithPhoneNumberResponse response;

    public Account.RegisterWithPhoneNumberResponse getResponse() {
        return response;
    }

    public void setResponse(Account.RegisterWithPhoneNumberResponse response) {
        this.response = response;
    }
}