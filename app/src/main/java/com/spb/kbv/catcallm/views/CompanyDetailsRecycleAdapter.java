package com.spb.kbv.catcallm.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.viewbadger.BadgeView;
import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.activities.ChatActivity;
import com.spb.kbv.catcallm.activities.CompanyInfoActivity;
import com.spb.kbv.catcallm.activities.ScrollingCompanyProfileActivity;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetailsRecycleAdapter extends RecyclerView.Adapter<CompanyDetailsRecycleAdapter.ViewHolder>{

    public List<UserDetails> detailsArray;
    private Context context;
    private CatcallApplication application;

    public CompanyDetailsRecycleAdapter(BaseActivity activity, List<UserDetails> detailsArray) {
        context = activity.getApplicationContext();
        application = (CatcallApplication) activity.getApplication();
        this.detailsArray = detailsArray;
    }

    @Override
    public CompanyDetailsRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_company_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CompanyDetailsRecycleAdapter.ViewHolder viewHolder, final int position) {
        final UserDetails details = detailsArray.get(position);
        Log.d("addAd", "in onBind " + position);
        viewHolder.companyNameTextView.setText(details.getUsername());
        Picasso.with(context).load(details.getAvatarUrl()).into(viewHolder.avatar);
        viewHolder.actionMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Context context = v.getContext();
                PopupMenu popup = new PopupMenu(context, v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    final UserDetails companyDetails = details;

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_action_delete:
                                /*application.getContentResolver().delete(MessagesContract.CompaniesEntry.CONTENT_URI, "_id = ?",
                                        new String[] {String.valueOf(companyDetails.getId())});
                                remove(getItem(position));
                                //todo: delete dialog from database
                                notifyDataSetChanged();*/
                                return true;
                            case R.id.menu_action_chat:
                                Intent intentChat = new Intent(context, ChatActivity.class);
                                intentChat.putExtra(ChatActivity.EXTRA_USER_DETAILS, companyDetails);
                                context.startActivity(intentChat);
                                return true;
                            case R.id.menu_action_contact:
                                return true;
                            case R.id.menu_action_profile:
                                Intent intentProfile = new Intent(context, CompanyInfoActivity.class);
                                intentProfile.putExtra(CompanyInfoActivity.EXTRA_COMPANY_DETAILS, companyDetails);
                                context.startActivity(intentProfile);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.actions);
                popup.show();
            }
        });

        viewHolder.companyNameTextView.setOnClickListener(new View.OnClickListener() {
            final UserDetails companyDetails = details;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScrollingCompanyProfileActivity.class);
                intent.putExtra(ScrollingCompanyProfileActivity.EXTRA_COMPANY_DETAILS, companyDetails);
                v.getContext().startActivity(intent);
            }
        });

        BadgeView badge = new BadgeView(context, viewHolder.avatar);
        badge.setText("5");
        badge.setBadgeMargin(0, 0);
        badge.setBadgeBackgroundColor(Color.MAGENTA);
        badge.show();
    }

    @Override
    public int getItemCount() {
        return detailsArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView companyNameTextView;
        public ImageView avatar;
        public ImageView actionMenuButton;

        public ViewHolder(View view) {
            super(view);
            companyNameTextView = (TextView) view.findViewById(R.id.list_item_company_details_name);
            avatar = (ImageView) view.findViewById(R.id.list_item_company_details_avatar);
            actionMenuButton = (ImageView) view.findViewById(R.id.list_item_company_details_list_item_menu);
        }
    }
}
