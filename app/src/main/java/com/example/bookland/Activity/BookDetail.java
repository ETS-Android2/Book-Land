package com.example.bookland.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookland.AddCart;
import com.example.bookland.BottomNavigation.FragmentShopping;
import com.example.bookland.R;
import com.example.bookland.Recycler.RecyclerAddCart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name, description, ratingStr, nameD, authorD, pages, genre, price, counter, name1;
    RatingBar bar;
    RatingBar ratingBar;
    Button minusBtn, plusBtn, ratingBtn;
    ImageButton add_shopping;
    private int count;
    private String bookName;
    private String bookPrice;
    List<AddCart> cartData;
    float rated;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference2;
    FirebaseDatabase firebaseDatabase2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        cartData = new ArrayList<>();
        initAll();

        final Bundle bundle = getIntent().getExtras();
        bookName = bundle.getString("name");
        name.setText(bundle.getString("name"));
        bookPrice = bundle.getString("price");
        description.setText(bundle.getString("description"));
        bar.setRating(Float.parseFloat(bundle.getString("rating")));
        ratingStr.setText(bundle.getString("rating"));
        Glide.with(this).load(bundle.getString("image")).into(imageView);

        nameD.setText((bundle.getString("name")));
        authorD.setText((bundle.getString("author")));
        genre.setText(String.format("Genre: %s", bundle.getString("genre")));
        pages.setText(String.format("%s pages ", bundle.getString("pages")));
        price.setText(String.format("%s som", bundle.getString("price")));


        name1.setText(bundle.getString("name"));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rated = rating;
                System.out.println(rating);
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counter.setText(count+"");
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>1){
                    count--;
                }
                counter.setText(count+"");
            }
        });
        add_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sCount = Integer.toString(count);
                SharedPreferences sharedPreferences1 = getSharedPreferences("userId", Context.MODE_PRIVATE);
                final String Uid = sharedPreferences1.getString("Uid", "");
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Users").child(Uid).child("AddCart").child(bookName);
                databaseReference.child("name").setValue(bookName);
                databaseReference.child("price").setValue(bookPrice);
                databaseReference.child("count").setValue(sCount);

                Toast.makeText(BookDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();


            }
        });
        SharedPreferences sharedPreferences1 = getSharedPreferences("userId", Context.MODE_PRIVATE);
        final String Uid = sharedPreferences1.getString("Uid", "");
        final SharedPreferences sharedPreferences2 = getSharedPreferences("Rating"+Uid, Context.MODE_PRIVATE);
        float rate = sharedPreferences2.getFloat(bookName, 0);
        ratingBar.setRating(rate);

        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.putFloat(bookName, rated);
                editor.commit();
                Toast.makeText(BookDetail.this, "Thank you for rating!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private  void initAll(){
        count = 1;
        imageView = findViewById(R.id.img_detail);
        name = findViewById(R.id.name_detail);
        description = findViewById(R.id.description_id);
        ratingBar = findViewById(R.id.rating_detail);
        bar = findViewById(R.id.rating_show);
        ratingStr = findViewById(R.id.rating_txt);

        nameD = findViewById(R.id.name);
        authorD = findViewById(R.id.author);
        genre = findViewById(R.id.genre);
        pages = findViewById(R.id.pages);
        price = findViewById(R.id.price);

        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.plusBtn);
        counter = findViewById(R.id.txtCounter);
        name1 = findViewById(R.id.name1);
        add_shopping = findViewById(R.id.add_shopping_btn);
        ratingBtn = findViewById(R.id.rate_btn);


    }

}