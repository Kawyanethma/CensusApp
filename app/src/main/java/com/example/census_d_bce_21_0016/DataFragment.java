package com.example.census_d_bce_21_0016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DataFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_data, container, false);
        Button buttonAddData = v.findViewById(R.id.buttonAddData);
        Button buttonListData = v.findViewById(R.id.buttonUploadCloud);
        buttonAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AddData.class);
                startActivity(intent);
            }
        });
        buttonListData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ListData.class);
                startActivity(intent);
            }
        });
        return v;
    }
}