package com.example.census_d_bce_21_0016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private boolean registered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        //check registered or not

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        registered = sharedPreferences.getBoolean("REGISTERED",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (registered){
                    intent= new Intent(MainActivity.this,Login.class);
                }
                else{
                    intent = new Intent(MainActivity.this,Register.class);
                }
                startActivity(intent);
                finish();
            }
        },3000);
    }
}