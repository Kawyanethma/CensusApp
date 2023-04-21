package com.example.census_d_bce_21_0016;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;

import android.os.StrictMode;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class AddData extends AppCompatActivity {
    EditText editTextName;
    EditText editTextAge;
    Button buttonSubmit;
    Button buttonAddImage;
    RadioButton genderradioButton;
    RadioGroup radioGroup;
    String encodedImage;
    ArrayList<ExampleItem> mExampleList;
    ImageView imageView;
    Intent camera;

    private static final int requestCamera =12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        editTextAge = (EditText) findViewById(R.id.editAge);
        editTextName = (EditText) findViewById(R.id.editName);
        buttonAddImage = (Button) findViewById(R.id.buttonImage);
        buttonSubmit = (Button) findViewById(R.id.Submit);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        imageView = (ImageView) findViewById(R.id.image);
        StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        buttonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((ActivityCompat.checkSelfPermission(
                        AddData.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{
                                Manifest.permission.CAMERA,
                        },123);
                    }
                }
                else{
                    camera=new Intent();
                    camera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera,118);
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==118&& resultCode==RESULT_OK){
            Bitmap photo= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            Toast.makeText(this, "Image saved in SharedPreferences", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "could not captured", Toast.LENGTH_SHORT).show();
        }
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
//           Toast.makeText(AddData.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
            Toast.makeText(AddData.this,"Data Added", Toast.LENGTH_SHORT).show();

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
            radioGroup.clearCheck();
        }

    }

    private void saveData() {
        String line1 = editTextName.getText().toString();
        String line2 = editTextAge.getText().toString();
        String line3 = genderradioButton.getText().toString();

        mExampleList.add(new ExampleItem(encodedImage,line1,line2,line3));

        SharedPreferences sharedPreferences = getSharedPreferences("ShareList",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }


}