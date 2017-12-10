package com.strongbulb.tomtomchat.activity.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.strongbulb.tomtomchat.R;

import model.ChatData;

/**
 * Created by Kangjeongu on 2017. 12. 9..
 */

public class ChatViewholder extends RecyclerView.ViewHolder{

    TextView name;
    TextView message;

    public ChatViewholder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.item_chat_tv_name);
        message = itemView.findViewById(R.id.item_chat_tv_message);
    }

    public void bindData(ChatData chatData) {
        if(chatData != null) {
            name.setText(chatData.getUserName());
            message.setText(chatData.getMessage());
        }



    }
}
