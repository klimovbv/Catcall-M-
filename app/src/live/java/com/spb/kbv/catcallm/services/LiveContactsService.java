package com.spb.kbv.catcallm.services;

import android.util.Log;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiveContactsService extends BaseLiveService {

    protected LiveContactsService(CatcallApplication application) {
        super(application);
    }


    @Subscribe
    public void makeCompaniesList(Contacts.GetCompaniesRequest request) {

        Contacts.GetCompaniesResponse response = new Contacts.GetCompaniesResponse();
        Log.d("myLogs", "InMemoryContactsService/makeCompaniesList ==");

        /*List<UserDetails> companies = UserDetails.listAll(UserDetails.class);*/
        /*List<UserDetails> companies = DatabaseManager.getInstance().getAllUsers();*/
        List<UserDetails> companies = UserDetails.listAll(UserDetails.class);
        if (companies != null && companies.size() > 0) {
            Log.d("myLogs", "companies not null  " + companies.size());
            response.companies = companies;
            bus.post(response);
        } else {Log.d("myLogs", "companies  null  ");


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
                String avatar = "http://www.gravatar.com/avatar/" + i + "?d=identicon&s=64";

                UserDetails company = new UserDetails(
                        1,
                        "Company # " + i,
                        "Address " + i,
                        avatar,
                        latitude.get(i - 1),
                        longitude.get(i - 1));

                //ormLite
                // *//*DatabaseManager.getInstance().addUser(company);*//*

                // sugar:
                company.save();


            }
            bus.post(new Contacts.GetCompaniesRequest());
        }
    }



/* Cursor companiesCursor = application.getContentResolver().query(
                MessagesContract.CompaniesEntry.CONTENT_URI, null, null, null, null);

        if (companiesCursor.moveToFirst()) {


            Log.d("myLogs", "companiesCursor is not empty " + companiesCursor);
            ArrayList<UserDetails> fakeList = new ArrayList<>();
            do {
                long id = companiesCursor.getInt(0);
                String avatarUrl = companiesCursor.getString(1);
                String companyName = companiesCursor.getString(2);
                String address = companiesCursor.getString(3);
                double latitude = companiesCursor.getDouble(4);
                double longitude = companiesCursor.getDouble(5);


                Log.d("myLogs", " in WHILE " + id + " / " + companyName + " / " + avatarUrl + " / " +
                        latitude + " / " + longitude);
                fakeList.add(new UserDetails(id, companyName, address, avatarUrl,
                        latitude, longitude));
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
                String avatar = "http://www.gravatar.com/avatar/" + i + "?d=identicon&s=64";

                ContentValues fakeCompaniesValues = new ContentValues();

                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_NAME, "Company # " + i);
                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_ADDRESS, "Address " + i);
                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LATITUDE, latitude.get(i - 1));
                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_LONGITUDE, longitude.get(i - 1));
                fakeCompaniesValues.put(MessagesContract.CompaniesEntry.COLUMN_AVATAR, avatar);

                Uri insertedUri = application.getContentResolver().insert(
                        MessagesContract.CompaniesEntry.CONTENT_URI,
                        fakeCompaniesValues
                );


            }
            companiesCursor.close();
            bus.post(new Contacts.GetCompaniesRequest());
        }
    }*/


    @Subscribe
    public void onSearchCompanies(Contacts.SearchCompanyRequest request){
        Contacts.SearchCompanyResponse response = new Contacts.SearchCompanyResponse();
        String query = request.query;

        ArrayList<Double> latitude = new ArrayList<>();
        ArrayList<Double> longitude = new ArrayList<>();
        latitude.add(59.95);
        longitude.add(30.23);
        latitude.add(59.96);
        longitude.add(30.22);
        latitude.add(59.88);
        longitude.add(30.37);

        ArrayList<UserDetails> fakeList = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
           int k = i * 10;
           String avatar = "http://www.gravatar.com/avatar/" + k + "?d=identicon&s=64";
           fakeList.add(new UserDetails(i, query + "FakeComp # " + k, "Some address " + k, avatar, latitude.get(i-1), longitude.get(i-1)));
        }

        response.companies = fakeList;
        bus.post(response);
    }

    @Subscribe
    public void onLoadDialogsList(Contacts.LoadCompaniesListWithOpenDialogsRequest request) {
        Contacts.LoadCompaniesListWithOpenDialogsResponse response =
                new Contacts.LoadCompaniesListWithOpenDialogsResponse();

        ArrayList<Message> listOfDialogs = new ArrayList<>();

        List<Message> messages = Message.listAll(Message.class);
        Map<Long, Message> idMap= new HashMap<>();
        for (Message mes : messages) {
            idMap.put(mes.getUserdetails().getId(), mes);
        }

        for (Message mes : idMap.values()){
            listOfDialogs.add(mes);
        }



        response.dialogsList = listOfDialogs;
        bus.post(response);

       /* Cursor messagiesCursor = application.getContentResolver().query(
                MessagesContract.MessagesEntry.CONTENT_URI,
                null,
                null,
                null,
                "_id DESC"
        );

        if (messagiesCursor.moveToFirst()) {
            do {

                Message message;
                UserDetails company;
                long _id = messagiesCursor.getLong(0);
                long id = messagiesCursor.getLong(1);
                long date = messagiesCursor.getLong(2);
                String text = messagiesCursor.getString(4);
                boolean isFromus = (messagiesCursor.getInt(3) == 1);
                boolean isRead = false;
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(date);



                Log.d("myCursor", " id = " + id + " _id = " + _id);

                Cursor companyCursor = application.getContentResolver().query(
                        MessagesContract.CompaniesEntry.CONTENT_URI,
                        null,
                        "_id = ?",
                        new String[] {String.valueOf(id)},
                        null);

                if (companyCursor.moveToFirst()) {

                    String avatarUrl = companyCursor.getString(1);
                    String companyName = companyCursor.getString(2);
                    String address = companyCursor.getString(3);
                    double latitude = companyCursor.getDouble(4);
                    double longitude = companyCursor.getDouble(5);

                    Log.d("myCursor", " company name = " + companyName);


                    company = new UserDetails(id, companyName, address, avatarUrl,
                            latitude, longitude);

                    message = new Message(
                            _id,
                            calendar,
                            text,
                            company,
                            isFromus,
                            isRead
                    );
                    listOfDialogs.add(message);
                }

            } while (messagiesCursor.moveToNext());
        } else
            Log.d("myCursor", "cursor empty===");

        response.dialogsList = listOfDialogs;
        bus.post(response);
    }*/


    }
}

