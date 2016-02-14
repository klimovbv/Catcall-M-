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

        for (int i = 1; i <= 25; i++) {
            fakeList.add(new UserDetails(i, "Company № " + i, null));
        }

        response.companies = fakeList;

        bus.post(response);
    }
}
