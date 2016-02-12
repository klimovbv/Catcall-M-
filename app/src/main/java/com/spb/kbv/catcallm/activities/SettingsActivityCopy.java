package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.views.MainNavDrawer;

public class SettingsActivityCopy extends BaseAuthenticatedActivity {

    private ImageView mAvatarView;
    private TextView mUsername;
    private Button mChangeSettingsButton;

    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);
        setNavDrawer(new MainNavDrawer(this));

        mAvatarView = (ImageView) findViewById(R.id.activity_settings_avatar);
        mUsername = (TextView) findViewById(R.id.activity_settings_username);
        mChangeSettingsButton = (Button) findViewById(R.id.activity_settings_changeprofile_button);

        mChangeSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
