package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.otto.Subscribe;

import java.util.Calendar;

public class InMemoryMessagesService extends BaseInMemoryService{
    protected InMemoryMessagesService(CatcallApplication application) {
        super(application);
    }

    @Subscribe
    public void sendMessage(Messages.SendMessageRequest request) {
        Messages.SendMessageResponse response = new Messages.SendMessageResponse();
        Message message = new Message(1, Calendar.getInstance(), request.messageText, null, true, false);

        response.message = message;

        bus.post(response);
    }

    @Subscribe
    public void receiveMessage(Messages.ReceiveIncomeMessageRequest request){
        Messages.ReceiveIncomeMessageResponse response = new Messages.ReceiveIncomeMessageResponse();

        UserDetails details = request.details;
        if (details == null) {
            details = new UserDetails(1, "Other Person", null);
        }

        response.message = new Message(1, Calendar.getInstance(), "some new text", details, false, true);

        bus.post(response);
    }
}
