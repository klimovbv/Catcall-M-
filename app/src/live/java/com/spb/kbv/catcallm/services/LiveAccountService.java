package com.spb.kbv.catcallm.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.enteties.ApiResponse;
import com.squareup.otto.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveAccountService extends BaseLiveService {

    private Gson gson = new GsonBuilder().create();

    protected LiveAccountService(CatcallApplication application, CatcallWebService api) {
        super(application, api);
    }

    @Subscribe
    public void register(Account.RegisterWithPhoneNumberRequest request) {
        /*Call<ApiResponse> call = api.createAccount();*/
        api.createAccount(request.phoneNumber).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call <ApiResponse> apiResponseCall, Response<ApiResponse> response) {
                if (response.body().getError() != null) {
                    Log.d("retroLog", "Response status code: " + response.code());
                    Log.d("retroLog", "Response toString: " + response.toString());
                    Log.d("retroLog", "Response body toString: " + response.body().toString());
                    String text = response.body().getError().getMessage();
                    Log.d("retroLog", "Response new message: " + text);
                } else {
                    Log.d("retroLog", "NOT ERROR ");
                }
                /*Log.d("retroLog", "Response : " + new Gson().toJson(response));*/
               /* Map<String, String> map = new HashMap<String, String>();
                map = gson.fromJson(new Gson().toJson(response), map.getClass());

                for (Map.Entry e : map.entrySet()) {
                    Log.d("retroLog", "Response body: " + e.getKey() + " " + e.getValue());
                }*/
            }

            @Override
            public void onFailure(Call <ApiResponse> apiResponseCall, Throwable t) {
                Log.d("retroLog", "==== FAILURE ");
            }
        });

    }
}
