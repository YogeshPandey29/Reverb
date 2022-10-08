package com.example.reverb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reverb.R;
import com.example.reverb.controller.DetailActivity;
import com.example.reverb.controller.MainActivity;
import com.example.reverb.model.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Articles> articlesList;
    private Context context;

    public NewsAdapter (List<Articles> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        Articles newsArticle = articlesList.get(position);

        holder.newsTitleTextview.setText(newsArticle.getTitle());
        holder.newsContentTextview.setText(newsArticle.getContent());

        Picasso.get().load(newsArticle.getUrlToImage()).into(holder.newsImageview);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent detailedNewsIntent = new Intent(context, DetailActivity.class);

                detailedNewsIntent.putExtra("title", newsArticle.getTitle());
                detailedNewsIntent.putExtra("description", newsArticle.getDescription());
                detailedNewsIntent.putExtra("urlToImage", newsArticle.getUrlToImage());
                detailedNewsIntent.putExtra("url", newsArticle.getUrl());
                detailedNewsIntent.putExtra("content", newsArticle.getContent());

                context.startActivity(detailedNewsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitleTextview;
        private TextView newsContentTextview;
        private ImageView newsImageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsTitleTextview = itemView.findViewById(R.id.newsHeading_textView);
            newsContentTextview = itemView.findViewById(R.id.newsContent_textView);
            newsImageview = itemView.findViewById(R.id.news_imageView);
        }
    }
}
