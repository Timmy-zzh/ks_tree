package com.timmy.ks_tree.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.timmy.ks_tree.R;

import java.io.IOException;
import java.io.InputStream;

public class BitmapActivity extends AppCompatActivity {

    private TextView tvContent;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_study);
        tvContent = findViewById(R.id.tv_content);
        imageView = findViewById(R.id.iv);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_glode);
//        imageView.setImageBitmap(bitmap);
//        //求bitmap占用的内存大小
//        int byteCount = 0;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//            byteCount = bitmap.getAllocationByteCount();
//        }
//        Log.e("Tim", "byteCount:" + byteCount);
//        tvContent.setText(String.valueOf(byteCount));

        loadAssets();
    }

    private void loadAssets() {
        try {
            InputStream is = getAssets().open("ic_glode_res.png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bitmap);
            //求bitmap占用的内存大小
            int byteCount = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                byteCount = bitmap.getAllocationByteCount();
            }
            Log.e("Tim", "byteCount:" + byteCount);
            tvContent.setText(String.valueOf(byteCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}