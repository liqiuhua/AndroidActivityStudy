package com.example.leo.activitystudy.Network;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.activitystudy.BaseActivity;
import com.example.leo.activitystudy.R;

public class HttpActivity extends BaseActivity {

    private TextView textViewHttpResponse;
    private EditText editTextHttpUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        editTextHttpUrl = (EditText)findViewById(R.id.editTextHttpUrl);
        textViewHttpResponse = (TextView)findViewById(R.id.textViewHttpResponse);
        findViewById(R.id.pbtnOpenHttpUrl).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.pbtnOpenHttpUrl:
                    {
                        String editUrl=editTextHttpUrl.getText().toString();
                        String url=null;
                        if(editUrl.length()>0)
                        {
                            if(!editUrl.startsWith("http://"))
                            {
                                url= "http://"+editUrl;
                            }
                            Toast.makeText(HttpActivity.this,url,Toast.LENGTH_SHORT).show();
                            HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {

                                @Override
                                public void onFinish(String response) {
                                    if(response.length()>0) {

                                        textViewHttpResponse.setText(response);
                                    }
                                    else {
                                        textViewHttpResponse.setText("error");
                                    }
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        }

                    }
                    break;
                }
            }
        });
    }

}
