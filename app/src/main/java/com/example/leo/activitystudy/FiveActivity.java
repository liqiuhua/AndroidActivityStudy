package com.example.leo.activitystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class FiveActivity extends BaseActivity {

    private List<Msg> msgsList = new ArrayList<>();
    private EditText inputTextMsg;
    private Button pbtnSendMsg;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_layout);
        initMsgs();
        inputTextMsg = (EditText) findViewById(R.id.inputMsg);
        pbtnSendMsg = (Button)findViewById(R.id.ptnSendMsg);
        msgRecyclerView = (RecyclerView)findViewById(R.id.messageRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgsList);
        msgRecyclerView.setAdapter(adapter);
        pbtnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputTextMsg.getText().toString();
                if(!" ".equals(content))
                {
                    Msg msg = new Msg(content,Msg.TYPE_SEND);
                    msgsList.add(msg);
                    adapter.notifyItemInserted(msgsList.size()-1);
                    msgRecyclerView.scrollToPosition(msgsList.size()-1);
                    Msg msg2 = new Msg(content,Msg.TYPE_RECEIVED);
                    msgsList.add(msg2);
                    adapter.notifyItemInserted(msgsList.size()-1);
                    msgRecyclerView.scrollToPosition(msgsList.size()-1);
                    inputTextMsg.setText("");
                }
            }
        });

    }
    private void initMsgs(){
        Msg msg_1 = new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgsList.add(msg_1);
        Msg msg_2 = new Msg("hello who is that?",Msg.TYPE_SEND);
        msgsList.add(msg_2);
    }
}
