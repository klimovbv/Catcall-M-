package com.spb.kbv.catcallm.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.activities.ChatActivity;
import com.spb.kbv.catcallm.activities.CompanyInfoActivity;
import com.spb.kbv.catcallm.activities.ScrollingCompanyProfileActivity;
import com.spb.kbv.catcallm.data.MessagesContract;
import com.spb.kbv.catcallm.infrastructure.CatcallApplication;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.picasso.Picasso;

public class CompanyDetailsAdapter extends ArrayAdapter<UserDetails> {

    private LayoutInflater inflater;
    private Context context;
    private CatcallApplication application;

    public CompanyDetailsAdapter(BaseActivity activity) {
        super(activity, 0);
        inflater = activity.getLayoutInflater();
        context = activity.getApplicationContext();
        application = activity.getCatcallApplication();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        final UserDetails details = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_company_details, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

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
                                application.getContentResolver().delete(MessagesContract.CompaniesEntry.CONTENT_URI, "_id = ?",
                                        new String[] {String.valueOf(companyDetails.getId())});
                                remove(getItem(position));
                                //todo: delete dialog from database
                                notifyDataSetChanged();
                                return true;
                            case R.id.menu_action_chat:
                                companyDetails.save();
                                Intent intentChat = new Intent(context, ChatActivity.class);
                                intentChat.putExtra(ChatActivity.EXTRA_USER_DETAILS, companyDetails);
                                context.startActivity(intentChat);
                                return true;
                            case R.id.menu_action_contact:
                                return true;
                            case R.id.menu_action_profile:
                                /*Intent intentProfile = new Intent(context, CompanyInfoActivity.class);
                                intentProfile.putExtra(CompanyInfoActivity.EXTRA_COMPANY_DETAILS, companyDetails);
                                context.startActivity(intentProfile);*/
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
                intent.putExtra(CompanyInfoActivity.EXTRA_COMPANY_DETAILS, companyDetails);
                v.getContext().startActivity(intent);
            }
        });


        return convertView;
    }

    public class ViewHolder {
        public TextView companyNameTextView;
        public ImageView avatar;
        public ImageView actionMenuButton;

        public ViewHolder(View view) {
            companyNameTextView = (TextView) view.findViewById(R.id.list_item_company_details_name);
            avatar = (ImageView) view.findViewById(R.id.list_item_company_details_avatar);
            actionMenuButton = (ImageView) view.findViewById(R.id.list_item_company_details_list_item_menu);
        }
    }
}
