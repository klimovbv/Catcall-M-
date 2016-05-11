package com.spb.kbv.catcallm.services.enteties;

import android.util.Log;

import com.squareup.otto.Bus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallbackPost <T extends ApiResponse> implements Callback<T> {

    private final Bus bus;
    private final Class<T> resultType;


    public RetrofitCallbackPost(Class<T> resultType, Bus bus) {
        this.resultType = resultType;
        this.bus = bus;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.d("retroLog", "in onResponse in RetrofitCallback" + resultType);
        bus.post(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
