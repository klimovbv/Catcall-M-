package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.services.entities.Message;

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

        public SendMessageResponse (Message message) {
            this.message = message;
        }
    }

    public static class ReceiveIncomeMessageRequest {

    }

    public static class ReceiveIncomeMessageResponse {
        public Message message;

        public ReceiveIncomeMessageResponse(Message message) {
            this.message = message;
        }
    }




}
