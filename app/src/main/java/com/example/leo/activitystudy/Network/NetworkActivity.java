package com.example.leo.activitystudy.Network;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.leo.activitystudy.BaseActivity;
import com.example.leo.activitystudy.R;

public class NetworkActivity extends BaseActivity implements View.OnClickListener {

    private EditText editTextInputUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_layout);
        findViewById(R.id.pbtnOpenWeb).setOnClickListener(this);
        editTextInputUri = (EditText)findViewById(R.id.editTextInputUri);
        findViewById(R.id.pbtnOpenHttp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.pbtnOpenWeb:
                WebActivity.actionStart(this,editTextInputUri.getText().toString());
                break;
            case R.id.pbtnOpenHttp:
                Intent intent= new Intent(this,HttpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
