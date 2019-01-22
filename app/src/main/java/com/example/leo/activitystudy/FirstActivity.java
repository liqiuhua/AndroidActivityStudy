package com.example.leo.activitystudy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leo.activitystudy.Login.LoginActivity;

public class FirstActivity extends BaseActivity implements View.OnClickListener {

    private EditText editText;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button gotoSecondActivity = (Button) findViewById(R.id.gotoSecondActivity);

        editText = (EditText) findViewById(R.id.inputData);
        gotoSecondActivity.setOnClickListener(this);
        ((Button)findViewById(R.id.gotoThreeActivity)).setOnClickListener(this);
        ((Button)findViewById(R.id.gotFourActivity)).setOnClickListener(this);
        ((Button)findViewById(R.id.gotFiveActivity)).setOnClickListener(this);
        ((Button)findViewById(R.id.gotoSixActivity)).setOnClickListener(this);
        ((Button)findViewById(R.id.gotoSevenActivity)).setOnClickListener(this);
        ((Button)findViewById(R.id.gotoTestActivity)).setOnClickListener(this);
        ((Button)findViewById(R.id.gotoBroadcastActivity)).setOnClickListener(this);
        ((Button)findViewById(R.id.gotoLoginActivity)).setOnClickListener(this);
    //    ((Button)findViewById(R.id.gotoThreeActivity)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.gotoSecondActivity:
                String Data = "Hello SecondActivity";
                /*活动间传递数据方法一
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                intent.putExtra("FirstActivityData",Data);
                startActivity(intent);
                */
                // 活动间传递数据方法二
                if(editText.getText()!=null) {
                    Toast.makeText(FirstActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    SecondActivity.actionStart(FirstActivity.this, editText.getText().toString());
                }
                break;
            case R.id.gotoThreeActivity:
                Toast.makeText(FirstActivity.this, "gotoThreeActivity", Toast.LENGTH_SHORT).show();
                intent= new Intent(FirstActivity.this,ThreeActivity.class);
                startActivity(intent);
                break;
            case R.id.gotFourActivity:
                intent= new Intent(FirstActivity.this,FourActivity.class);
                startActivity(intent);
                break;
            case R.id.gotFiveActivity:
                intent= new Intent(FirstActivity.this,FiveActivity.class);
                startActivity(intent);
                break;
            case R.id.gotoSixActivity:
                intent= new Intent(FirstActivity.this,SixActivity.class);
                startActivity(intent);
                break;
            case R.id.gotoSevenActivity:
                intent= new Intent(FirstActivity.this,SevenActivity.class);
                startActivity(intent);
                break;
            case R.id.gotoBroadcastActivity:
                intent= new Intent(FirstActivity.this,BroadcastActivity.class);
                startActivity(intent);
                break;
            case R.id.gotoTestActivity:
                intent= new Intent(FirstActivity.this,BroadcastActivity.class);
                startActivity(intent);
                break;
            case R.id.gotoLoginActivity:
                intent= new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
                default:break;
        }
    }

    //        gotoSecondActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String Data = "Hello SecondActivity";
//
//                /*活动间传递数据方法一
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                intent.putExtra("FirstActivityData",Data);
//                startActivity(intent);
//                */
//                // 活动间传递数据方法二
//                if(editText.getText()!=null) {
//                    Toast.makeText(FirstActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
//                    SecondActivity.actionStart(FirstActivity.this, editText.getText().toString());
//                }
//            }
//        });
//        Button gotoThreeActivity = (Button)findViewById(R.id.gotoThreeActivity);
//        gotoThreeActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(FirstActivity.this,ThreeActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button gotoFourActivity = (Button)findViewById(R.id.gotFourActivity);
//        gotoFourActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(FirstActivity.this,FourActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button gotoFiveActivity = (Button)findViewById(R.id.gotFiveActivity);
//        gotoFiveActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(FirstActivity.this,FiveActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button gotoSixActivity = (Button)findViewById(R.id.gotoSixActivity);
//        gotoSixActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(FirstActivity.this,SixActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button gotoSevenActivity = (Button)findViewById(R.id.gotoSevenActivity);
//        gotoSevenActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(FirstActivity.this,SevenActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button gotoTestActivity = (Button)findViewById(R.id.gotoTestActivity);
//        gotoTestActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NewsContentActivity.actionStart(FirstActivity.this,"hello ","world");
//            }
//        });


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,"You select the add item，李秋华",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,"You select the remove item",Toast.LENGTH_SHORT).show();
                break;
                default:

                    break;
        }
        return true;
    }
}
