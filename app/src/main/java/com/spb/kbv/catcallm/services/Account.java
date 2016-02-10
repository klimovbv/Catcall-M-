package com.spb.kbv.catcallm.services;

public final class Account {

    private Account(){
    };

    public static class RegisterWithPhoneNumberRequest {
        public String phoneNumber;

        public RegisterWithPhoneNumberRequest(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    public static class RegisterWithPhoneNumberResponse{
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


}
