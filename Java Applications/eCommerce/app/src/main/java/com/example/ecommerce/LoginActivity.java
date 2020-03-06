package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ecommerce.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    // data binding
    ActivityLoginBinding mainBinding;

    // vars
    private ClickHandler clickHandler;
    public User user;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loadingBar = new ProgressDialog(this);

        // bind user
        user = new User();
        mainBinding.setUser(user);

        // bind clickHandler
        clickHandler = new ClickHandler(this);
        mainBinding.setClickHandler(clickHandler);


    }

    public class ClickHandler {
        private Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void loginButtonClick(View view) {
            loginUser();

        }
    }

    private void loginUser() {
        if (mainBinding.getUser().getPhoneNumber() == null || mainBinding.getUser().getPhoneNumber().isEmpty()) {
            Toast.makeText(this, "Please enter your phone number ....", Toast.LENGTH_SHORT).show();
        } else if (user.getPassword() == null || mainBinding.getUser().getPassword().isEmpty()) {
            Toast.makeText(this, "Please enter your password ....", Toast.LENGTH_SHORT).show();
        } else {
            String password = user.getPassword();
            String phoneNumber = user.getPhoneNumber();
            loadingBar.setTitle("Account Login");
            loadingBar.setMessage("Logging in, Please wait");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phoneNumber,password);
        }
    }
    public void AllowAccessToAccount(String phoneNumber,String password){
        final DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(phoneNumber).exists()){
                    User userData = dataSnapshot.child(parentDbName).child(phoneNumber).getValue(User.class);
                    if (userData.getPhoneNumber().equals(phoneNumber)){
                        if (userData.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this,"Logged in Successfully...",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Log.d("!@!","!@!");
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"Account with this " + phoneNumber + " phone number do not exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
