package com.spb.kbv.catcallm.services.enteties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetrofitCallback <T extends RegisterApiResponse> implements Callback<T> {

    protected final Class<T> resultType;

    public RetrofitCallback(Class<T> resultType) {
        this.resultType = resultType;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
