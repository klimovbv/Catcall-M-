package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;

public final class Messages {
    private Messages(){
    };

    public static class SendMessageRequest {
        public String messageText;

        public SendMessageRequest(String messageText) {
            this.messageText = messageText;
        }
    }

    public static class SendMessageResponse {
        public Message message;


    }

    public static class ReceiveIncomeMessageRequest {
        public UserDetails details;

        public ReceiveIncomeMessageRequest(UserDetails details) {
            this.details = details;
        }
    }

    public static class ReceiveIncomeMessageResponse {
        public Message message;

    }




}
