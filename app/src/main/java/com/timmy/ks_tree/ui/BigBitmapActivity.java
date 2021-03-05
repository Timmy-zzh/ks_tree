package com.timmy.ks_tree.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.timmy.ks_tree.R;

import java.io.IOException;
import java.io.InputStream;

public class BigBitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_bitmap);
        ImageView imageView = findViewById(R.id.iv);
//        imageView.setImageResource(R.mipmap.ic_big);
        try {
            InputStream is = getAssets().open("ic_big.jpg");
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = decoder.decodeRegion(new Rect(0, 0, 600, 600), options);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}