package com.timmy.ks_tree;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.timmy.ks_tree.hotfix.HotFixActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testClassLoader();
    }

    private void testClassLoader() {
        ClassLoader classLoader = MainActivity.this.getClassLoader();
        Log.e("Tim", classLoader.toString());
    }

    public void hotfix(View view) {
        startActivity(new Intent(this, HotFixActivity.class));
    }
}
