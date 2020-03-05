package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecommerce.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // data binding
    ActivityMainBinding mainBinding;

    // vars
    private ClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        clickHandler = new ClickHandler(this);
        mainBinding.setClickHandler(clickHandler);



    }
    public class ClickHandler{
        private Context context;
        public ClickHandler(Context context){
            this.context = context;
        }
        public void loginButtonClick(View view){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        public void joinNowButtonClick(View view){
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}
