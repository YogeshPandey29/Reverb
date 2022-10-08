package com.example.reverb.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.reverb.R;
import com.example.reverb.adapter.CategoryAdapter;
import com.example.reverb.adapter.NewsAdapter;
import com.example.reverb.api.APIHandler;
import com.example.reverb.model.Articles;
import com.example.reverb.model.Category;
import com.example.reverb.model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {

    // 3e457acfe9a444f1b001787e8d41909b

    private RecyclerView categoryRecyclerView;
    private RecyclerView newsRecyclerView;
    private CategoryAdapter categoryAdapter;
    private NewsAdapter newsAdapter;
    private ProgressBar loadingIndicator;

    private List<Articles> articleList;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryRecyclerView = findViewById(R.id.categoriesContainer_RV);
        newsRecyclerView = findViewById(R.id.newsItemContainer_RV);
        loadingIndicator = findViewById(R.id.loading_indicator);

        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL , false));
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        articleList = new ArrayList<>();
        categoryList = new ArrayList<>();

        newsAdapter = new NewsAdapter(articleList, this);
        categoryAdapter = new CategoryAdapter(categoryList, this, this::onCategoryClick);

        categoryRecyclerView.setAdapter(categoryAdapter);
        newsRecyclerView.setAdapter(newsAdapter);

        // calling the method to get the categories data in the arraylist

        getCategoryData();

        // calling the getNewsData method to fetch all the global news

        getNewsData("Global");

        newsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCategoryClick(int categoryElementPosition) {

        String category = categoryList.get(categoryElementPosition).getCategory();
        getNewsData(category);
    }

    private void getCategoryData () {

        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "Global"));
        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "Entertainment"));
        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "General"));
        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "Health"));
        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "Business"));
        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "Technology"));
        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "Science"));
        categoryList.add(new Category("https://images.unsplash.com/photo-1557682250-33bd709cbe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=829&q=80", "Sports"));

        categoryAdapter.notifyDataSetChanged();
    }

    private void getNewsData (String categoryString) {

        loadingIndicator.setVisibility(View.VISIBLE);
        articleList.clear();

        String newsByCategoryUrl = "https://newsapi.org/v2/top-headlines/?country=in&category=" + categoryString + "&sortBy=publishedAt&language=en&apiKey=3e457acfe9a444f1b001787e8d41909b";
        String globalNewsUrl = "https://newsapi.org/v2/top-headlines/?country=in&language=en&apiKey=3e457acfe9a444f1b001787e8d41909b";
        String BASE_URL = "https://newsapi.org/";

        Retrofit retrofitAPIHandler = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        APIHandler apiHandler = retrofitAPIHandler.create(APIHandler.class);

        Call<News> call;

        if (categoryString.equals("Global")) {
            call = apiHandler.getAllNewsData(globalNewsUrl);
        }
        else {
            call = apiHandler.getFilteredNewsData(newsByCategoryUrl);
        }

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                News newsResponse = response.body();
                loadingIndicator.setVisibility(View.GONE);

                List<Articles> articles = newsResponse.getArticles();

                for (int i=0; i<articles.size(); i++) {
                    articleList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(), articles.get(i).getUrlToImage(), articles.get(i).getUrl(), articles.get(i).getContent()));
                }

                newsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went Wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}