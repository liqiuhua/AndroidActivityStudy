package com.example.leo.activitystudy.News;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leo.activitystudy.R;

public class NewsContentFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.news_content_fragment,container,false);
        return view;
    }
    public  void refresh(String newsTitle,String newsContent)
    {
        View visibilityLayout = view.findViewById(R.id.linearLayoutNewsVisibility);
        visibilityLayout.setVisibility(view.VISIBLE);
        TextView newsTitleText = (TextView) view.findViewById(R.id.textViewNewsTitle);
        TextView newsContentText = (TextView)view.findViewById(R.id.textViewNewsContent);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }
}
