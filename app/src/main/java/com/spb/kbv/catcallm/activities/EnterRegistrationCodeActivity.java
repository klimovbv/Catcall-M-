package com.spb.kbv.catcallm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.services.Account;
import com.squareup.otto.Subscribe;

public class EnterRegistrationCodeActivity extends BaseActivity implements View.OnClickListener {
    EditText editText;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);

        editText = (EditText)findViewById(R.id.activity_enter_code_editText);
        button = (Button)findViewById(R.id.activity_enter_code_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (editText.getText().toString().equals("33333")){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            editText.setError("Wrong code");
        }
    }

    @Subscribe
    public void onReceiveSms(Account.OnReceiveSmsCodeEvent event) {
        Log.d("mySms", " in Activity listener " + event.code);
        editText.setText(event.code);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Subscribe
    public void onGetResponseFromRegistrationRequest (Account.RegisterWithPhoneNumberResponse response){
        Log.d("retroLog", "on Response");
        if (response.didSucceed()){
            Log.d("retroLog", "on respone OK");
            response.showErrorToast(this);
        } else {
            Log.d("retroLog", "Response new message: " + response.getError());
        }
    }
}
