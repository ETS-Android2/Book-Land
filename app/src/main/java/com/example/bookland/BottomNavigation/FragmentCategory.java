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

import com.example.bookland.R;
import com.example.bookland.Recycler.RecyclerCategoryAdapter;

public class FragmentCategory extends Fragment {
    View view;
    String[]category;
    RecyclerView recyclerView;
    int[]image = {R.drawable.biography, R.drawable.business, R.drawable.cooking,R.drawable.history,
            R.drawable.homegarden, R.drawable.horror,R.drawable.kids, R.drawable.literature, R.drawable.medical,
            R.drawable.religion, R.drawable.fantasy, R.drawable.science, R.drawable.sport, R.drawable.crime, R.drawable.ecenomics, R.drawable.philosophy};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        category = getResources().getStringArray(R.array.BookCategory);
        recyclerView = view.findViewById(R.id.recycler_categ);
        RecyclerCategoryAdapter recyclerCategoryAdapter = new RecyclerCategoryAdapter(getContext(), category, image);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerCategoryAdapter);

        return view;
    }

}
