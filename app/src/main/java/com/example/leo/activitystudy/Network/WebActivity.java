package com.example.leo.activitystudy.Network;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.leo.activitystudy.BaseActivity;
import com.example.leo.activitystudy.R;

public class WebActivity extends BaseActivity {

    public static void actionStart(Context context,String Data)
    {
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("inputUri",Data);
        context.startActivity(intent);
    }
    private WebView webView;
    private EditText editTextUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView= (WebView)findViewById(R.id.web_View);
        webView.getSettings().setJavaScriptEnabled(true);

        editTextUrl = (EditText)findViewById(R.id.editTextUrl);

        String inputData = getIntent().getStringExtra("inputUri");
        String url=null;
        if(inputData.length()>0)
        {
            if(!inputData.startsWith("http://"))
            {
                url= "http://"+inputData;
            }
            OpenUrl(url);
        }
        findViewById(R.id.pbtnOpenUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.pbtnOpenUrl:
                    {
                       String editUrl=editTextUrl.getText().toString();
                       String url=null;
                        if(editUrl.length()>0)
                        {
                            if(!editUrl.startsWith("http://"))
                            {
                                url= "http://"+editUrl;
                            }
                            OpenUrl(url);
                        }
                    }
                    break;
                }
            }
        });
    }
    private void OpenUrl(String url)
    {
        editTextUrl.setText(url);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
