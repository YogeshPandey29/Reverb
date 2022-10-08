package com.example.reverb.model;

public class Category {

    // class variables for the Category class

    private String categoryImageUrl;
    private String category;

    // public constructor for the Category class

    public Category (String categoryImageUrl, String category) {
        this.categoryImageUrl = categoryImageUrl;
        this.category = category;
    }

    // getter and setter methods for the class variables

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
