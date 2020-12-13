package com.example.bookland.Recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookland.Activity.ActivityCategory;
import com.example.bookland.Activity.BookDetail;
import com.example.bookland.AddCart;
import com.example.bookland.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerAddCart extends RecyclerView.Adapter<RecyclerAddCart.MyCartView> {
    Context context;
    List<AddCart> cartData;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyCartView holder, final int position) {
        holder.name.setText(cartData.get(position).getName()+"    x  "+cartData.get(position).getCount());
        holder.price.setText(cartData.get(position).getPrice());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences1 = context.getSharedPreferences("userId", Context.MODE_PRIVATE);
                final String Uid = sharedPreferences1.getString("Uid", "");
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(Uid).child("AddCart").child(cartData.get(position).getName());
                databaseReference.removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartData.size();
    }

    public class MyCartView extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageButton delete;
        public MyCartView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name2);
            price = itemView.findViewById(R.id.price);
            delete = itemView.findViewById(R.id.delete_cart);
        }
    }
}
