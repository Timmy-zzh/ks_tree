package com.timmy.ks_tree.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.timmy.ks_tree.R;
import com.timmy.ks_tree.Test;
import com.timmy.ks_tree.hotfix.HotFixActivity;
import com.timmy.ks_tree.retrofit.RetrofitStu;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void testClassLoader() {
        ClassLoader classLoader = MainActivity.this.getClassLoader();
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
