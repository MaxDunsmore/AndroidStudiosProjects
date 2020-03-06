package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.ecommerce.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    // data binding
    ActivityRegisterBinding mainBinding;

    // vars
    private ClickHandler clickHandler;
    private User user;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        loadingBar = new ProgressDialog(this);

        user = new User();
        mainBinding.setUser(user);
        clickHandler = new ClickHandler(this);
        mainBinding.setClickHandler(clickHandler);

    }

    public class ClickHandler {
        private Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void registerButtonClick(View view) {
            createAccount();
        }
    }

    private void createAccount() {
        if (user.getName() == null || mainBinding.getUser().getName().isEmpty()) {
            Toast.makeText(this, "Please enter your name....", Toast.LENGTH_SHORT).show();
        } else if (user.getPhoneNumber() == null || mainBinding.getUser().getPhoneNumber().isEmpty()) {
            Toast.makeText(this, "Please enter your phone number ....", Toast.LENGTH_SHORT).show();
        } else if (user.getPassword() == null || mainBinding.getUser().getPassword().isEmpty()) {
            Toast.makeText(this, "Please enter your password ....", Toast.LENGTH_SHORT).show();
        } else {
            String name = user.getName();
            String password = user.getPassword();
            String phoneNumber = user.getPhoneNumber();
            loadingBar.setTitle("Creating Account");
            loadingBar.setMessage("Your Account is currently being created");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidatePhoneNumber(name, password, phoneNumber);
        }
    }
    private void ValidatePhoneNumber(final String name, final String password, final String phoneNumber) {
        final DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phoneNumber).exists())) {
                    HashMap<String, Object> userdatamap = new HashMap<>();
                    userdatamap.put("phoneNumber", phoneNumber);
                    userdatamap.put("password", password);
                    userdatamap.put("name", name);
                    RootRef.child("Users").child(phoneNumber).updateChildren(userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been made", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this,"Network Erro: Please Try Again...",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText(RegisterActivity.this, "This phone number: " + phoneNumber + " already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using another phone number", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
