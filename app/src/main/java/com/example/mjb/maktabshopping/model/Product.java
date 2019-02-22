package com.example.mjb.maktabshopping.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {



    private List<Image> images;
    private List<Category> categories;
    private String name;
    private String price;
    @SerializedName("average_rating")
    private Float rate;

    public Product(List<Image> images, List<Category> categories, String name, String price, Float rate) {
        this.images = images;
        this.categories = categories;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}