package com.spb.kbv.catcallm.views;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.Utils.DateFormats;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.services.entities.Message;

import java.util.ArrayList;

public class MessageListAdapter extends BaseAdapter{

    private final BaseActivity activity;
    private final LayoutInflater layoutInflater;
    private ArrayList<Message> messages;

    public MessageListAdapter(BaseActivity activity, ArrayList<Message> messages) {
        this.activity = activity;
        this.messages = messages;
        layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = messages.get(position);
        if (message.isFromUs()){
            convertView = layoutInflater.inflate(R.layout.list_item_message_client, null);
        } else {
            convertView = layoutInflater.inflate(R.layout.list_item_message_from, null);
        }

        TextView messageFrom = (TextView) convertView.findViewById(R.id.message_from);
        TextView messageText = (TextView) convertView.findViewById(R.id.message_text);
        TextView messageDate = (TextView) convertView.findViewById(R.id.message_date);

        if (message.isFromUs()){
            messageFrom.setVisibility(View.GONE);
        } else {
            messageFrom.setText(message.getOtherUser().getUsername());
        }

        messageText.setText(message.getMessageText());
/*
        String createdAt = DateUtils.formatDateTime(
                activity.getApplicationContext(),
                message.getCratedAt().getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME);*/

        /*messageDate.setText(DateFormats.showTimeFormat(message.getCratedAt()));*/

        return convertView;
    }
}
