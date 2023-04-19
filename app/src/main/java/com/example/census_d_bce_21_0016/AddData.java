package com.example.census_d_bce_21_0016;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddData extends AppCompatActivity {
    EditText editTextName;
    EditText editTextAge;
    Button buttonSubmit;
    Button buttonAddImage;
    RadioButton genderradioButton;
    RadioGroup radioGroup;
    ArrayList<ExampleItem> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        editTextAge = (EditText) findViewById(R.id.editAge);
        editTextName = (EditText) findViewById(R.id.editName);
        buttonAddImage = (Button) findViewById(R.id.buttonImage);
        buttonSubmit = (Button) findViewById(R.id.Submit);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    public void onclickSubmit(View view) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selectedId);
        if(selectedId==-1){
            Toast.makeText(AddData.this,"Select your gender", Toast.LENGTH_SHORT).show();
        } else if (editTextName.getText().toString().isEmpty()) {
            Toast.makeText(AddData.this,"Type your name", Toast.LENGTH_SHORT).show();
        } else if (editTextAge.getText().toString().isEmpty()) {
            Toast.makeText(AddData.this,"Type your age", Toast.LENGTH_SHORT).show();
        } else{
           Toast.makeText(AddData.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
            Toast.makeText(AddData.this,"Done", Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences("ShareList", MODE_PRIVATE);
            String json = sharedPreferences.getString("task list", null);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
            mExampleList = gson.fromJson(json, type);
            if (mExampleList == null) {
                mExampleList = new ArrayList<>();
            }
            saveData();
            editTextAge.getText().clear();
            editTextName.getText().clear();
        }

    }

    private void saveData() {
        String line1 = editTextName.getText().toString();
        String line2 = editTextAge.getText().toString();
        String line3 = genderradioButton.getText().toString();

        mExampleList.add(new ExampleItem(line1,line2,line3));

        SharedPreferences sharedPreferences = getSharedPreferences("ShareList",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }


}