package com.example.leo.activitystudy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.leo.activitystudy.News.News;
import com.example.leo.activitystudy.News.NewsContentFragment;
import com.example.leo.activitystudy.fragment.Right_2_Fragment;

import java.util.prefs.BackingStoreException;

public class SevenActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_layout);
    }
}