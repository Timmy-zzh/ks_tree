package com.timmy.ks_tree.hotfix;

import androidx.appcompat.app.AppCompatActivity;
import dalvik.system.DexClassLoader;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.timmy.ks_tree.R;

import java.io.File;

public class HotFixActivity extends AppCompatActivity {

    private IDo iDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);
    }

    public void testHotFix(View view) {

        //hotfit jar包路径
        File jarFile = new File(Environment.getExternalStorageDirectory().getPath()
                + File.separator + "tim" + File.separator + "do_hotfix.jar");

        if (!jarFile.exists()) {
            iDo = new DoException();
            Toast.makeText(HotFixActivity.this, iDo.doSomething(), Toast.LENGTH_SHORT).show();
        } else {
            DexClassLoader dexClassLoader = new DexClassLoader(jarFile.getAbsolutePath(),
                    getExternalCacheDir().getAbsolutePath(),
                    null, getClassLoader());

            try {
                Class<?> aClass = dexClassLoader.loadClass("com.timmy.ks_tree.hotfix.DoHotfix");
                iDo = (IDo) aClass.newInstance();
                Toast.makeText(HotFixActivity.this, iDo.doSomething(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Tim", e.toString());
            }
        }

    }
}