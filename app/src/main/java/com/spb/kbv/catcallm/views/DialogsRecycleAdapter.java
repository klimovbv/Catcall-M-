package com.spb.kbv.catcallm.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.Utils.DateFormats;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.activities.ChatActivity;
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DialogsRecycleAdapter extends RecyclerView.Adapter<DialogsRecycleAdapter.ViewHolder>{

    public ArrayList<Message> messagesArray;
    private Context context;

    public DialogsRecycleAdapter(BaseActivity activity, ArrayList<Message> messagesArray) {
        context = activity.getApplicationContext();
        this.messagesArray = messagesArray;
    }

    @Override
    public DialogsRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("addAd", "in Dialog onCreateHolder ");
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dialogs_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DialogsRecycleAdapter.ViewHolder viewHolder, final int position) {
        Log.d("addAd", " in Dialog onBind " + position);
        Message message = messagesArray.get(position);
        final UserDetails details = message.getUserdetails();

        viewHolder.companyNameTextView.setText(details.getUsername());
        Picasso.with(context).load(details.getAvatarUrl()).into(viewHolder.avatar);
        viewHolder.companyAddressTextView.setText(details.getAddress());
        viewHolder.lastMessageTextView.setText(message.getMessageText());

        /*Log.d("dialogadap", "last message = " + message.getMessageText());*/

       /* if (message.isRead()){
            //todo change null to real sign
            viewHolder.status.setImageResource(R.drawable.ic_action_navigation_menu);
        } else {
            viewHolder.status.setImageResource(R.mipmap.ic_message_unread);
        }*/

        viewHolder.lastMessageDateTextView.setText(DateFormats.showTimeAndDayFormat(message.getCreatedAt()));

        viewHolder.backGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra(ChatActivity.EXTRA_USER_DETAILS, details);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messagesArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView companyNameTextView;
        public TextView companyAddressTextView;
        public TextView lastMessageTextView;
        public TextView lastMessageDateTextView;
        public ImageView status;
        public ImageView avatar;
        public View backGround;

        public ViewHolder(View view) {
            super(view);
            companyNameTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_name);
            companyAddressTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_address);
            lastMessageTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_last_message);
            lastMessageDateTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_date);
            avatar = (ImageView) view.findViewById(R.id.list_item_dialogs_details_avatar);
            status = (ImageView) view.findViewById(R.id.list_item_company_details_status);
            backGround = view.findViewById(R.id.list_item_dialogs_details_background);
        }
    }
}
