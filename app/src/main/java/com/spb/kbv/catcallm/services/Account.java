package com.spb.kbv.catcallm.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spb.kbv.catcallm.services.enteties.ApiResponse;
import com.spb.kbv.catcallm.services.enteties.Data;
import com.spb.kbv.catcallm.services.enteties.Response;

public final class Account {

    private Account(){
    };

    public static class RegisterWithPhoneNumberRequest {
        public String phoneNumber;

        public RegisterWithPhoneNumberRequest(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    public static class RegisterWithPhoneNumberResponse extends ApiResponse {

    }

    public static class SendVerificationCodeRequest {
        public String verificationCode;

        public SendVerificationCodeRequest(String verificationCode) {
            this.verificationCode = verificationCode;
        }
    }

    public static class SendVerificationCodeResponse {
    }

    public static class LoginWithLocalTokenRequest {
        public String authToken;

        public LoginWithLocalTokenRequest(String authToken) {
            this.authToken = authToken;
        }
    }

    public static class LoginWithLocalTokenResponse {
    }

    public static class OnReceiveSmsCodeEvent {
        public String code;
    }


    public class GetUserResponse extends Response {
        public String user_id;
    }
}
