package com.spb.kbv.catcallm.infrastructure;

import com.orm.SugarApp;
import com.spb.kbv.catcallm.services.Module;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

public class CatcallApplication extends SugarApp {
    private Auth auth;
    private Bus bus;
    private Picasso authedPicasso;

    public CatcallApplication(){
        bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
        createAuthedPicasso();
        Module.register(this);


        //ormLite:
        /*DatabaseManager.init(this);*/

    }

    private void createAuthedPicasso() {
        //ToDo: build authed Picasso
    }

    public Auth getAuth() {
        return auth;
    }

    public Bus getBus() {
        return bus;
    }

    public Picasso getAuthedPicasso() {
        return authedPicasso;
    }
}
