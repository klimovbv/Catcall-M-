package com.spb.kbv.catcallm.services;


import com.spb.kbv.catcallm.services.enteties.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CatcallWebService {

    @FormUrlEncoded
    @POST("user.new")
    Call<Account.RegisterWithPhoneNumberResponse> createAccount(
            @Field("phone") String phoneNumber
    );

    //Get information about user
    @FormUrlEncoded
    @POST("user.get")
    Call<Account.GetUserResponse> getAccount(
            @Field("did") String deviceId,
            @Field("uid") String userId
    );

 /*   @FormUrlEncoded
    @POST("user.login")
    Call<Account.LoginUserBySmsResponse> loginUserSmsCode(
            @Field("act") Integer action,
            @Field("uid") String userId,
            @Field("did") String deviceId,
            @Field("code") String smsCode
    );

    @FormUrlEncoded
    @POST("user.login")
    Call<Account.LoginUserByPhoneResponse> loginUserSmsCode(
            @Field("act") Integer action,
            @Field("phone") String phone,
            @Field("did") String deviceId
    );

    @FormUrlEncoded
    @POST("user.login")
    Call<Account.ConfirmDeviceAuthorizationResponse> loginUserSmsCode(
            @Field("phone") Integer phone,
            @Field("did") String deviceId,
            @Field("code") String smsCode
    );

    @FormUrlEncoded
    @POST("user.remove")
    Call<Account.DeleteAccountResponse> deleteAccount(
            @Field("did") String deviceId,
            @Field("uid") String userId,
            @Field("act") Integer actionCode
    );

*/
}
