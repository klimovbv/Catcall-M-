package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseAuthenticatedActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (!application.getAuth().getUser().isLoggedIn()){
            if (application.getAuth().hasAuthToken()){
                Intent intent = new Intent(this, AuthenticationActivity.class);
                intent.putExtra(AuthenticationActivity.EXTRA_RETURN_TO_ACTIVITY, getClass().getName());
                startActivity(intent);
            } else {
                startActivity(new Intent(this, RegistrationActivity.class));
            }
            finish();
            return;
        }*/
        onCatcallAppCreate(savedInstanceState);
    }

    protected abstract void onCatcallAppCreate(Bundle savedInstanceState);
}
