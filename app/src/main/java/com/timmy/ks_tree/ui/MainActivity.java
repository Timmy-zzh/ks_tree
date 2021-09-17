package com.timmy.ks_tree.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.timmy.ks_tree.R;
import com.timmy.ks_tree.Test;
import com.timmy.ks_tree.hotfix.HotFixActivity;
import com.timmy.ks_tree.retrofit.RetrofitStu;

import java.util.concurrent.Semaphore;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Semaphore semaphore;
        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        TextView textView = new TextView(this);
        textView.setText("123");
    }

    private void testClassLoader() {
        ClassLoader classLoader = MainActivity.this.getClassLoader();
        try {
            classLoader.loadClass("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("Tim", classLoader.toString());
    }

    public void hotfix(View view) {
        startActivity(new Intent(this, HotFixActivity.class));
    }

    public void testOkHttp(View view) {
        Test test = new Test();
        test.testOkhttp();
    }

    public void testRetrofit(View view) {
        new RetrofitStu().testRetrofit();
    }

    public void testBitmap(View view) {
        startActivity(new Intent(this, BitmapActivity.class));
    }

    public void bigBitmap(View view) {
        startActivity(new Intent(this, BigBitmapActivity.class));
    }

    public void glide(View view) {
        startActivity(new Intent(this, GlideActivity.class));
    }
}
