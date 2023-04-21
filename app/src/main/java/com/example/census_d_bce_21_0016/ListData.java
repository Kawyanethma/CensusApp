package com.example.census_d_bce_21_0016;

import android.content.SharedPreferences;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListData extends AppCompatActivity {
    ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;


    int countList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        sharedPreferences = getSharedPreferences("ShareList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onclickUpload(View view) {
//        sharedPreferences1= getSharedPreferences("SharedPrefCount", MODE_PRIVATE);
//        countList = sharedPreferences1.getInt("countList",0);
//
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//
//        rootRef.child("Users").child("List"+countList).setValue(mExampleList);
//

//        ListData.this.finish();
//        countList++;
//        sharedPreferences1 = getSharedPreferences("SharedPrefCount",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences1.edit();
//        editor.putInt("countList",countList);
//        editor.apply();
      DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        for(ExampleItem user : mExampleList) {
            String name = user.getLine1();
            String age = user.getLine2();
            String gender = user.getLine3();
            String image = user.getImageResource();

            rootRef.child("Users").child(name).child("Age").setValue(age);
            rootRef.child("Users").child(name).child("Gender").setValue(gender);
            rootRef.child("Users").child(name).child("Photo").setValue(image);
        }
        getSharedPreferences("ShareList", 0).edit().clear().apply();
        ListData.this.finish();
        Toast.makeText(ListData.this,"Data Uploaded", Toast.LENGTH_SHORT).show();

    }
}