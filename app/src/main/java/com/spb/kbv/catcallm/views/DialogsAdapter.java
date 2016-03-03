package com.spb.kbv.catcallm.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;

public class DialogsAdapter extends ArrayAdapter<UserDetails> {

    private LayoutInflater inflater;

    public DialogsAdapter(BaseActivity activity) {
        super(activity, 0);
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        UserDetails details = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_dialogs_details, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message lastMessage = lastMessage(details);

        viewHolder.companyNameTextView.setText(details.getUsername());
        viewHolder.avatar.setImageResource(R.drawable.ic_launcher);
        viewHolder.companyAddressTextView.setText(details.getAddress());
        viewHolder.lastMessageDateTextView.setText(lastMessage.message);

        if (lastMessage.isRead()){
            //todo change null to real sign
            viewHolder.status.setImageResource(R.drawable.ic_action_navigation_menu);
        } else {
            viewHolder.status.setImageResource(R.drawable.ic_media_play);
        }

        viewHolder.lastMessageDateTextView.setText(lastMessage.getCratedAt().getTime().toString());




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

    private Message lastMessage (UserDetails companyDetail) {
        // todo: query lastMessageId if > 0
    }
}
