package com.example.bookland.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookland.R;

public class BookDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name, description, ratingStr, nameD, authorD, pages, genre, price;
    Context context;
    RatingBar bar;
    RatingBar ratingBar;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        imageView = findViewById(R.id.img_detail);
        name = findViewById(R.id.name_detail);
        description = findViewById(R.id.description_id);
        ratingBar = findViewById(R.id.rating_detail);
        bar = findViewById(R.id.rating_show);
        ratingStr = findViewById(R.id.rating_txt);
        order = findViewById(R.id.order_id);

        nameD = findViewById(R.id.name);
        authorD = findViewById(R.id.author);
        genre = findViewById(R.id.genre);
        pages = findViewById(R.id.pages);
        price = findViewById(R.id.price);

        Bundle bundle = getIntent().getExtras();
        name.setText(bundle.getString("name"));
        description.setText(bundle.getString("description"));
        bar.setRating(Float.parseFloat(bundle.getString("rating")));
        ratingStr.setText(bundle.getString("rating"));
        Glide.with(this).load(bundle.getString("image")).into(imageView);

        nameD.setText((bundle.getString("name")));
        authorD.setText((bundle.getString("author")));
        genre.setText(String.format("Genre: %s", bundle.getString("genre")));
        pages.setText(String.format("pages %s", bundle.getString("pages")));
        price.setText(String.format("som %s", bundle.getString("price")));

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(BookDetail.this, "Selected "+rating, Toast.LENGTH_SHORT).show();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetail.this, CrdCardForm.class);
                BookDetail.this.startActivity(intent);
            }
        });


    }
}