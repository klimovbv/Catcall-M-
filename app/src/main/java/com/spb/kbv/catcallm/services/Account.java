package com.spb.kbv.catcallm.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spb.kbv.catcallm.services.enteties.BaseApiResponse;
import com.spb.kbv.catcallm.services.enteties.RegisterApiResponse;
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

    public static class RegisterWithPhoneNumberResponse extends RegisterApiResponse {


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

    public static class DeleteAccountRequest {
    }

    public static class DeleteAccountResponse extends BaseApiResponse {

        @SerializedName("response")
        @Expose
        private Integer response;

        public Integer getResponse() {
            return response;
        }

        public void setResponse(Integer response) {
            this.response = response;
        }
    }

    public static class LoginUserBySmsRequest {
        public String uid;
        public String did;
        public String code;

        public LoginUserBySmsRequest(String uid, String did, String code) {
            this.did = did;
            this.uid = uid;
            this.code = code;
        }

    }

    public static class LoginUserBySmsResponse extends BaseApiResponse {
        @SerializedName("response")
        @Expose
        private Integer response;

        public Integer getResponse() {
            return response;
        }

        public void setResponse(Integer response) {
            this.response = response;
        }

    }
}
