package com.spb.kbv.catcallm.services;


import com.spb.kbv.catcallm.services.enteties.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CatcallWebService {

    @FormUrlEncoded
    @POST("user.create")
    Call<ApiResponse> createAccount(
            @Field("user_phone") String phoneNumber
    );
}
