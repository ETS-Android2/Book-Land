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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookland.AddCart;
import com.example.bookland.R;
import com.example.bookland.Recycler.RecyclerAddCart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentShopping extends Fragment {
    View view;
    List<AddCart> cartDataM;
    RecyclerAddCart recyclerAddCart;
    RecyclerView recyclerView1;
    private DatabaseReference reference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, container, false);
        cartDataM = new ArrayList<>();

        recyclerView1 = view.findViewById(R.id.recycler_cart);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();
        ClearAll();
        DataFirebase();

        return view;

    }
    private void DataFirebase(){
        Query query = reference.child("AddCart");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot i : snapshot.getChildren()){
                    AddCart addCart = new AddCart();
                    addCart.setName(i.child("name").getValue().toString());
                    addCart.setPrice(i.child("price").getValue().toString());
                    addCart.setCount(i.child("count").getValue().toString());
                    cartDataM.add(addCart);
                }
                recyclerAddCart = new RecyclerAddCart(getActivity(), cartDataM);
                recyclerView1.setAdapter(recyclerAddCart);
                recyclerAddCart.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ClearAll(){
        if(cartDataM != null){
            cartDataM.clear();
            if (recyclerAddCart != null) {
                recyclerAddCart.notifyDataSetChanged();
            }
        }
        cartDataM = new ArrayList<>();
    }

}
