package com.example.leo.activitystudy.News;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leo.activitystudy.NewsContentActivity;
import com.example.leo.activitystudy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;
    Configuration mConfiguration;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.news_title_fragment,container,false);
        mConfiguration = this.getResources().getConfiguration();
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNewsTitle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }
    private List<News> getNews()
    {
        List<News> newsList = new ArrayList<>();
        for (int i=1;i<50;i++)
        {
            News news = new News();
            news.setTitle("This is news title "+ i);
            news.setContent(getRandomLengthContent("This is news content "+ i +"."));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content)
    {
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++)
        {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int ori = mConfiguration.orientation;
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            isTwoPane= true;
        }
        else {
            isTwoPane=false;
        }
    }
    class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView newsTitleText;

            public ViewHolder(View view)
            {
                super(view);
                newsTitleText = (TextView)view.findViewById(R.id.textViewNewsTitleItem);
            }
        }
        public NewsAdapter(List<News> newsList)
        {
            mNewsList = newsList;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.news_item),parent,false);
             final ViewHolder holder = new ViewHolder(view);
             view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  News news = mNewsList.get(holder.getAdapterPosition());
                  if(isTwoPane)
                  {

                     NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.fragmentNewsContent);
                     newsContentFragment.refresh(news.getTitle(),news.getContent());
                  }
                  else
                      {
                          NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                      }
              }
          });
            return holder;
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        public void onBindNewsTitle(ViewHolder holder, int position)
        {
            News news =mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ViewHolder)
            {
                onBindNewsTitle((ViewHolder) holder,position);
            }
        }
    }
}
