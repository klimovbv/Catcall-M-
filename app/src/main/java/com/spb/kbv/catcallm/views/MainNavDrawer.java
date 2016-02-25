package com.spb.kbv.catcallm.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.activities.SearchActivity;
import com.spb.kbv.catcallm.activities.SelectCompanyActivity;
import com.spb.kbv.catcallm.activities.SettingsActivity;
import com.spb.kbv.catcallm.infrastructure.User;

public class MainNavDrawer extends NavDrawer{
    private final TextView displayNameText;
    private final ImageView avatarImage;

    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(
                SelectCompanyActivity.class,
                "New Dialog",
                null,
                R.drawable.ic_launcher,
                R.id.include_main_nav_drawer_topItems));

        addItem(new ActivityNavDrawerItem(
                SettingsActivity.class,
                "Settings",
                null,
                R.drawable.ic_launcher,
                R.id.include_main_nav_drawer_topItems));

        addItem(new ActivityNavDrawerItem(SearchActivity.class,
                "Search",
                null,
                R.drawable.ic_launcher,
                R.id.include_main_nav_drawer_topItems));


        addItem(new BasicNavDrawerItem(
                "Logout",
                null,
                R.drawable.ic_launcher,
                R.id.include_main_nav_drawer_bottomItems) {
            @Override
            public void onClick(View v) {
                activity.getCatcallApplication().getAuth().logout();
            }
        });

        displayNameText = (TextView)navDrawerView.findViewById(R.id.include_main_nav_drawer_displayName);
        avatarImage = (ImageView)navDrawerView.findViewById(R.id.include_main_nav_drawer_avatar);

        User loggedInUser = activity.getCatcallApplication().getAuth().getUser();
        displayNameText.setText(loggedInUser.getUserName());
        if (loggedInUser.getAvatarUrl() != null && !loggedInUser.getAvatarUrl().isEmpty()) {
            activity.getCatcallApplication().getAuthedPicasso().load(loggedInUser.getAvatarUrl()).into(avatarImage);
        }
    }

    /*@Subscribe
    public void onUserDetailUpdate(Account.UserDetailsUpdateEvent event){
        Picasso.with(activity).load(event.user.getAvatarUrl()).into(avatarImage);
        displayNameText.setText(event.user.getDisplayName());

    }*/
}
