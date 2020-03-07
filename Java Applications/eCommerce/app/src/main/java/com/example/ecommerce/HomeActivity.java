package com.example.ecommerce;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.ecommerce.databinding.ActivityHomeBinding;
import com.rey.material.widget.CheckBox;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    // data binding
    ActivityHomeBinding activityHomeBinding;
    // vars
    public ClickHandler clickHandler;
    //public User user;
    private ProgressDialog loadingBar;
    private CheckBox checkBoxRememberMe;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        clickHandler = new ClickHandler(this);
        activityHomeBinding.setClickHandler(clickHandler);
    }

    public class ClickHandler {
        private Context context;
        public ClickHandler(Context context) {
            this.context = context;
        }
        public void buttonClick(View view) {
            Paper.book().destroy();
            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
