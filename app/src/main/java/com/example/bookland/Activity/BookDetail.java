package com.example.bookland.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookland.R;

public class BookDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name, description;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        imageView = findViewById(R.id.img_detail);
        name = findViewById(R.id.name_detail);
        description = findViewById(R.id.description_id);

        Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        name.setText(bundle.getString("name"));
        description.setText(bundle.getString("description"));
        //imageView.setImageResource(bundle.getInt("image"));
        Glide.with(this).load(bundle.getString("image")).into(imageView);

    }
}