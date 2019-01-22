package com.example.leo.activitystudy.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.leo.activitystudy.BaseActivity;
import com.example.leo.activitystudy.R;

import static android.preference.PreferenceManager.*;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button pbtnLogin;
    private Button pbtnSignIn;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private SharedPreferences preferences;
    private SharedPreferences.Editor prefEditor;
    private CheckBox checkBoxRememberPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        pbtnLogin =(Button)findViewById(R.id.pbtnLogin);
        pbtnSignIn = (Button)findViewById(R.id.pbtnSignIn);
        pbtnLogin.setOnClickListener(this);
        pbtnSignIn.setOnClickListener(this);
        editTextUsername = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        checkBoxRememberPassword = (CheckBox)findViewById(R.id.checkBoxRememberPassword);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRememberPassword = preferences.getBoolean("remember_password",false);
        if(isRememberPassword)
        {
            String username = preferences.getString("username","");
            String password = preferences.getString("password","");
            editTextUsername.setText(username);
            editTextPassword.setText(password);
            checkBoxRememberPassword.setChecked(true);
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.pbtnLogin:
            {
                    if(editTextPassword.getText().toString().isEmpty() ||editTextUsername.getText().toString().isEmpty())
                    {
                        Toast.makeText(this,"Please input username or password",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(editTextUsername.getText().toString().equals("admin") && editTextPassword.getText().toString().equals("admin"))
                    {
                        prefEditor=preferences.edit();
                        if(checkBoxRememberPassword.isChecked())
                        {
                            prefEditor.putBoolean("remember_password",true);
                            prefEditor.putString("username",editTextUsername.getText().toString());
                            prefEditor.putString("password",editTextPassword.getText().toString());
                        }
                        else
                        {
                            prefEditor.clear();
                        }
                        prefEditor.apply();//特别要注意加上这句
                        Intent intent = new Intent(LoginActivity.this,MainLoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(this,"username or password error",Toast.LENGTH_SHORT).show();
                    }
            }
            break;
            case R.id.pbtnSignIn:
            {

            }
                break;
            default:
                break;
        }
    }
}
