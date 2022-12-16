package com.example.restoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restoapp.models.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    MenuFragment menuFragment = new MenuFragment();
    OrderFragment orderFragment = new OrderFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    ProductFragment productFragment = new ProductFragment();

    Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.menuFragment);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        Bundle extras = getIntent().getExtras();
        int accountID = extras.getInt("accountID");

        Bundle bundle = new Bundle();
        bundle.putInt("accountID", accountID);
        profileFragment.setArguments(bundle);

        //Serializable order = getIntent().getSerializableExtra("order");
        //Bundle mBundle = new Bundle();
        //mBundle.setClassLoader(Order.class.getClassLoader());
        //mBundle.putSerializable("order", order);
        //orderFragment.setArguments(mBundle);
        //productFragment.setArguments(mBundle);
        //menuFragment.setArguments(mBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.nav_fragment, menuFragment)
                .commit();
    }

    private NavigationBarView.OnItemSelectedListener navListener = new
            NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menuFragment:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setReorderingAllowed(true)
                                    .replace(R.id.nav_fragment, menuFragment)
                                    .commit();
                            return true;
                        case R.id.orderFragment:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setReorderingAllowed(true)
                                    .replace(R.id.nav_fragment, orderFragment)
                                    .commit();
                            return true;
                        case R.id.profileFragment:
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setReorderingAllowed(true)
                                    .replace(R.id.nav_fragment, profileFragment)
                                    .commit();
                            return true;
                    }
                    return false;
                }
            };
}
