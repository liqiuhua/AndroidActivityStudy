package com.example.leo.activitystudy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leo.activitystudy.News.NewsContentFragment;

public class NewsContentActivity extends BaseActivity {

    public static void actionStart(Context context, String newsTitle, String newsContent)
    {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content_layout);
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
       NewsContentFragment newsContentFragment = (NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentNewsContent);
       newsContentFragment.refresh(newsTitle,newsContent);
    }
}
