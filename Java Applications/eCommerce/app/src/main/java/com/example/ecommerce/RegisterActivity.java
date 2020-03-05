package com.example.ecommerce;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Context;
import android.os.Bundle;
import android.view.View;


import com.example.ecommerce.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    // data binding
    ActivityRegisterBinding mainBinding;

    // vars
    private ClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        clickHandler = new ClickHandler(this);
        mainBinding.setClickHandler(clickHandler);

    }
    public class ClickHandler {
        private Context context;
        public ClickHandler(Context context){
            this.context = context;
        }
        public void registerButtonClick(View view){
            createAccount();
        }
    }

    private void createAccount() {

    }
}
