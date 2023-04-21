package com.example.census_d_bce_21_0016;

import android.graphics.Bitmap;

public class ExampleItem {
    private String mImageResource;
    private String mLine1;
    private String mLine2;
    private String mLine3;
    public ExampleItem(String imageResource,String line1, String line2,String line3) {
        mImageResource = imageResource;
        mLine1 = line1;
        mLine2 = line2;
        mLine3 = line3;
    }
    public String getImageResource() {
        return mImageResource;
    }
    public String getLine1() {
        return mLine1;
    }

    public String getLine2() {
        return mLine2;
    }
    public String getLine3() {
        return mLine3;
    }
}
