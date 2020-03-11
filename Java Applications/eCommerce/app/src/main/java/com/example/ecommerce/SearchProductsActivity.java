package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.ecommerce.databinding.ActivitySearchProductsBinding;

public class SearchProductsActivity extends AppCompatActivity {
    ActivitySearchProductsBinding activitySearchProductsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchProductsBinding = DataBindingUtil.setContentView(this,R.layout.activity_search_products);
    }
}
