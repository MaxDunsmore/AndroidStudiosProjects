package com.example.ecommerce;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Model.Cart;
import com.example.ecommerce.Prevalent.Prevalent;
import com.example.ecommerce.ViewHolder.CartViewHolder;
import com.example.ecommerce.databinding.ActivityCartBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding activityCartBinding;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart);


        ClickHandler clickHandler = new ClickHandler(this);
        activityCartBinding.setClickHandler(clickHandler);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setLayoutManager(layoutManager);

        activityCartBinding.cartList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        activityCartBinding.cartList.setLayoutManager(layoutManager);
    }

    public class ClickHandler {
        private Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void nextButtonCart(View view) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(cartListRef.child("User View")
                                .child(Prevalent.currentUserOnline.getPhoneNumber()).child("Products"), Cart.class)
                        .build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
                String stringQuantity = "Quantity: " + model.getQuantity();
                String stringPrice = "Price: " + model.getPrice();
                String stringName = model.getPname();
                holder.textProductQuantity.setText(stringQuantity);
                holder.textProductPrice.setText(stringPrice);
                holder.textProductName.setText(stringName);
                holder.itemView.setOnClickListener(view -> {
                    CharSequence options[] = new CharSequence[]{
                            "Edit",
                            "Remove"
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                    builder.setTitle("Cart Options:");
                    builder.setItems(options, (dialog, which) -> {
                        if (which == 0) {
                            Intent intent = new Intent(CartActivity.this, ProductDetailsActivity.class);
                            intent.putExtra("pid", model.getPid());
                            startActivity(intent);
                        }
                        if (which == 1) {
                            cartListRef.child("User View")
                                    .child(Prevalent.currentUserOnline.getPhoneNumber())
                                    .child("Products")
                                    .child(model.getPid())
                                    .removeValue();
                            //possibly remove code
                            cartListRef.child("Admin View")
                                    .child(Prevalent.currentUserOnline.getPhoneNumber())
                                    .child("Products")
                                    .child(model.getPid())
                                    .removeValue()
                                    .addOnCompleteListener(task -> {
                                        Toast.makeText(CartActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    });
                    builder.show();

                });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
                return new CartViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
