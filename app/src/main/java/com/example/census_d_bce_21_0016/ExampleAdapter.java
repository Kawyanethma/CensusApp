package com.example.census_d_bce_21_0016;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        public TextView mTextViewLine3;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.Name);
            mTextViewLine2 = itemView.findViewById(R.id.Age);
            mTextViewLine3 = itemView.findViewById(R.id.Gender);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
        holder.mTextViewLine3.setText(currentItem.getLine3());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
