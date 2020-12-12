package com.example.bookland.BottomNavigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookland.BookMark;
import com.example.bookland.R;
import com.example.bookland.Recycler.RecyclerMarkAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentMark extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecyclerMarkAdapter recyclerMarkAdapter;
    ArrayList<BookMark> MarkData;
    Context context;

    private DatabaseReference myRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_mark, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_mark_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();
        MarkData = new ArrayList<>();
        ClearAll();
        DataFirebase();


        return view;
    }
    private void DataFirebase(){
        SharedPreferences sharedPreferences1 = this.getActivity().getSharedPreferences("userId", Context.MODE_PRIVATE);
        String Uid = sharedPreferences1.getString("Uid", "");
        Query query = myRef.child("Users").child(Uid).child("Book_Saved");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot i : snapshot.getChildren()){
                    BookMark bookMark = new BookMark();
                    bookMark.setImageUrlMark(i.child("image").getValue().toString());
                    bookMark.setNameMark(i.child("name").getValue().toString());
                    bookMark.setCategoryMark(i.child("category").getValue().toString());
                    bookMark.setAuthorMark(i.child("author").getValue().toString());
                    bookMark.setPriceMark(i.child("price").getValue().toString());
                    bookMark.setRatingMark(i.child("rating").getValue().toString());
                    bookMark.setDescriptionMark(i.child("description").getValue().toString());
                    MarkData.add(bookMark);
                }
                recyclerMarkAdapter = new RecyclerMarkAdapter(getActivity(), MarkData);
                recyclerView.setAdapter(recyclerMarkAdapter);
                recyclerMarkAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if(MarkData != null){
            MarkData.clear();
            if (recyclerMarkAdapter != null) {
                recyclerMarkAdapter.notifyDataSetChanged();
            }
        }
        MarkData = new ArrayList<>();

    }

}
