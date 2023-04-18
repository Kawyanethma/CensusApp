package com.example.census_d_bce_21_0016;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.census_d_bce_21_0016.databinding.ActivityHomeBinding;
import com.example.census_d_bce_21_0016.databinding.ActivityMainBinding;

public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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