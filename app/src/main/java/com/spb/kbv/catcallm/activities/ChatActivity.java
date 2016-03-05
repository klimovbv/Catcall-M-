package com.spb.kbv.catcallm.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.spb.kbv.catcallm.R;
import com.spb.kbv.catcallm.services.InMemoryMessagesService;
import com.spb.kbv.catcallm.services.Messages;
import com.spb.kbv.catcallm.services.entities.Message;
import com.spb.kbv.catcallm.services.entities.UserDetails;
import com.spb.kbv.catcallm.views.MainNavDrawer;
import com.spb.kbv.catcallm.views.MessageListAdapter;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatActivity extends BaseAuthenticatedActivity implements View.OnClickListener {

    public static final String EXTRA_USER_DETAILS = "EXTRA_USER_DETAILS";
    private Button mSendButton;
    private EditText mMessageEditText;
    private MessageListAdapter mAdapter;
    private ArrayList<Message> mMessages;
    private ListView mMessagesLisView;
    private UserDetails details;

    private ImageView companyAvatar;
    private TextView companyName;

    @Override
    protected void onCatcallAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chat);
        /*setNavDrawer(new MainNavDrawer(this));*/

        details = getIntent().getParcelableExtra(EXTRA_USER_DETAILS);

        toolbar.setNavigationIcon(R.drawable.ic_action_back_in_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        companyAvatar = (ImageView) toolbar.findViewById(R.id.list_item_company_details_avatar);
        companyName = (TextView) toolbar.findViewById(R.id.list_item_company_details_name);

        Picasso.with(this).load(details.getAvatarUrl()).into(companyAvatar);
        companyName.setText(details.getUsername());

        mSendButton = (Button) findViewById(R.id.activity_chat_send_button);
        mMessageEditText = (EditText) findViewById(R.id.activity_chat_message_editText);
        mMessagesLisView = (ListView) findViewById(R.id.activity_chat_messages_list);
        mMessages = new ArrayList<>();

        mAdapter = new MessageListAdapter(this, mMessages);
        mMessagesLisView.setAdapter(mAdapter);

        mSendButton.setOnClickListener(this);

        bus.post(new Messages.LoadMessagesRequest(details.getId(), details));

    }

    @Subscribe
    public void onMessagesLoaded(Messages.LoadMessagesResponse response){
        mMessages.addAll(response.messages);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        bus.post(new Messages.SendMessageRequest(mMessageEditText.getText().toString(), details.getId()));
        mMessageEditText.setText("");
    }

    @Subscribe
    public void onSendMessageDeliveredAction(Messages.SendMessageResponse response){
        Log.d("myLogs", "in onSendMessageDeliveredAction " + response.message.getMessageText());
        mMessages.add(response.message);
        mAdapter.notifyDataSetChanged();
        bus.post(new Messages.ReceiveIncomeMessageRequest(details));
    }

    @Subscribe
    public void onNewMessageReceivedAction(Messages.ReceiveIncomeMessageResponse response){
        mMessages.add((response.message));
        mAdapter.notifyDataSetChanged();
    }
}
