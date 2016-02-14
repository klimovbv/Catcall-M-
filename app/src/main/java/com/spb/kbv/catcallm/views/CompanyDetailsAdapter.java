package com.spb.kbv.catcallm.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.activities.BaseActivity;
import com.spb.kbv.catcallm.services.entities.UserDetails;

public class CompanyDetailsAdapter extends ArrayAdapter<UserDetails> {

    private LayoutInflater inflater;

    public CompanyDetailsAdapter(BaseActivity activity) {
        super(activity, 0);
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        UserDetails details = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_company_details, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.companyNameTextView.setText(details.getUsername());
        viewHolder.avatar.setImageResource(R.drawable.ic_launcher);


        return convertView;
    }

    public class ViewHolder {
        public TextView companyNameTextView;
        public ImageView avatar;

        public ViewHolder(View view) {
            companyNameTextView = (TextView) view.findViewById(R.id.list_item_company_details_name);
            avatar = (ImageView) view.findViewById(R.id.list_item_company_details_avatar);
        }
    }
}
