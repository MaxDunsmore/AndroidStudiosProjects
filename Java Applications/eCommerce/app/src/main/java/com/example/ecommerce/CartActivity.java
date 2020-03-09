package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private ClickHandler clickHandler;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = DataBindingUtil.setContentView(this,R.layout.activity_cart);

        clickHandler = new ClickHandler(this);
        activityCartBinding .setClickHandler(clickHandler);

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
        public void nextButtonCart(View view){

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
                holder.textProductQuantity.setText("Quantity: " + model.getQuantity());
                holder.textProductPrice.setText("Price: $" + model.getPrice());
                holder.textProductQuantity.setText(model.getPname());
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
