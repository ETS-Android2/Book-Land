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

import com.bumptech.glide.Glide;
import com.example.bookland.Activity.BookDetail;
import com.example.bookland.Book;
import com.example.bookland.BookCard;
import com.example.bookland.R;

import java.util.ArrayList;

public class RecyclerCategoryCard extends RecyclerView.Adapter<RecyclerCategoryCard.MyView> {
    ArrayList<BookCard> cData;

    Context context;

    public RecyclerCategoryCard(Context context, ArrayList<BookCard> cData){
        this.context = context;
        this.cData = cData;
    }


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_category_card, parent, false);
        return new MyView(view);    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, final int position) {
        holder.name.setText(cData.get(position).getNameCard());
        holder.price.setText(cData.get(position).getPriceCard());
        holder.rating.setText(cData.get(position).getRatingCard());
        Glide.with(context).load(cData.get(position).getImageUrlCard()).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("name", cData.get(position).getNameCard());
                intent.putExtra("price", cData.get(position).getPriceCard());
                intent.putExtra("image", cData.get(position).getImageUrlCard());
                intent.putExtra("rating", cData.get(position).getRatingCard());
                intent.putExtra("description", cData.get(position).getDescriptionCard());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cData.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, price, rating;
        ImageView image;
        public MyView(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.gridCard);
            name = itemView.findViewById(R.id.name_card);
            price = itemView.findViewById(R.id.fee_card);
            image = itemView.findViewById(R.id.img_card);
            rating = itemView.findViewById(R.id.rating_card);
        }
    }
}
