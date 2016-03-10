package com.spb.kbv.catcallm.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.Account;
import com.squareup.otto.Bus;

public class SmsReceiver extends BroadcastReceiver {

    private Bus bus;
    private CatcallApplication application;

    @Override
    public void onReceive(Context context, Intent intent) {

        application = ((CatcallApplication)context.getApplicationContext());
        bus = application.getBus();
        bus.register(this);
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages;
        if (bundle != null) {
            Object[] pduArray = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pduArray.length];

            for (int i = 0; i < pduArray.length; i++){
                messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
            }

            String sms_from = messages[0].getDisplayOriginatingAddress();
            Log.d("mySms", " sms from " + sms_from);
            if (sms_from.equals("+79817698969")){
                StringBuilder smsText = new StringBuilder();
                for (SmsMessage message : messages) {
                    smsText.append(message.getMessageBody());
                }
                String body = smsText.toString();
                Log.d("mySms", " sms text " + body);

                Account.OnReceiveSmsCodeEvent event = new Account.OnReceiveSmsCodeEvent();
                event.code = body;
                bus.post(event);
            }
        }
    }
}
