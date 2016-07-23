package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.services.Account;
import com.squareup.otto.Subscribe;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {
    private static String numbers = "0123456789";
    private View mSendButton;
    private EditText mPhoneNumberText;
    private boolean unableChangeEditText = false;
    private String phoneNumberFormat = "--- --- -- --";
    private int mStarter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mPhoneNumberText = (EditText) findViewById(R.id.activity_phone_number_text);
        mSendButton = findViewById(R.id.activity_registration_send_button);
        mSendButton.setOnClickListener(this);


        /*mPhoneNumberText.addTextChangedListener(new TextWatcher() {
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
                mStarter = mPhoneNumberText.getSelectionStart();
                int newStart = mPhoneNumberText.getSelectionStart();
                Log.d("edLog", "editable = " + s + "start = " + mStarter);
                *//*String phone = mPhoneNumberText.getText().toString();
                String numbers = "0123456789";*//*

                *//*StringBuilder builder = new StringBuilder(phone.length());
                for (int i = 0; i < phone.length(); i++) {
                    String ch = phone.substring(i, i + 1);
                    if (numbers.contains(ch)){
                        builder.append(ch);
                        if (phoneNumberFormat.substring(builder.length(), builder.length() + 1).equals(" ")) {
                            Log.d("edLog2", "adding___  = " + ch);
                            builder.append(" ");

                            if (phone.substring(phone., start + 3).equals("-")) {
                                Log.d("edLog2", "i == phone.length() -1 ");
                                start++;
                                *//**//*start = start + 2;*//**//*
                            }
                        }
                        Log.d("edLog", "getting numbers  = " + ch);
                    }
                }*//*

                unableChangeEditText = true;
                *//*int phoneLength = builder.length();*//*

                *//*builder.append(phoneNumberFormat.substring(phoneLength, phoneNumberFormat.length()));*//*

                *//*for (int i = 0; i < (10 - phoneLength); i++) {
                    builder.append("-");
                }*//*
                Log.d("edLog2", "getText = " + mPhoneNumberText.getText().toString());
                String newText = stringMatch(mPhoneNumberText.getText().toString(), mStarter);
                mPhoneNumberText.setText(newText);
                mPhoneNumberText.setSelection(mStarter);
                unableChangeEditText = false;

            }
        });*/

        mPhoneNumberText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    public String stringMatch(String editingString, int starter) {
        String format = "--- --- -- --";


        int editingLength = editingString.length();
        int formatLength = format.length();
        boolean starterSet = false;

        Log.d("edLog2", " editingString = " + editingString);

        if (mStarter > formatLength && !editingString.substring(editingLength - 1, editingLength).equals("-")){
            mStarter--;
            return editingString.substring(0, editingLength - 1);
        };

        StringBuilder numbersFromEnteredString = new StringBuilder();
        for (int i = 0; i < editingString.length(); i++) {
            String ch = editingString.substring(i, i + 1);
            if (numbers.contains(ch)) {
                numbersFromEnteredString.append(ch);
            }
        }


        if (mStarter != 0 && format.substring(mStarter - 1, mStarter).equals(" ") && !editingString.substring(mStarter - 2, mStarter - 1).equals("-")){
            mStarter++;
            starterSet = true;
            /*Log.d("edLog2", "first if " + editingString.substring(mStarter, mStarter + 1));*/
        }


        String enteredNumbers = numbersFromEnteredString.toString();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < formatLength; i++) {
            if (format.substring(i, i + 1).equals(" ")){
                builder.append(" ");
                if (mStarter == i) {
                    mStarter++;
                    starterSet = true;
                }
            } else if (enteredNumbers.length() > 0) {
                builder.append(enteredNumbers.charAt(0));
                enteredNumbers = enteredNumbers.substring(1, enteredNumbers.length());
                if (!starterSet && mStarter == i) {
                    if (format.substring(i, i + 1).equals(" ")){
                        mStarter++;
                    }
                    starterSet = true;
                }
            } else {
                if (!starterSet) {
                    mStarter = i;
                    starterSet = true;
                }
                builder.append("-");
            }
        }

        Log.d("edLog2", "builder = " + builder);

       /* StringBuilder phoneNumber = new StringBuilder();
        for (int i = 0; i < builder.length(); i++){

            if (format.substring(phoneNumber.length(), phoneNumber.length() + 1).equals(" ")){
                Log.d("edLog2", "added ___ " + " starter = " + starter + " length = " + phoneNumber.length());
                phoneNumber.append(" ");
                if (phoneNumber.length() == starter - 1) {
                    mStarter++;
                    Log.d("edLog2", "starter + 1");
                }
            }

            phoneNumber.append(builder.substring(i, i + 1));

        }
        Log.d("edLog2", "phoneBuilder = " + phoneNumber);

        if (phoneNumber.length() < formatLength){
            phoneNumber.append(format.substring(phoneNumber.length(), formatLength));
        }*/
        /*if (builder.length() < format.length()) {
            builder.append(format.substring(builder.length(), format.length()));
        }*/
        return builder.toString();
    }

    @Override
    public void onClick(View view) {
        String phoneNumber = mPhoneNumberText.getText().toString();
        StringBuilder buffer = new StringBuilder();
        buffer.append("+7");
        for (int i = 0; i < phoneNumber.length(); i++) {
            String charCheck = phoneNumber.substring(i, i +1);
            if (numbers.contains(charCheck)) {
                buffer.append(charCheck);
            }
        }
        String numberForRegistration = buffer.toString();
        Log.d("phoneLog", "phone = " + buffer.toString());
        /*if (numberForRegistration.length() != 12) {
            mPhoneNumberText.setError("Enter valid number");
            return;
        }*/


        bus.post(new Account.RegisterWithPhoneNumberRequest(numberForRegistration/*"+79062446078"*/));

        /*Intent intent = new Intent(this, EnterRegistrationCodeActivity.class);
        startActivity(intent);
        finish();*/
    }/**/

    @Subscribe
    public void onGetResponseFromRegistrationRequest (Account.RegisterWithPhoneNumberResponse response){
        Log.d("retroLog", "on Response");
        if (response.didSucceed()){
            Log.d("retroLog", "on respone OK " + response.getResponse().getStatus() +" id = " + response.getResponse().getUserId());

        } else {
            Log.d("retroLog", "Response new message: " + response.getError().getErrMsg());
            response.showErrorToast(this);
        }
    }




}
