package com.example.bookland.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.bookland.Helpers.BookCard;
import com.example.bookland.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerCategoryCard extends RecyclerView.Adapter<RecyclerCategoryCard.MyView> {
    ArrayList<BookCard> cData;
    Context context;
    FirebaseDatabase rootNode;
    FirebaseDatabase rootNodeS;
    DatabaseReference reference;
    DatabaseReference referenceS;


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
    public void onBindViewHolder(@NonNull final MyView holder, final int position) {
        holder.name.setText(cData.get(position).getName());
        holder.price.setText(cData.get(position).getPrice());
        holder.rating.setText(cData.get(position).getRating());
        Glide.with(context).load(cData.get(position).getImage()).into(holder.image);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", cData.get(position).getName());
                intent.putExtra("price", cData.get(position).getPrice());
                intent.putExtra("image", cData.get(position).getImage());
                intent.putExtra("rating", cData.get(position).getRating());
                intent.putExtra("description", cData.get(position).getDescription());
                context.startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences1 = context.getSharedPreferences("userId", Context.MODE_PRIVATE);
        final String Uid = sharedPreferences1.getString("Uid", "");
        final SharedPreferences sharedPreferences = context.getSharedPreferences("pref"+Uid, MODE_PRIVATE);
        String check = sharedPreferences.getString(cData.get(position).getName(), "");
        if(check.equals("0") || check.isEmpty() || check.equals("")){
            holder.saved.setImageResource(R.drawable.bookmark);
        }else {
            holder.saved.setImageResource(R.drawable.ic_baseline_bookmark_24);
        }
        holder.saved.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if(holder.saved.getDrawable().getConstantState() == context.getDrawable(R.drawable.bookmark).getConstantState()){
                    holder.saved.setImageResource(R.drawable.ic_baseline_bookmark_24);
                    rootNodeS = FirebaseDatabase.getInstance();
                    referenceS = rootNodeS.getReference("Users").child(Uid).child("Book_Saved");
                    referenceS.child(cData.get(position).getName()).setValue(cData.get(position));

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(cData.get(position).getName(), "1");
                    editor.apply();

                }else{
                    holder.saved.setImageResource(R.drawable.bookmark);
                    referenceS = FirebaseDatabase.getInstance().getReference("Users").child(Uid).child("Book_Saved").child(cData.get(position).getName());
                    referenceS.removeValue();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(cData.get(position).getName(), "0");
                    editor.apply();

                }
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
        ImageView image, saved;
        public MyView(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.gridCard);
            name = itemView.findViewById(R.id.name_card);
            price = itemView.findViewById(R.id.fee_card);
            image = itemView.findViewById(R.id.img_card);
            rating = itemView.findViewById(R.id.rating_card);
            saved = itemView.findViewById(R.id.saved_card);
        }
    }
}
