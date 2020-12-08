package com.example.bookland.BottomNavigation;

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
import com.example.bookland.Recycler.RecyclerCategoryAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentShopping extends Fragment {
    View view;
    List<AddCart> cartData;
    RecyclerView recyclerView1;

    public List<AddCart> getCartData() {
        return cartData;
    }

    public void setCartData(List<AddCart> cartData) {
        this.cartData = cartData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, container, false);
        cartData = new ArrayList<>();
        /*
        String name = getActivity().getIntent().getExtras().getString("name");
        String price = getActivity().getIntent().getExtras().getString("price");
        int count = getActivity().getIntent().getExtras().getInt("count");

        cartData.add(new AddCart(name, price, count));

         */
        //cartData.add(new AddCart("The Book Thief", "350", 1));


        recyclerView1 = view.findViewById(R.id.recycler_cart);
        RecyclerAddCart recyclerAddCart = new RecyclerAddCart(getContext(), cartData);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setAdapter(recyclerAddCart);


        return view;

    }
}
