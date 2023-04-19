package com.example.census_d_bce_21_0016;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    private EditText editText;
    private String password;
    private int errorCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onclick(View view) {
        editText = (EditText) findViewById(R.id.loginPassword);
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        password = sharedPreferences.getString("password","");

        if(password.equals(editText.getText().toString())){
            Intent intent = new Intent(Login.this,Home.class);
            startActivity(intent);
            finish();
        }else {
            editText.setError("worng password");
            errorCount++;
            if(errorCount>=3){
                Context context = getApplicationContext();
                CharSequence text = "Wrong password, Closing App";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Login.this.finish();
                System.exit(0);
            }
        }
    }
}