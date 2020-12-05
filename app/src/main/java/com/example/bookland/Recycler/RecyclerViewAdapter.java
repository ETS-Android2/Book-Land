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
import com.example.bookland.Book;
import com.example.bookland.Activity.BookDetail;
import com.example.bookland.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyView> {
    ArrayList<Book> mData;
    Context context;
    FirebaseDatabase rootNode;
    FirebaseDatabase rootNodeS;
    DatabaseReference reference;
    DatabaseReference referenceS;

    public RecyclerViewAdapter(Context context, ArrayList<Book> mData){
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_top, parent, false);
        return new MyView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyView holder, final int position) {
        holder.name.setText(mData.get(position).getName());
        holder.price.setText(mData.get(position).getPrice());
        holder.rating.setText(mData.get(position).getRating());
        Glide.with(context).load(mData.get(position).getImage()).into(holder.image);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Book_List");
        
        final SharedPreferences sharedPreferences = context.getSharedPreferences("pref", MODE_PRIVATE);
        String check = sharedPreferences.getString(mData.get(position).getName(), "");
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
                    referenceS = rootNodeS.getReference("Book_Saved");
                    referenceS.child(mData.get(position).getName()).setValue(mData.get(position));

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(mData.get(position).getName(), "1");
                    editor.apply();

                }else{
                    holder.saved.setImageResource(R.drawable.bookmark);
                    referenceS = FirebaseDatabase.getInstance().getReference("Book_Saved").child(mData.get(position).getName());
                    referenceS.removeValue();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(mData.get(position).getName(), "0");
                    editor.apply();

                }

            }

        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("name", mData.get(position).getName());
                intent.putExtra("price", mData.get(position).getPrice());
                intent.putExtra("image", mData.get(position).getImage());
                intent.putExtra("rating", mData.get(position).getRating());
                intent.putExtra("description", mData.get(position).getDescription());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
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
