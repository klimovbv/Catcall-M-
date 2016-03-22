package com.spb.kbv.catcallm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
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
    private boolean unableChangeEditText = false;
    private String phoneNumberFormat = "--- --- -- --";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mPhoneNumberText = (EditText) findViewById(R.id.activity_phone_number_text);
        mSendButton = findViewById(R.id.activity_registration_send_button);
        mSendButton.setOnClickListener(this);


        mPhoneNumberText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("edLog", "charseq = " + s + " start = " + start + " count = " + count + " after = " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (unableChangeEditText) {
                    return;
                }
                int start = mPhoneNumberText.getSelectionStart();
                int newStart = mPhoneNumberText.getSelectionStart();
                Log.d("edLog", "editable = " + s + "start = " + start);
                String phone = mPhoneNumberText.getText().toString();
                String numbers = "0123456789";

                StringBuilder builder = new StringBuilder(phone.length());
                for (int i = 0; i < phone.length(); i++) {
                    String ch = phone.substring(i, i + 1);
                    if (numbers.contains(ch)){
                        builder.append(ch);
                        if (phoneNumberFormat.substring(builder.length(), builder.length() + 1).equals(" ")) {
                            builder.append(" ");
                            /*start++;*/
                        }
                        Log.d("edLog", "getting numbers  = " + ch);
                    }
                }

                unableChangeEditText = true;
                int phoneLength = builder.length();
                if (phoneNumberFormat.substring(start, start + 1).equals(" ")){
                    /*builder.insert(start, " ");*/
                }
                builder.append(phoneNumberFormat.substring(phoneLength, phoneNumberFormat.length()));

                /*for (int i = 0; i < (10 - phoneLength); i++) {
                    builder.append("-");
                }*/
                Log.d("edLog", "builder = " + builder);
                mPhoneNumberText.setText(builder);
                mPhoneNumberText.setSelection(start);
                unableChangeEditText = false;

            }
        });
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
