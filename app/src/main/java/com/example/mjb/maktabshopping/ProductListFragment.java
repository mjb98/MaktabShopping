package com.example.mjb.maktabshopping;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mjb.maktabshopping.model.Category;
import com.example.mjb.maktabshopping.model.Product;
import com.example.mjb.maktabshopping.model.ProductLab;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjb.maktabshopping.network.Api;
import com.example.mjb.maktabshopping.network.RetrofitClinetInstance;
import com.example.mjb.maktabshopping.utils.numbers;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    private RecyclerView lastProductsRecyvlerView;
    private RecyclerView popularProductsRecyclerView;
    private RecyclerView bestsellProductsRecyclerView;
    private RecyclerView categoryListRecyclerView;
    private ProductsAdapter lastAdapter;
    private ProductsAdapter popularAdapter;
    private ProductsAdapter bestSellerAdapter;
    private CategoryAdapter mCategoryAdapter;
    private ProgressDialog mProgressDialog;
    private ProductLab mProductLab = ProductLab.getInstance();
    private List<Product> mProducts = new ArrayList<>();


    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance() {

        Bundle args = new Bundle();


        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        setRetainInstance(true);
        lastProductsRecyvlerView = view.findViewById(R.id.product_list_recuclerview);
        popularProductsRecyclerView = view.findViewById(R.id.product_list_popularProductsRecyclerView);
        bestsellProductsRecyclerView = view.findViewById(R.id.product_list_bestSellerRecyclerlerview);
        categoryListRecyclerView = view.findViewById(R.id.category_list_recuclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        lastProductsRecyvlerView.setLayoutManager(layoutManager);
        popularProductsRecyclerView.setLayoutManager(layoutManager1);
        bestsellProductsRecyclerView.setLayoutManager(layoutManager2);
        categoryListRecyclerView.setLayoutManager(layoutManager3);
        getLastProducts();
        getBestSellerProducts();
        getPopularProduct();
        getCategories();


        return view;

    }

    private void getLastProducts() {

        if (mProductLab.getAllProducts() == null || mProductLab.getAllProducts().size() == 0) {
//            mProgressDialog = new ProgressDialog(getActivity());
//            mProgressDialog.setMessage("getting products items");
//            mProgressDialog.show();


            RetrofitClinetInstance.getRetrofitClinetInstance().create(Api.class).getProductList(Api.allPrducts).enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        //  mProgressDialog.cancel();
                        mProductLab.setAllProducts(response.body());
                        lastAdapter = new ProductsAdapter(response.body());
                        lastProductsRecyvlerView.setAdapter(lastAdapter);


                    }

                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    if (getActivity() != null)

                        Toast.makeText(getActivity(), "problem with your request", Toast.LENGTH_SHORT).show();
                    //    mProgressDialog.cancel();

                }
            });
        } else {
            mProducts = mProductLab.getAllProducts();
            lastAdapter = new ProductsAdapter(mProducts);
            lastProductsRecyvlerView.setAdapter(lastAdapter);

        }


    }


    private class ProductHolder extends RecyclerView.ViewHolder {
        private ImageView prdouctImageview;
        private TextView productNameTextView;
        private TextView productPriceTextView;
        private Product mProducts;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            prdouctImageview = itemView.findViewById(R.id.product_list_imageview);
            productNameTextView = itemView.findViewById(R.id.product_title_list);
            productPriceTextView = itemView.findViewById(R.id.product_price_list);
        }

        public void bind(Product products) {
            mProducts = products;
            productNameTextView.setText(products.getName());
            productPriceTextView.setText(numbers.convertNuیmber(numbers.splitDigits(Integer.parseInt(products.getPrice()))));
            if (mProducts.getImages() != null && mProducts.getImages().size() > 0) {
                //Toast.makeText(MainActivity.this,"joon",Toast.LENGTH_LONG).show();
                Picasso.get().load(mProducts.getImages().get(0).getPath()).into(prdouctImageview);
            }

        }
    }

    private class ProductsAdapter extends RecyclerView.Adapter<ProductHolder> {
        private List<Product> mList;

        public ProductsAdapter(List<Product> list) {
            mList = list;
        }

        public void setList(List<Product> list) {
            mList = list;
        }

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).
                    inflate(R.layout.product_list_item, parent, false);
            return new ProductHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            Product products = mList.get(position);
            holder.bind(products);

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    private void getPopularProduct() {

        if (mProductLab.getPopularProducts() == null || mProductLab.getPopularProducts().size() == 0) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("getting products items");
            mProgressDialog.show();


            RetrofitClinetInstance.getRetrofitClinetInstance().create(Api.class).getProductList(Api.orderByPopularity).enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        mProgressDialog.cancel();
                        mProductLab.setPopularProducts(response.body());
                        popularAdapter = new ProductsAdapter(response.body());
                        mProductLab.setAllProducts(response.body());
                        popularProductsRecyclerView.setAdapter(popularAdapter);


                    }

                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    if (getActivity() != null)

                        Toast.makeText(getActivity(), "problem with your request", Toast.LENGTH_SHORT).show();
                    mProgressDialog.cancel();

                }
            });
        } else {

            popularAdapter = new ProductsAdapter(mProductLab.getPopularProducts());
            popularProductsRecyclerView.setAdapter(popularAdapter);

        }


    }

    private void getBestSellerProducts() {

        if (mProductLab.getBestSellerProducts() == null || mProductLab.getBestSellerProducts().size() == 0) {
//            mProgressDialog = new ProgressDialog(getActivity());
//            mProgressDialog.setMessage("getting products items");
//            mProgressDialog.show();


            RetrofitClinetInstance.getRetrofitClinetInstance().create(Api.class).getProductList(Api.orderByRateList).enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()) {
                        // mProgressDialog.cancel();
                        mProductLab.setBestSellerProducts(response.body());
                        bestSellerAdapter = new ProductsAdapter(response.body());
                        bestsellProductsRecyclerView.setAdapter(bestSellerAdapter);


                    }

                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    if (getActivity() != null)

                        Toast.makeText(getActivity(), "problem with your request", Toast.LENGTH_SHORT).show();
                    // mProgressDialog.cancel();

                }
            });
        } else {

            bestSellerAdapter = new ProductsAdapter(mProductLab.getBestSellerProducts());
            bestsellProductsRecyclerView.setAdapter(bestSellerAdapter);

        }

    }

    private class CategoryHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImageview;
        private TextView categoryNameTextview;
        private Category mCategory;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            categoryImageview = itemView.findViewById(R.id.category_item_imageview);
            categoryNameTextview = itemView.findViewById(R.id.category_item_textview);
        }

        public void bind(Category category) {

            categoryNameTextview.setText(category.getName());
            if (category.getImage() != null) {
                //Toast.makeText(MainActivity.this,"joon",Toast.LENGTH_LONG).show();
                Picasso.get().load(category.getImage().getPath()).into(categoryImageview);
            }

        }
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
        private List<Category> mList;

        public CategoryAdapter(List<Category> list) {
            mList = list;
        }

        public void setList(List<Category> list) {
            mList = list;
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).
                    inflate(R.layout.category_list_item, parent, false);
            return new CategoryHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
            Category category = mList.get(position);
            holder.bind(category);

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    private void getCategories() {

        if (mProductLab.getCategoryList() == null || mProductLab.getCategoryList().size() == 0) {
//            mProgressDialog = new ProgressDialog(getActivity());
//            mProgressDialog.setMessage("getting products items");
//            mProgressDialog.show();


            RetrofitClinetInstance.getRetrofitClinetInstance().create(Api.class).getCategory(Api.getAllcategories).enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if (response.isSuccessful()) {
                        // mProgressDialog.cancel();
                        mProductLab.setCategoryList(response.body());
                        mCategoryAdapter = new CategoryAdapter(response.body());
                        categoryListRecyclerView.setAdapter(mCategoryAdapter);


                    }

                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {

                    if (getActivity() != null)

                        Toast.makeText(getActivity(), "problem with your request", Toast.LENGTH_SHORT).show();
                    // mProgressDialog.cancel();

                }
            });
        } else {

            mCategoryAdapter = new CategoryAdapter(mProductLab.getCategoryList());
            categoryListRecyclerView.setAdapter(mCategoryAdapter);

        }


    }
}