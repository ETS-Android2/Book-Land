package com.example.bookland;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookland.BottomNavigation.FragmentCategory;
import com.example.bookland.BottomNavigation.FragmentHome;
import com.example.bookland.BottomNavigation.FragmentMark;
import com.example.bookland.BottomNavigation.FragmentSearch;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    BottomNavigationView myNavig;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myNavig = findViewById(R.id.bottom_navigation);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        sharedPreferences = getSharedPreferences("user", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("switch", "StringTrue");
        editor.apply();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentHome()).commit();

        myNavig.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new FragmentHome();
                        break;
                    case R.id.navigation_category:
                        fragment = new FragmentCategory();
                        break;
                    case R.id.navigation_mark:
                        fragment = new FragmentMark();
                        break;
                    case R.id.navigation_search:
                        fragment = new FragmentSearch();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                return true;
            }
        });

    }

}