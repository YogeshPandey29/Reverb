package com.example.reverb.api;

import com.example.reverb.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIHandler {

    @GET
    Call<News> getAllNewsData (@Url String urlString);

    @GET
    Call<News> getFilteredNewsData (@Url String urlString);

}
