package com.example.bookland.Recycler;

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
import com.example.bookland.BookMark;
import com.example.bookland.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerMarkAdapter extends RecyclerView.Adapter<RecyclerMarkAdapter.MyView> {
    ArrayList<BookMark> markData;
    Context context;
    FirebaseDatabase rootNode;
    FirebaseDatabase rootNodeS;
    DatabaseReference reference;
    DatabaseReference referenceS;


    public RecyclerMarkAdapter(Context context, ArrayList<BookMark> markData){
        this.context = context;
        this.markData = markData;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_topnew, parent, false);
        return new MyView(view);    }

    @Override
    public void onBindViewHolder(@NonNull final MyView holder, final int position) {
        holder.name.setText(markData.get(position).getNameMark());
        holder.price.setText(markData.get(position).getPriceMark());
        holder.rating.setText(markData.get(position).getRatingMark());
        Glide.with(context).load(markData.get(position).getImageUrlMark()).into(holder.image);


        SharedPreferences sharedPreferences1 = context.getSharedPreferences("userId", Context.MODE_PRIVATE);
        final String Uid = sharedPreferences1.getString("Uid", "");
        final SharedPreferences sharedPreferences = context.getSharedPreferences("pref"+Uid, MODE_PRIVATE);
        String check = sharedPreferences.getString(markData.get(position).getNameMark(), "");
        if(check.equals("0") || check.isEmpty() || check.equals("")){
            holder.saved.setImageResource(R.drawable.bookmark);
        }else {
            holder.saved.setImageResource(R.drawable.ic_baseline_bookmark_24);
        }
        holder.saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.saved.getDrawable().getConstantState() == context.getDrawable(R.drawable.bookmark).getConstantState()){
                    holder.saved.setImageResource(R.drawable.ic_baseline_bookmark_24);
                    rootNodeS = FirebaseDatabase.getInstance();
                    referenceS = rootNodeS.getReference("Users").child(Uid).child("Book_Saved");
                    referenceS.child(markData.get(position).getNameMark()).setValue(markData.get(position));

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(markData.get(position).getNameMark(), "1");
                    editor.apply();

                }else{
                    holder.saved.setImageResource(R.drawable.bookmark);
                    referenceS = FirebaseDatabase.getInstance().getReference("Users").child(Uid).child("Book_Saved").child(markData.get(position).getNameMark());
                    referenceS.removeValue();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(markData.get(position).getNameMark(), "0");
                    editor.apply();
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
