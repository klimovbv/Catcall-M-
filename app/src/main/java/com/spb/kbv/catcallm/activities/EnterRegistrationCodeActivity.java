package com.spb.kbv.catcallm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spb.kbv.catcallm.R;

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
        if (editText.getText().toString().equals("3333")){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            editText.setError("Wrong code");
        }
    }
}
