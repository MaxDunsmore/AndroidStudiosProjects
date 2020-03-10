package com.example.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ecommerce.Model.Products;
import com.example.ecommerce.Prevalent.Prevalent;
import com.example.ecommerce.databinding.ActivityProductDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {
    ActivityProductDetailsBinding activityProductDetailsBinding;
    public boolean descriptionStatus = false; // true = displayed
    public ClickHandler clickHandler;
    private String productID = "";
    private String saveCurrentDate;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);

        productID = getIntent().getStringExtra("pid");

        clickHandler = new ClickHandler(this);
        activityProductDetailsBinding.setClickHandler(clickHandler);

        getProductDetails();
        activityProductDetailsBinding.numberButton.setOnValueChangeListener((view, oldValue, newValue) -> {
            Calendar calendar = Calendar.getInstance();
            saveCurrentDate = calendar.getTime().toString();


            final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
            final HashMap<String, Object> cartMap = new HashMap<>();
            cartMap.put("pid", productID);
            cartMap.put("pname", activityProductDetailsBinding.productNameDetails.getText().toString());
            cartMap.put("price", activityProductDetailsBinding.productPriceDetails.getText().toString());
            cartMap.put("date", saveCurrentDate);
            cartMap.put("quantity", activityProductDetailsBinding.numberButton.getNumber());
            cartMap.put("discount", "");
            cartListRef.child("User View").child(Prevalent.currentUserOnline.getPhoneNumber()).child("Products").child(productID)
                    .updateChildren(cartMap)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            cartListRef.child("Admin View").child(Prevalent.currentUserOnline.getPhoneNumber()).child("Products").child(productID)
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                if (newValue == 0){
                                                    displayToastMessage("Item no longer in cart");
                                                    cartListRef.child("User View")
                                                            .child(Prevalent.currentUserOnline.getPhoneNumber())
                                                            .child("Products")
                                                            .child(productID)
                                                            .removeValue();
                                                }else if(newValue < oldValue){
                                                ;
                                                    displayToastMessage("Item removed from cart");
                                                }else if(newValue > oldValue){
                                                    displayToastMessage("Item added to card");
                                                }

                                            }
                                        }
                                    });
                        }
                    });
        });

    }

    private void displayToastMessage(String s) {
        toast = Toast.makeText(ProductDetailsActivity.this, s, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.WHITE);
        v.setBackgroundColor(ContextCompat.getColor(ProductDetailsActivity.this,
                R.color.colorPrimaryDark));
        toast.setGravity(Gravity.BOTTOM, 0, 290);
        toast.show();
    }

    private void getProductDetails() {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Products products = dataSnapshot.getValue(Products.class);
                    activityProductDetailsBinding.productNameDetails.setText(products.getPname());
                    activityProductDetailsBinding.productPriceDetails.setText(products.getPrice());
                    activityProductDetailsBinding.productDescriptionDetails.setText(products.getDescription());
                    activityProductDetailsBinding.longDescriptionProductDetailsText.setText(products.getDescriptionLong());
                    Picasso.get().load(products.getImage()).into(activityProductDetailsBinding.productImageDetails);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class ClickHandler {
        private Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void addToCartDetails(View view) {

        }

        public void imageDetailsClicked(View view) {

        }

        public void numberButtonCLicked(View view) {

        }
        public void cartClicked(View view){
            Intent intent = new Intent(ProductDetailsActivity.this,CartActivity.class);
            startActivity(intent);
        }

        public void displayFullDescription(View view) {
            if (!descriptionStatus) {
                activityProductDetailsBinding.longDescriptionProductDetails.setText("Hide description");
                activityProductDetailsBinding.longDescriptionProductDetailsText.setVisibility(View.VISIBLE);
                activityProductDetailsBinding.longDescriptionProductDetails.setBackgroundResource(R.drawable.backgroundbuttonup);
                descriptionStatus = true;
            } else if (descriptionStatus) {
                activityProductDetailsBinding.longDescriptionProductDetails.setText("Product description");
                activityProductDetailsBinding.longDescriptionProductDetailsText.setVisibility(View.INVISIBLE);
                activityProductDetailsBinding.longDescriptionProductDetails.setBackgroundResource(R.drawable.backgroundbuttondown);

                descriptionStatus = false;
            }
        }
    }
}
