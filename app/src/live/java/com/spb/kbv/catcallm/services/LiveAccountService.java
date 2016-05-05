package com.spb.kbv.catcallm.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

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
        Call<UserDetails> call = api.createAccount(request.phoneNumber);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                Log.d("retroLog", "Response status code: " + response.code());
                Map<String, String> map = gson.fromJson(response.toString(), Map.class);

                for (Map.Entry e : map.entrySet()) {
                    Log.d("retroLog", "Response body: " + e.getKey() + " " + e.getValue());
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Log.d("retroLog", "==== FAILURE ");
            }
        });

    }
}
