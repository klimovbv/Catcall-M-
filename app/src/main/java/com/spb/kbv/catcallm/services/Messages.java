package com.spb.kbv.catcallm.services;

import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;

import java.util.ArrayList;

public final class Messages {
    private Messages(){
    };

    public static class SendMessageRequest {
        public long companyId;
        public String messageText;


        public SendMessageRequest(String messageText, long companyId) {
            this.companyId = companyId;
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

    public static class LoadMessagesRequest {
        public long id;
        public UserDetails userDetails;

        public LoadMessagesRequest(long id, UserDetails userDetails) {
            this.id = id;
            this.userDetails = userDetails;
        }
    }

    public static class LoadMessagesResponse {
        public ArrayList<Message> messages;

    }




}
