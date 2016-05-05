package com.spb.kbv.catcallm.services;

import android.util.Log;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.Message;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LiveMessagesService extends BaseLiveService {
    protected LiveMessagesService(CatcallApplication application) {
        super(application);
    }

    @Subscribe
    public void sendMessage(Messages.SendMessageRequest request) {
        Messages.SendMessageResponse response = new Messages.SendMessageResponse();

        Message message = new Message(1,
                Calendar.getInstance(),
                request.messageText,
                /*request.details,*/
                true
        );

        message.setUserdetails(request.details);
        message.save();
        /*DatabaseManager.getInstance().addMessage(message);*/

        /*ContentValues contentValues = new ContentValues();
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_COMP_KEY, request.companyId);
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_DATE, Calendar.getInstance().getTimeInMillis());
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_ISFROMUS, 1);
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_TEXT, request.messageText);
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_FILE, "null");

        Uri insertedUri = application.getContentResolver().insert(
                MessagesContract.MessagesEntry.CONTENT_URI, contentValues);

*/

       /* Message message = new Message(1, Calendar.getInstance(), request.messageText, null, true, false);*/

        response.message = message;
        bus.post(response);
    }

    @Subscribe
    public void receiveMessage(Messages.ReceiveIncomeMessageRequest request){
        Messages.ReceiveIncomeMessageResponse response = new Messages.ReceiveIncomeMessageResponse();

        Message message = new Message(1,
                Calendar.getInstance(),
                "some new text",
                /*request.details,*/
                false
        );

        message.setUserdetails(request.details);
        /*DatabaseManager.getInstance().addMessage(message);*/
        message.save();

        /*ContentValues contentValues = new ContentValues();
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_COMP_KEY, request.details.getId());
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_DATE, Calendar.getInstance().getTimeInMillis());
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_ISFROMUS, 0);
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_TEXT, "some new text");
        contentValues.put(MessagesContract.MessagesEntry.COLUMN_FILE, "null");

        Uri insertedUri = application.getContentResolver().insert(
                MessagesContract.MessagesEntry.CONTENT_URI, contentValues);*/

        /*UserDetails details = request.details;
        if (details == null) {
            details = new UserDetails(1, "Other Person", null, 54, 32);
        }*/

     /*   response.message = new Message(1, Calendar.getInstance(), "some new text", request.details, false, true);*/
        response.message = message;

        bus.post(response);
    }

    @Subscribe
    public void loadMessages(Messages.LoadMessagesRequest request){


        /*Collection<Message> allMessages = request.userDetails.getMessages();*/

  /*      List<Message> am1 = Message.listAll(Message.class);
        if (am1 != null) {
            Log.d("myLogs", " am > 0 " + am1.size());
            for (Message mes : am1){
                Log.d("myLogs", " messgae +" + mes.getId() + " " + mes.getUserdetails().getUsername() + " " +  mes.getMessageText());
            }
        }
        else {
            Log.d("myLogs", " am = 0 " + (am1 == null));
        }*/

        List<Message> am = request.userDetails.getMessages();
        if (am != null) {
            Log.d("myLogs", " am > 0 " + am.size());
            for (Message mes : am){
                Log.d("myLogs", " messgae +" + mes.getId() + " " + /*mes.getUserdetails().getId()*/  mes.getMessageText());
            }
        }
        else {
            Log.d("myLogs", " am = 0 " + (am == null));
        }

        Messages.LoadMessagesResponse response = new Messages.LoadMessagesResponse();
        if (am != null && am.size() > 0) {
            ArrayList<Message> messages = new ArrayList<>();


            if (am != null && am.size() > 0) {
                Log.d("myLogs", " allMessage > 0 " + am.size());
                for (Message message : am) {

                    messages.add(message);

                }
            } else {
                Log.d("myLogs", " allMessage = 0 ");
            }

        /*Cursor messagiesCursor = application.getContentResolver().query(
                MessagesContract.MessagesEntry.CONTENT_URI,
                null,
                "comp_id = ?",
                new String[]{String.valueOf(request.id)},
                null
        );
        ArrayList<Message> messages = new ArrayList<>();
        if (messagiesCursor.moveToFirst()){

            do {
                long _id = messagiesCursor.getLong(1);
                long date = messagiesCursor.getLong(2);
                String text = messagiesCursor.getString(4);
                boolean isFromus = (messagiesCursor.getInt(3) == 1);
                boolean isRead = false;
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(date);

                messages.add(new Message(
                       _id,
                        calendar,
                        text,
                        request.userDetails,
                        isFromus,
                        isRead
                ));
            } while (messagiesCursor.moveToNext());
        }*/


            response.messages = messages;
        }
        bus.post(response);
    }
}
