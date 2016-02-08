package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.spb.kbv.catcallm.R;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private View mSendButton;
    private View mPhoneNumberText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.activity_registration);

        mPhoneNumberText = findViewById(R.id.activity_phone_number_text);
        mSendButton = findViewById(R.id.activity_registration_send_button);
        mSendButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

    }
}
