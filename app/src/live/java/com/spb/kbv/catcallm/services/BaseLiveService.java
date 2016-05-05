package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.squareup.otto.Bus;

public class BaseLiveService {

    protected final Bus bus;
    protected final CatcallApplication application;
    protected final CatcallWebService api;

    /*protected final Handler handler;
    protected final Random random;*/


    protected BaseLiveService(CatcallApplication application, CatcallWebService api){
        this.application = application;
        this.api = api;
        bus = application.getBus();
        bus.register(this);
      /*  handler = new Handler();
        random = new Random();*/
    }
    /*protected void invokeDelayed(Runnable runnable, long millisecondMin, long millisecondMax){
        if (millisecondMin > millisecondMax)
            throw new IllegalArgumentException("Min must be smaller than max");
        long delay = (long)(random.nextDouble() * (millisecondMax - millisecondMin)) + millisecondMin;
        handler.postDelayed(runnable, delay);
    }

    protected void postDelayed(final Object object, long millisecondMin, long millisecondMax){
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                bus.post(object);
            }
        }, millisecondMin, millisecondMax);
    }

    protected void postDelayed(Object event, long milliseconds){
        postDelayed(event, milliseconds, milliseconds);
    }

    protected void postDelayed(Object event){
        postDelayed(event, 600, 1200);
    }*/

}

