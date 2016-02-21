package com.spb.kbv.catcallm.services;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.spb.kbv.catcallm.data.MessagesContract;
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
        Log.d("myLogs", "InMemoryContactsService/makeCompaniesList ==");
        Cursor companiesCursor = application.getContentResolver().query(
                MessagesContract.CompaniesEntry.CONTENT_URI, null, null, null, null);

        if (companiesCursor.moveToFirst()) {
            Log.d("myLogs", "companiesCursor is not empty " + companiesCursor);
            ArrayList<UserDetails> fakeList = new ArrayList<>();
            do {
                Log.d("myLogs", " in WHILE " + companiesCursor.getInt(0) + " / " + companiesCursor.getString(2) + " / " + companiesCursor.getString(1) + " / " +
                        companiesCursor.getDouble(3) + " / " + companiesCursor.getDouble(4));
                fakeList.add(new UserDetails(companiesCursor.getLong(0), companiesCursor.getString(2), companiesCursor.getString(1),
                        companiesCursor.getDouble(3), companiesCursor.getDouble(4)));
            } while (companiesCursor.moveToNext());


            response.companies = fakeList;

            bus.post(response);
        } else {
            Log.d("myLogs", "companiesCurso is empty ");
            ArrayList<Double> latitude = new ArrayList<>();
            ArrayList<Double> longitude = new ArrayList<>();
            latitude.add(59.92);
            longitude.add(30.24);
            latitude.add(59.94);
            longitude.add(30.23);
            latitude.add(59.87);
            longitude.add(30.34);

            for (int i = 1; i <= 3; i++) {
                ContentValues fakeCompaniesValues = new ContentValues();

                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_NAME, "Company # " + i);
                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LATITUDE, latitude.get(i - 1));
                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LONGITUDE, longitude.get(i - 1));
                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_AVATAR, "someUrl");

                Uri insertedUri = application.getContentResolver().insert(
                        MessagesContract.CompaniesEntry.CONTENT_URI,
                        fakeCompaniesValues
                );


            }
            companiesCursor.close();
            bus.post(new Contacts.GetCompaniesRequest());
        }
    }
}
