package com.example.census_d_bce_21_0016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.census_d_bce_21_0016.databinding.ActivityHomeBinding;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ColorPicker extends AppCompatActivity {
    ConstraintLayout mLayout;
    int mDefultColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        openColorPicker();

        mLayout = (ConstraintLayout) findViewById(R.id.layout);
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
        mDefultColor = sharedPreferences.getInt("color",0);
        if (mDefultColor==0)
            mDefultColor = ContextCompat.getColor(ColorPicker.this ,R.color.white);

        mLayout.setBackgroundColor(mDefultColor);
    }
    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Intent intent = new Intent(ColorPicker.this,Home.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefultColor = color;
                mLayout.setBackgroundColor(mDefultColor);
                SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("color",mDefultColor);
                editor.apply();
                Intent intent = new Intent(ColorPicker.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        colorPicker.show();
    }
}