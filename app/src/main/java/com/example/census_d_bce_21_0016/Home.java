package com.example.census_d_bce_21_0016;

import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.census_d_bce_21_0016.databinding.ActivityHomeBinding;

import java.util.Objects;

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