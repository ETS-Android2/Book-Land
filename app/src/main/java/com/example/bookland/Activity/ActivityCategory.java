package com.example.bookland.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.bookland.Book;
import com.example.bookland.BookCard;
import com.example.bookland.MainActivity;
import com.example.bookland.R;
import com.example.bookland.Recycler.RecyclerCategoryAdapter;
import com.example.bookland.Recycler.RecyclerCategoryCard;
import com.example.bookland.Recycler.RecyclerViewAdapter;
import com.example.bookland.TabLayout.TopFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityCategory extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerCategoryCard recyclerCategoryCard;
    DatabaseReference cRef;
    Context context;

    ArrayList<BookCard> dataBiography;
    ArrayList<BookCard> dataBusiness;
    ArrayList<BookCard> dataCooking;
    ArrayList<BookCard> dataHistorical;
    ArrayList<BookCard> dataHome;
    ArrayList<BookCard> dataHorror;
    ArrayList<BookCard> dataKids;
    ArrayList<BookCard> dataNovel;
    ArrayList<BookCard> dataMedical;
    ArrayList<BookCard> dataReligion;
    ArrayList<BookCard> dataFantasy;
    ArrayList<BookCard> dataScience;
    ArrayList<BookCard> dataSports;
    ArrayList<BookCard> dataDetective;
    ArrayList<BookCard> dataEconomics;
    ArrayList<BookCard> dataPhilosophy;

    private String categoryN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dataBiography = new ArrayList<>();
        dataBusiness= new ArrayList<>();
        dataCooking= new ArrayList<>();
        dataHistorical= new ArrayList<>();
        dataNovel= new ArrayList<>();
        dataHome = new ArrayList<>();
        dataHorror=new ArrayList<>();
        dataKids=new ArrayList<>();
        dataMedical=new ArrayList<>();
        dataReligion=new ArrayList<>();
        dataFantasy=new ArrayList<>();
        dataScience=new ArrayList<>();
        dataSports=new ArrayList<>();

        dataPhilosophy=new ArrayList<>();
        dataDetective=new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);
        categoryN = getIntent().getStringExtra("categoryName");

        cRef = FirebaseDatabase.getInstance().getReference();
        ClearAll();
        DataFirebase();
    }
    private void DataFirebase(){
        Query query = cRef.child("Book_List");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot i : snapshot.getChildren()) {
                    BookCard bookCard = new BookCard();
                    bookCard.setImageUrlCard(i.child("image").getValue().toString());
                    bookCard.setNameCard(i.child("name").getValue().toString());
                    bookCard.setAuthorCard(i.child("author").getValue().toString());
                    bookCard.setPriceCard(i.child("price").getValue().toString());
                    bookCard.setRatingCard(i.child("rating").getValue().toString());
                    bookCard.setDescriptionCard(i.child("description").getValue().toString());
                    bookCard.setCategoryCard(i.child("category").getValue().toString());
                    if ((i.child("category").getValue().toString()).contains("biography")) {
                        dataBiography.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("business")) {
                        dataBusiness.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("cooking")) {
                        dataCooking.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("history")) {
                        dataHistorical.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("home")) {
                        dataHome.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("horror")) {
                        dataHorror.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("kids")) {
                        dataKids.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("novel")) {
                        dataNovel.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("medical")) {
                        dataMedical.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("religion")) {
                        dataReligion.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("fantasy")) {
                        dataFantasy.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("science")) {
                        dataScience.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("sports")) {
                        dataSports.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("detective")) {
                        dataDetective.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("economics")) {
                        dataEconomics.add(bookCard);
                    }if ((i.child("category").getValue().toString()).contains("philosophy")) {
                        dataPhilosophy.add(bookCard);
                    }
                }

                if(categoryN.equals("Biography")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataBiography);
                }else if(categoryN.equals("Business")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataBusiness);
                }else if(categoryN.equals("Cooking")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataCooking);
                }else if(categoryN.equals("Historical")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataHistorical);
                }else if(categoryN.equals("Home & Garden")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataHome);
                }else if(categoryN.equals("Horror")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataHorror);
                }else if(categoryN.equals("Kids")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataKids);
                }else if(categoryN.equals("Novel")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataNovel);
                }else if(categoryN.equals("Medical")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataMedical);
                }else if(categoryN.equals("Religion")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataReligion);
                }else if(categoryN.equals("Fantasy")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataFantasy);
                }else if(categoryN.equals("Science & Math")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataScience);
                }else if(categoryN.equals("Sports")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataSports);
                }else if(categoryN.equals("Detective")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataDetective);
                }else if(categoryN.equals("Economics")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataEconomics);
                }else if(categoryN.equals("Philosophy & Motivation")){
                    recyclerCategoryCard = new RecyclerCategoryCard(getApplicationContext(), dataPhilosophy);
                }
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                recyclerView.setAdapter(recyclerCategoryCard);
                recyclerCategoryCard.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ClearAll(){
        if(dataBiography != null){
            dataBiography.clear();
            if (recyclerCategoryCard != null) {
                recyclerCategoryCard.notifyDataSetChanged();
            }
        }
        if(dataCooking != null){
            dataCooking.clear();
            if (recyclerCategoryCard != null) {
                recyclerCategoryCard.notifyDataSetChanged();
            }
        }
        if(dataNovel != null){
            dataNovel.clear();
            if (recyclerCategoryCard != null) {
                recyclerCategoryCard.notifyDataSetChanged();
            }
        }
    }
}