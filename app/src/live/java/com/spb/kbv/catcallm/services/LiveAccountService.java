package com.spb.kbv.catcallm.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

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
        Call<Object> call = api.createAccount(request.phoneNumber);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("retroLog", "Response status code: " + response.code());
                Log.d("retroLog", "Response toString: " + response.toString());
                Log.d("retroLog", "Response body toString: " + response.body().toString());
                /*Log.d("retroLog", "Response : " + new Gson().toJson(response));*/
               /* Map<String, String> map = new HashMap<String, String>();
                map = gson.fromJson(new Gson().toJson(response), map.getClass());

                for (Map.Entry e : map.entrySet()) {
                    Log.d("retroLog", "Response body: " + e.getKey() + " " + e.getValue());
                }*/
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("retroLog", "==== FAILURE ");
            }
        });

    }
}
