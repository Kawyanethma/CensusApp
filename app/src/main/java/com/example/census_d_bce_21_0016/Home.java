package com.example.census_d_bce_21_0016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.census_d_bce_21_0016.databinding.ActivityHomeBinding;
import com.example.census_d_bce_21_0016.databinding.ActivityMainBinding;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Home extends AppCompatActivity {
    ConstraintLayout mLayout;
    int mDefultColor;

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        mLayout = (ConstraintLayout) findViewById(R.id.layout);
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        mDefultColor = sharedPreferences.getInt("color",0);
        if (mDefultColor==0)
             mDefultColor = ContextCompat.getColor(Home.this ,R.color.white);

        mLayout.setBackgroundColor(mDefultColor);

        repalceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    repalceFragment(new HomeFragment());
                    break;
                case R.id.add_data:
                    repalceFragment(new DataFragment());
                    break;
                case R.id.pref:
//                    openColorPicker();
                    repalceFragment(new PrefFragment());
                    break;
            }
            return true;
        });
    }



    private void repalceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }


}