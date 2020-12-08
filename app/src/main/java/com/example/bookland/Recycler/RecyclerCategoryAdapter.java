package com.example.bookland.Recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookland.Activity.ActivityCategory;
import com.example.bookland.R;

public class RecyclerCategoryAdapter extends RecyclerView.Adapter<RecyclerCategoryAdapter.MyView> {
    String[]categoryD;
    int[]image;
    Context context;


    public RecyclerCategoryAdapter(Context context, String[]categoryD, int[]image){
        this.context = context;
        this.image = image;
        this.categoryD = categoryD;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_category, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, final int position) {
        holder.category.setText(categoryD[position]);
        holder.imageView.setImageResource(image[position]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityCategory.class);
                intent.putExtra("categoryName", categoryD[position]);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryD.length;
    }

    public class MyView extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView category;
        public MyView(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_category_id);
            imageView = itemView.findViewById(R.id.category_img);
            category = itemView.findViewById(R.id.category_txt);
        }
    }
}
