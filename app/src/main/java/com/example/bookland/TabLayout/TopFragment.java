package com.example.bookland.TabLayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookland.Book;
import com.example.bookland.R;
import com.example.bookland.Recycler.RecyclerTopAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecyclerTopAdapter recyclerTopAdapter;
    ArrayList<Book> mData;
    private DatabaseReference myRef;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public static TopFragment newInstance(String param1, String param2) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_top, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();
        mData = new ArrayList<>();
        ClearAll();
        DataFirebase();

        return view;

    }

    private void DataFirebase(){
        Query query = myRef.child("Book_List");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot i : snapshot.getChildren()){
                    Book book = new Book();
                    book.setImage(i.child("image").getValue().toString());
                    book.setName(i.child("name").getValue().toString());
                    book.setAuthor(i.child("author").getValue().toString());
                    book.setPrice(i.child("price").getValue().toString());
                    book.setRating(i.child("rating").getValue().toString());
                    book.setDescription(i.child("description").getValue().toString());
                    book.setCategory(i.child("category").getValue().toString());
                    book.setPages(i.child("pages").getValue().toString());
                    mData.add(book);
                }

                recyclerTopAdapter = new RecyclerTopAdapter(getActivity().getApplicationContext(), mData);
                recyclerView.setAdapter(recyclerTopAdapter);
                recyclerTopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ClearAll(){
        if(mData != null){
            mData.clear();
            if (recyclerTopAdapter != null) {
                recyclerTopAdapter.notifyDataSetChanged();
            }
        }
        mData = new ArrayList<>();
    }
}