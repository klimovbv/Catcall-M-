package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class InMemoryContactsService extends BaseInMemoryService {

    protected InMemoryContactsService(CatcallApplication application) {
        super(application);
    }

    @Subscribe
    public void makeCompaniesList(Contacts.GetCompaniesRequest request) {
        Contacts.GetCompaniesResponse response = new Contacts.GetCompaniesResponse();

        ArrayList<UserDetails> fakeList = new ArrayList<>();
        ArrayList<Double> latitude = new ArrayList<>();
        ArrayList<Double> longitude = new ArrayList<>();
        latitude.add(59.92);
        longitude.add(30.24);
        latitude.add(59.94);
        longitude.add(30.23);
        latitude.add(59.87);
        longitude.add(30.34);

        for (int i = 1; i <= 3; i++) {
            fakeList.add(new UserDetails(i, "Company № " + i, null, latitude.get(i-1), longitude.get(i-1)));
        }

        response.companies = fakeList;

        bus.post(response);
    }
}
