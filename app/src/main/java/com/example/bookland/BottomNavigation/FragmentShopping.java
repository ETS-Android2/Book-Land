package com.example.bookland.BottomNavigation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookland.Helpers.AddCart;
import com.example.bookland.R;
import com.example.bookland.Adapters.RecyclerAddCart;
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
    Button placeBtn;
    TextView total;
    private DatabaseReference reference;
    int myTotal = 0;


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, container, false);
        cartDataM = new ArrayList<>();
        placeBtn = view.findViewById(R.id.order_btn);
        total = view.findViewById(R.id.total_id);

        recyclerView1 = view.findViewById(R.id.recycler_cart);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference();
        ClearAll();
        DataFirebase();

        SharedPreferences sharedPreferences1 = this.getActivity().getSharedPreferences("userId", Context.MODE_PRIVATE);
        final String Uid = sharedPreferences1.getString("Uid", "");

        placeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.place_dialog, null);
                final EditText place = (EditText) mView.findViewById(R.id.place);
                final EditText customer = (EditText) mView.findViewById(R.id.customer);
                Button dialogOrder = mView.findViewById(R.id.order_dialog);
                builder.setView(mView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                dialogOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tPlace = place.getText().toString();
                        String tCustomer = customer.getText().toString();
                        if(tPlace.isEmpty() || tCustomer.isEmpty()){
                            Toast.makeText(getActivity(), "Please fill the place and customer", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Ordered Successfully", Toast.LENGTH_SHORT).show();
                            DatabaseReference refer = FirebaseDatabase.getInstance().getReference("Users").child(Uid).child("AddCart");
                            refer.removeValue();
                            myTotal = 0;
                            alertDialog.dismiss();

                        }
                    }
                });

            }
        });

        return view;

    }
    @SuppressLint("SetTextI18n")
    private void DataFirebase(){
        SharedPreferences sharedPreferences1 = this.getActivity().getSharedPreferences("userId", Context.MODE_PRIVATE);
        final String Uid = sharedPreferences1.getString("Uid", "");
        Query query = reference.child("Users").child(Uid).child("AddCart");
        query.addValueEventListener(new ValueEventListener() {
            final int[] amountPrice = {0};
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot i : snapshot.getChildren()){
                    AddCart addCart = new AddCart();
                    addCart.setName(i.child("name").getValue().toString());
                    addCart.setPrice(i.child("price").getValue().toString());
                    addCart.setCount(i.child("count").getValue().toString());
                    int priceOf = Integer.parseInt(i.child("price").getValue().toString());
                    int amountOf = Integer.parseInt(i.child("count").getValue().toString());
                    amountPrice[0] +=priceOf*amountOf;

                    cartDataM.add(addCart);
                }
                recyclerAddCart = new RecyclerAddCart(getActivity(), cartDataM);
                recyclerView1.setAdapter(recyclerAddCart);
                recyclerAddCart.notifyDataSetChanged();
                myTotal = amountPrice[0];
                total.setText("Total: "+myTotal +" som");
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
