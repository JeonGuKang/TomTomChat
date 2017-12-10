package com.strongbulb.tomtomchat.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.strongbulb.tomtomchat.R;
import com.strongbulb.tomtomchat.activity.adapter.ChatAdapter;

import java.util.ArrayList;

import model.ChatData;

/**
 * Created by Kangjeongu on 2017. 12. 9..
 */

public class ChatActivity extends AppCompatActivity {

    private Button send;
    private EditText message;
    private RecyclerView rv;
    private ChatAdapter chatAdapter;
    private ArrayList<ChatData> list;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.activity_main_btn_send);
        message = findViewById(R.id.activity_main_et_message);
        rv  = findViewById(R.id.activity_main_rv_chat);
        chatAdapter = new ChatAdapter(this, chatClickListener);
        setRecyclerView(this, rv, LinearLayout.VERTICAL, -1);
        rv.setAdapter(chatAdapter);
        list = new ArrayList<>();
        chatAdapter.setList(list);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatData chatData = new ChatData("user", message.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
                databaseReference.child("1").child("message").push().setValue(chatData);
                message.setText("");
            }
        });
        databaseReference.child("1").child("message").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
                chatAdapter.addList(new ChatData(dataSnapshot.getKey() , chatData.getUserName(),chatData.getMessage()));  // adapter에 추가합니다.
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

    public static void setRecyclerView(Context mContext, RecyclerView recyclerView, int orientation, int dividerColor) {
        if (recyclerView != null) {
            final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, orientation, false);
            recyclerView.setLayoutManager(layoutManager);
            if (dividerColor > -1) {
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, orientation);
                dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mContext, dividerColor));
                recyclerView.addItemDecoration(dividerItemDecoration);
            }
        }
    }

    ChatAdapter.ChatClickListener chatClickListener = new ChatAdapter.ChatClickListener() {
        @Override
        public void OnClickLisener(ChatData data) {

        }

        @Override
        public void LongClickListener(ChatData data) {
            databaseReference.child("1").child("message").child(data.getKey()).removeValue();
        }
    };

}
