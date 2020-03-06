package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecommerce.databinding.ActivityHomeBinding;
import com.example.ecommerce.databinding.ActivityLoginBinding;

public class HomeActivity extends AppCompatActivity {
    // data binding
    ActivityHomeBinding mainBinding;

    // vars
    public ClickHandler clickHandler;
    //public User user;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);

        clickHandler = new ClickHandler(this);
        mainBinding.setClickHandler(clickHandler);
    }

    public class ClickHandler {
        private Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void buttonClick(View view) {
            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
