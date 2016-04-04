package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.data.DatabaseManager;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.Message;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class InMemoryMessagesService extends BaseInMemoryService{
    protected InMemoryMessagesService(CatcallApplication application) {
        super(application);
    }

    @Subscribe
    public void sendMessage(Messages.SendMessageRequest request) {
        Messages.SendMessageResponse response = new Messages.SendMessageResponse();

        Message message = new Message(1,
                Calendar.getInstance(),
                request.messageText,
                request.details,
                true
        );

        DatabaseManager.getInstance().addMessage(message);

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
                request.details,
                false
        );

        DatabaseManager.getInstance().addMessage(message);

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

        Collection<Message> allMessages = request.userDetails.getMessages();
        ArrayList<Message> messages = new ArrayList<>();


        if (allMessages.size() > 0){
            for (Message message : allMessages) {

                    messages.add(message);

            }
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

        Messages.LoadMessagesResponse response = new Messages.LoadMessagesResponse();
        response.messages = messages;
        bus.post(response);
    }
}
