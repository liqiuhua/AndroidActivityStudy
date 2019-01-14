package com.example.leo.activitystudy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {

/* 活动间传递数据方法一
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.second_layout);

Intent intent = getIntent();
String Data = intent.getStringExtra("FirstActivityData");
Log.d("SecondActiveLog",Data);



}
*/

//    活动间传递数据方法二
    public static void actionStart(Context context,String Data)
    {
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("Data",Data);
        context.startActivity(intent);
    }
    private TextView textView;
    private ImageView imageView;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstantceState) {
        super.onCreate(savedInstantceState);
        setContentView(R.layout.second_layout);
        textView = (TextView)findViewById(R.id.dispData);
        Intent intent = getIntent();
        String Data = getIntent().getStringExtra("Data");
      //  Toast.makeText(SecondActivity.this,"second Data"+Data, Toast.LENGTH_SHORT).show();
        textView.setText(Data);
        imageView = (ImageView)findViewById(R.id.imageShow);
        Button button = (Button)findViewById(R.id.chageImg);
        Drawable drawable = (Drawable)imageView.getDrawable();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     if(imageView.getId() ==R.drawable.girl1)
                if(i==0)
                {
                    imageView.setImageResource(R.drawable.girl2);
                    i++;
                }
                else
                {
                    i=0;
                    imageView.setImageResource(R.drawable.girl1);
                }

            }
        });
    }

}
