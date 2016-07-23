package com.spb.kbv.catcallm.services;

import android.test.suitebuilder.annotation.Suppress;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.infrastructure.User;
import com.spb.kbv.catcallm.services.enteties.RetrofitCallbackPost;
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

        Log.d("retroLog", "in register");
        api.createAccount(request.phoneNumber).enqueue(new RetrofitCallbackPost<>(
                Account.RegisterWithPhoneNumberResponse.class, bus));
    }

    @Subscribe
    public void delete(Account.DeleteAccountRequest request){
        Log.d("retroLog", " in delete");
        User user = application.getAuth().getUser();
        api.deleteAccount(user.getDeviceId(), user.getUserId(), 3).enqueue(new RetrofitCallbackPost<>(
                Account.DeleteAccountResponse.class, bus));
    }

    @Subscribe
    public void smsVerification(Account.LoginUserBySmsRequest request) {

        String uid = request.uid;
        String did = request.did;
        String code = request.code;
        Log.d("retroLog", " in smsVerification uid = " + uid + " / did = " + did + " / code = " + code);
        api.loginUserSmsCode(0, request.uid, request.did, request.code).enqueue(new RetrofitCallbackPost<>(
                Account.LoginUserBySmsResponse.class, bus));
    }
}
