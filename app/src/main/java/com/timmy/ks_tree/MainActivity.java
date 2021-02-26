package com.timmy.ks_tree;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    public void test(View view) {
//        testClassLoader();
        Test test = new Test();
        test.testOkhttp();
//        new RetrofitStu().testRetrofit();
    }
}
