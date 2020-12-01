package com.example.bookland.Recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookland.Activity.InsertToFirebase;
import com.example.bookland.Book;
import com.example.bookland.Activity.BookDetail;
import com.example.bookland.BookMark;
import com.example.bookland.R;
import com.example.bookland.TabLayout.TopFragment;
import com.example.bookland.User;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class RecyclerMarkAdapter extends RecyclerView.Adapter<RecyclerMarkAdapter.MyView> {
    ArrayList<BookMark> markData;
    Context context;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TopFragment topFragment = new TopFragment();

    public RecyclerMarkAdapter(Context context, ArrayList<BookMark> markData){
        this.context = context;
        this.markData = markData;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_top, parent, false);
        return new MyView(view);    }

    @Override
    public void onBindViewHolder(@NonNull final MyView holder, final int position) {
        holder.name.setText(markData.get(position).getNameMark());
        holder.price.setText(markData.get(position).getPriceMark());
        holder.rating.setText(markData.get(position).getRatingMark());
        Glide.with(context).load(markData.get(position).getImageUrlMark()).into(holder.image);


        holder.saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topFragment.isCheck()==false){
                    holder.saved.setImageResource(R.drawable.ic_baseline_bookmark_24);
                    topFragment.setCheck(true);
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Book_Saved");
                    String name = markData.get(position).getNameMark();
                    String author = markData.get(position).getAuthorMark();
                    String price = markData.get(position).getPriceMark();
                    String rating = markData.get(position).getRatingMark();
                    String description = markData.get(position).getDescriptionMark();
                    String category = markData.get(position).getCategoryMark();
                    String image = markData.get(position).getImageUrlMark();
                    User user = new User(name, author, price, category, rating, description, image);
                    reference.child(name).setValue(user);
                }else{
                    holder.saved.setImageResource(R.drawable.bookmark);
                    reference = FirebaseDatabase.getInstance().getReference("Book_Saved").child(markData.get(position).getNameMark());
                    reference.removeValue();
                    topFragment.setCheck(false);
                }

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("name", markData.get(position).getNameMark());
                intent.putExtra("price", markData.get(position).getPriceMark());
                intent.putExtra("image", markData.get(position).getImageUrlMark());
                intent.putExtra("rating", markData.get(position).getRatingMark());
                intent.putExtra("description", markData.get(position).getDescriptionMark());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return markData.size();
    }



    public class MyView extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, price, rating;
        ImageView image, saved;
        @SuppressLint("UseCompatLoadingForDrawables")
        public MyView(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.gridlayout);
            name = itemView.findViewById(R.id.name_id);
            price = itemView.findViewById(R.id.fee_id);
            image = itemView.findViewById(R.id.img_grid);
            rating = itemView.findViewById(R.id.rating_id);
            saved = itemView.findViewById(R.id.saved_id);

        }
    }
}
