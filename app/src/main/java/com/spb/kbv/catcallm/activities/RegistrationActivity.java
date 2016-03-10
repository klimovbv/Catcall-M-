package com.spb.kbv.catcallm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.receivers.SmsReceiver;
import com.spb.kbv.catcallm.services.Account;
import com.squareup.otto.Subscribe;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private View mSendButton;
    private EditText mPhoneNumberText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mPhoneNumberText = (EditText) findViewById(R.id.activity_phone_number_text);
        mSendButton = findViewById(R.id.activity_registration_send_button);
        mSendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String phoneNumber = mPhoneNumberText.getText().toString();
        if (phoneNumber.length() != 7) {
            mPhoneNumberText.setError("Enter valid number");
            return;
        }


        bus.post(new Account.RegisterWithPhoneNumberRequest(phoneNumber));

        Intent intent = new Intent(this, EnterRegistrationCodeActivity.class);
        startActivity(intent);
        finish();
    }


}
