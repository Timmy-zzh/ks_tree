package com.timmy.ks_tree.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.timmy.ks_tree.R;

public class GlideActivity extends AppCompatActivity {

    private ImageView imageView;
    private final static String url = "https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageView = findViewById(R.id.iv);
    }

    public void loadBitmap(View view) {
        RequestManager requestManager = Glide.with(this);
        DrawableTypeRequest<String> typeRequest = requestManager.load(url);
        Target<GlideDrawable> target = typeRequest.into(imageView);

//        Glide.with(this)
//                .load(url)
//                .placeholder(R.mipmap.ic_empty_default_2x)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .error(R.mipmap.ic_2)
//                .into(imageView);
    }
}