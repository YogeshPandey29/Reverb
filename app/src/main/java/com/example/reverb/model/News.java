package com.example.reverb.model;

import java.util.List;

public class News {

    // class variables for News class

    private String status;
    private int totalResults;
    private List<Articles> articles;

    // public constructor for News class

    public News (String status, int totalResults, List<Articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    // getter and setter methods for the class variables

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
