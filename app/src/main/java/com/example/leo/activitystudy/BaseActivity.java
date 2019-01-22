package com.example.leo.activitystudy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {

    private ForceLogoutReceiver forceLogoutReceiver;
    @Override
    protected void onCreate(Bundle savedInstantceState)
    {
        super.onCreate(savedInstantceState);
        Log.d("BaseActivity",getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        ActionBar actionBar =getSupportActionBar();
        if(actionBar !=null)
        {
            actionBar.hide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.leo.activitystudy.ForceLogout");
        forceLogoutReceiver = new ForceLogoutReceiver();
        registerReceiver(forceLogoutReceiver,intentFilter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(forceLogoutReceiver !=null)
        {
            unregisterReceiver(forceLogoutReceiver);
            forceLogoutReceiver= null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceLogoutReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Waring");
            builder.setMessage("you are logout ,please try login again");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent = new Intent(context,FirstActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
