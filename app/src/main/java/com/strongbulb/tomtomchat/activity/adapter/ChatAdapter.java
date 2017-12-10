package com.strongbulb.tomtomchat.activity.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strongbulb.tomtomchat.R;
import com.strongbulb.tomtomchat.activity.adapter.viewholder.ChatViewholder;

import java.util.ArrayList;

import model.ChatData;

/**
 * Created by Kangjeongu on 2017. 12. 9..
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<ChatData> list;
    private ChatClickListener chatClickListener;

    public ChatAdapter(Activity activity, ChatClickListener chatClickListener) {
        this.activity = activity;
        this.chatClickListener = chatClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(activity).inflate(R.layout.item_chat, parent, false);
        return new ChatViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ChatViewholder) {
            ((ChatViewholder) holder).bindData(list.get(position));
            ((ChatViewholder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    chatClickListener.LongClickListener(list.get(position));
                    list.remove(position);
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<ChatData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(ChatData data) {
        list.add(data);
        notifyItemChanged(list.size()-1);
    }

    public interface ChatClickListener {
        void OnClickLisener(ChatData data);
        void LongClickListener(ChatData data);
    }

}
