package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Module {
    private static final String API_URL = "http://api.catcall.ru/api";

    public static void register (CatcallApplication application) {
        /*new InMemoryAccountService(application);*/

        CatcallWebService api = createWebService();
        new LiveMessagesService(application);
        new LiveContactsService(application);
        new LiveAccountService(application, api);
    }

    private static CatcallWebService createWebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CatcallWebService.class);
    }
}
