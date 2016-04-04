package com.spb.kbv.catcallm.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.Utils.DateFormats;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.squareup.picasso.Picasso;

public class DialogsAdapter extends ArrayAdapter<Message> {

    private LayoutInflater inflater;
    private Context context;

    public DialogsAdapter(BaseActivity activity) {
        super(activity, 0);
        context = activity.getApplicationContext();
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        Message message = getItem(position);
        UserDetails details = message.getOtherUser();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_dialogs_details, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.companyNameTextView.setText(details.getUsername());
        Picasso.with(context).load(details.getAvatarUrl()).into(viewHolder.avatar);
        viewHolder.companyAddressTextView.setText(details.getAddress());
        viewHolder.lastMessageTextView.setText(message.getMessageText());
        Log.d("dialogadap", "last message = " + message.getMessageText());

        if (message.isRead()){
            //todo change null to real sign
            viewHolder.status.setImageResource(R.drawable.ic_action_navigation_menu);
        } else {
            viewHolder.status.setImageResource(R.mipmap.ic_message_unread);
        }

        /*viewHolder.lastMessageDateTextView.setText(DateFormats.showTimeAndDayFormat(message.getCratedAt()));*/
        return convertView;
    }

    public class ViewHolder {
        public TextView companyNameTextView;
        public TextView companyAddressTextView;
        public TextView lastMessageTextView;
        public TextView lastMessageDateTextView;
        public ImageView status;
        public ImageView avatar;

        public ViewHolder(View view) {
            companyNameTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_name);
            companyAddressTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_address);
            lastMessageTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_last_message);
            lastMessageDateTextView = (TextView) view.findViewById(R.id.list_item_dialogs_details_date);
            avatar = (ImageView) view.findViewById(R.id.list_item_dialogs_details_avatar);
            status = (ImageView) view.findViewById(R.id.list_item_company_details_status);

        }
    }
}