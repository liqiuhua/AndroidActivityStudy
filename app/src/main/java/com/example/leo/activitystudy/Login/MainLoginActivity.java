package com.example.leo.activitystudy.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leo.activitystudy.BaseActivity;
import com.example.leo.activitystudy.R;

public class MainLoginActivity extends BaseActivity implements View.OnClickListener {

        private Button pbtnForceLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        pbtnForceLogout = (Button) findViewById(R.id.pbtnForceLogout);
        pbtnForceLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.pbtnForceLogout:
                Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("com.example.leo.activitystudy.ForceLogout");
                sendBroadcast(intent);
                break;
        }
    }
}
