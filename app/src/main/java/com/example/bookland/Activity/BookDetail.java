package com.example.bookland.Activity;

import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

public class BookDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name, description, ratingStr, nameD, authorD, pages, genre, price, counter, name1;
    RatingBar bar;
    RatingBar ratingBar;
    Button order, minusBtn, plusBtn;
    ImageButton add_shopping;
    private int count;
    private String bookName;
    private String bookPrice;
    SharedPreferences sharedPreferences;
    Context context;

    List<AddCart> cartData;
    RecyclerView recyclerView;
    RecyclerAddCart recyclerAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initAll();

        Bundle bundle = getIntent().getExtras();
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
        pages.setText(String.format("pages %s", bundle.getString("pages")));
        price.setText(String.format("som %s", bundle.getString("price")));

        name1.setText(bundle.getString("name"));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(BookDetail.this, "Selected " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetail.this, CrdCardForm.class);
                BookDetail.this.startActivity(intent);
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

                cartData.add(new AddCart(bookName, bookPrice, count));


                sharedPreferences = getSharedPreferences("cart", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(bookName, count);
                editor.apply();

                Toast.makeText(BookDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();

            }
        });

        FragmentShopping shopping = new FragmentShopping();
        shopping.setCartData(cartData);
    }

    private  void initAll(){
        count = 1;
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

        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.plusBtn);
        counter = findViewById(R.id.txtCounter);
        name1 = findViewById(R.id.name1);
        add_shopping = findViewById(R.id.add_shopping_btn);

        cartData = new ArrayList<>();

    }
}