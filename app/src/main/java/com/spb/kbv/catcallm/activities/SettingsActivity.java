package com.spb.kbv.catcallm.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.soundcloud.android.crop.Crop;
import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.infrastructure.User;
import com.spb.kbv.catcallm.views.MainNavDrawer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends BaseAuthenticatedActivity implements View.OnClickListener {

    private static final short REQUEST_SELECT_IMAGE = 100;

    private ImageView mAvatarView;
    private TextView mUsername;
    private Button mChangeSettingsButton;
    private User user;
    private File tempOutputFile;

    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);
        setNavDrawer(new MainNavDrawer(this));

        tempOutputFile = new File(getExternalCacheDir(), "temp-image.jpg");
        mAvatarView = (ImageView) findViewById(R.id.activity_settings_avatar);
        mUsername = (TextView) findViewById(R.id.activity_settings_username);

        mAvatarView.setOnClickListener(this);
        mUsername.setOnClickListener(this);
        /*mChangeSettingsButton = (Button) findViewById(R.id.activity_settings_changeprofile_button);

        mChangeSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        user = application.getAuth().getUser();

        mUsername.setText(user.getUserName());
        //Todo: set Avatar image


    }


    @Override
    public void onClick(View view) {
        int itemId = view.getId();
        if (itemId == R.id.activity_settings_username) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Change User Name");
            final EditText editName = new EditText(this);
            dialog.setView(editName);
            dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final String enteredName = editName.getText().toString();
                    user.setUserName(enteredName);
                    mUsername.setText(enteredName);
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialog.show();
            return;
        }

        if (itemId == R.id.activity_settings_avatar) {
            List<Intent> otherImageCaptureIntents = new ArrayList<>();
            List<ResolveInfo> otherImageCaptureActivities = getPackageManager()
                    .queryIntentActivities(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);
            for (ResolveInfo info : otherImageCaptureActivities){
                Intent captureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempOutputFile));
                otherImageCaptureIntents.add(captureIntent);
            }

            Intent selectImageIntent = new Intent(Intent.ACTION_PICK);
            selectImageIntent.setType("image/*");

            Intent chooser = Intent.createChooser(selectImageIntent, "Chooser Avatar");
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, otherImageCaptureIntents.toArray(
                    new Parcelable[otherImageCaptureActivities.size()]));

            startActivityForResult(chooser, REQUEST_SELECT_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            tempOutputFile.delete();
            return;
        }

        if (requestCode == REQUEST_SELECT_IMAGE) {
            Uri outputFile;
            Uri tempFileUri = Uri.fromFile(tempOutputFile);

            if (data != null && (data.getAction() == null || !data.getAction().equals(MediaStore.ACTION_IMAGE_CAPTURE))) {
                outputFile = data.getData();
            } else
                outputFile = tempFileUri;

            Crop.of(outputFile, Uri.fromFile(tempOutputFile))
                    .asSquare()
                    .start(this);
        } else if (requestCode == Crop.REQUEST_CROP) {
            //todo add avatar progressframe
            mAvatarView.setImageURI(Uri.fromFile(tempOutputFile));
            // reload navDrawer avatar

        }
    }
}
