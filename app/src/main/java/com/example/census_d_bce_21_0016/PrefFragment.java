package com.example.census_d_bce_21_0016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PrefFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_pref, container, false);
        Button buttonBgcolor = v.findViewById(R.id.buttonBgcolor);
        buttonBgcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ColorPicker.class);
                startActivity(intent);
            }
        });
        return v;
    }
}