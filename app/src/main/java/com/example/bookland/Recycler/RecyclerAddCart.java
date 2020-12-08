package com.example.bookland.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookland.Activity.ActivityCategory;
import com.example.bookland.Activity.BookDetail;
import com.example.bookland.AddCart;
import com.example.bookland.R;

import java.util.List;

public class RecyclerAddCart extends RecyclerView.Adapter<RecyclerAddCart.MyCartView> {
    Context context;
    List<AddCart> cartData;

    public RecyclerAddCart(Context context, List<AddCart> cartData) {
        this.context = context;
        this.cartData = cartData;
    }

    @NonNull
    @Override
    public MyCartView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_add_cart, parent, false);
        return new MyCartView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartView holder, int position) {
        holder.name.setText(cartData.get(position).getName());
        holder.price.setText(cartData.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return cartData.size();
    }

    public class MyCartView extends RecyclerView.ViewHolder {
        TextView name, price;
        public MyCartView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name2);
            price = itemView.findViewById(R.id.price);
        }
    }
}
