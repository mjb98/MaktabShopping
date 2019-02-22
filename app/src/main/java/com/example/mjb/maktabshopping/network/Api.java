package com.example.mjb.maktabshopping.network;



import java.util.List;

import com.example.mjb.maktabshopping.model.Category;
import com.example.mjb.maktabshopping.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface Api {

    String allPrducts = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/?consumer_key=ck_00fdf4e3f65c5275d802b412db586ba2cac6835f&consumer_secret=cs_d2571d995db502ea4b04bfae270b92ac447eb8ba";
    String orderByRateList = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products?consumer_key=ck_04fd3a34f60353c1005af7eb4b9e9efe93d620a6&consumer_secret=cs_291ecd9c9f483bf21e0cdbc79aca3d0afc1fede7&orderby=rating";
    String orderByPopularity = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products?consumer_key=ck_04fd3a34f60353c1005af7eb4b9e9efe93d620a6&consumer_secret=cs_291ecd9c9f483bf21e0cdbc79aca3d0afc1fede7&orderby=popularity";
    String getAllcategories = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/products/categories/?consumer_key=ck_00fdf4e3f65c5275d802b412db586ba2cac6835f&consumer_secret=cs_d2571d995db502ea4b04bfae270b92ac447eb8ba";

    @GET
    Call<List<Product>> getProductList(@Url String url);

    @GET("products/{id}?consumer_key=ck_04fd3a34f60353c1005af7eb4b9e9efe93d620a6&consumer_secret=cs_291ecd9c9f483bf21e0cdbc79aca3d0afc1fede7")
    Call<Product> getProduct(@Path("id") Integer id);

    @GET
    Call<List<Category>> getCategory(@Url String url);
}
