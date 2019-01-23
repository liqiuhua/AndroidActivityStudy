package com.example.leo.activitystudy;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThreeActivity extends BaseActivity implements View.OnClickListener {

    private Button pbtnPhoneCall;
    private EditText editTextPhoneNum;
    private Button pbtnReturnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_layout);
        pbtnPhoneCall = (Button)findViewById(R.id.pbtnCallPhone);
        editTextPhoneNum = (EditText)findViewById(R.id.editTextPhoneNum);
        pbtnPhoneCall.setOnClickListener(this);
        pbtnReturnCall =(Button)findViewById(R.id.pbtnReturnCall);
        pbtnReturnCall.setOnClickListener(this);

    }
    private void call()
    {
        try
        {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+editTextPhoneNum.getText().toString()));
            startActivity(intent);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.pbtnCallPhone:
            {
                if(ContextCompat.checkSelfPermission(ThreeActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(ThreeActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

                }
                else
                {
                    if(!editTextPhoneNum.getText().toString().isEmpty())
                        call();
                    else
                    {
                        Toast.makeText(this,"please input phone number",Toast.LENGTH_SHORT).show();
                    }
                }
            }
                break;
            case R.id.pbtnReturnCall:
            {
                if(ContextCompat.checkSelfPermission(ThreeActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                   // ActivityCompat.requestPermissions(ThreeActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                    Toast.makeText(this,"you denied the permission",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    try
                    {
                        Intent intent = new Intent(Intent.ACTION_CALL_BUTTON);
                        startActivity(intent);
                    }
                    catch (SecurityException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(editTextPhoneNum.getText().toString()!=null)
                        call();
                }
                else
                {
                    Toast.makeText(this,"you denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:break;
        }
    }
}
