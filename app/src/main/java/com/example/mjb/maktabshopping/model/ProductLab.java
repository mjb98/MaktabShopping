package com.example.mjb.maktabshopping.model;

import java.util.List;

public class ProductLab {
    private static final ProductLab ourInstance = new ProductLab();
    private List<Product> allProducts;
    private List<Product> bestSellerProducts;
    private List<Product> popularProducts;
    private List<Category> mCategoryList;

    public List<Product> getBestSellerProducts() {
        return bestSellerProducts;
    }

    public List<Product> getPopularProducts() {
        return popularProducts;
    }

    public static ProductLab getInstance() {
        return ourInstance;
    }

    private ProductLab() {
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setBestSellerProducts(List<Product> bestSellerProducts) {
        this.bestSellerProducts = bestSellerProducts;
    }

    public void setPopularProducts(List<Product> popularProducts) {
        this.popularProducts = popularProducts;
    }

    public List<Category> getCategoryList() {
        return mCategoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        mCategoryList = categoryList;
    }
}
