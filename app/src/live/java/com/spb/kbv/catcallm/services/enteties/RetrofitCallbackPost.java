package com.spb.kbv.catcallm.services.enteties;

import android.util.Log;

import com.squareup.otto.Bus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallbackPost <T extends BaseApiResponse> implements Callback<T> {

    private final Bus bus;
    private final Class<T> resultType;


    public RetrofitCallbackPost(Class<T> resultType, Bus bus) {
        this.resultType = resultType;
        this.bus = bus;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 403) {
            Log.d("retroLog", "403!!!");
            return;
        }

        if (response.code() == 400) {
            Log.d("retroLog", "400!!!");
            return;
        }

        if (response.code() == 500) {
            Log.d("retroLog", "500!!! " + response.message());
            return;
        }
        Log.d("retroLog", "code = " + response.code() + " message =  + " + response.message());
        bus.post(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d("retroLog", "onFailure" + t);
    }
}
