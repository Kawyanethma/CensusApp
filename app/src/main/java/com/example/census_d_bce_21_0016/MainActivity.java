package com.example.census_d_bce_21_0016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private boolean registerd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        //check registered or not
        Register reg = new Register();
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        registerd = sharedPreferences.getBoolean("REGISTERED",false);
        System.out.println(registerd);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (registerd){
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