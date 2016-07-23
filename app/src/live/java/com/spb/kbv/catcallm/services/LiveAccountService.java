package com.spb.kbv.catcallm.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.infrastructure.User;
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
        /*Call<Account.RegisterWithPhoneNumberResponse> call = api.createAccount(request.phoneNumber);*/
        Log.d("retroLog", "in register");
        /*api.createAccount(request.phoneNumber).enqueue(new RetrofitCallbackPost<>(
                Account.RegisterWithPhoneNumberResponse.class, bus));*/

        api.createAccount(request.phoneNumber).enqueue(new Callback<Account.RegisterWithPhoneNumberResponse>() {
            @Override
            public void onResponse(Call<Account.RegisterWithPhoneNumberResponse> call, Response<Account.RegisterWithPhoneNumberResponse> response) {
                Account.RegisterWithPhoneNumberResponse resp = response.body();
                Log.d("retroLog", "onResponse callback" + response.message() + " / " + response.body()
                );
                if (response.body() != null)
                    bus.post(response.body());
            }

            @Override
            public void onFailure(Call<Account.RegisterWithPhoneNumberResponse> call, Throwable t) {

            }
        });



    }

    @Subscribe
    public void delete(Account.DeleteAccountRequest request){
        Log.d("retroLog", " in delete");
        User user = application.getAuth().getUser();
        api.deleteAccount(user.getDeviceId(), user.getUserId(), 3).enqueue(new Callback<Account.DeleteAccountResponse>() {
            @Override
            public void onResponse(Call<Account.DeleteAccountResponse> call, Response<Account.DeleteAccountResponse> response) {
                bus.post(response);
            }

            @Override
            public void onFailure(Call<Account.DeleteAccountResponse> call, Throwable t) {

            }
        });
    }
}
