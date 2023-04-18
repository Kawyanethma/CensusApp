package com.example.census_d_bce_21_0016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText = (EditText) findViewById(R.id.newPassword);
    }

    public void onclick(View view) {
        String passwordInput = editText.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(passwordInput.isEmpty()){
            editText.setError("Password field can't be empty");
        }else if(passwordInput.length()<5){
            editText.setError("Password should be more than 5 characters");
        }else{
            editText.setError(null);
            editor.putString("password",editText.getText().toString());
            editor.putBoolean("REGISTERED",true);
            editor.apply();
            Intent intent = new Intent(Register.this,Home.class);
            startActivity(intent);
            finish();
        }

    }

}