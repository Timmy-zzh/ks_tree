package com.timmy.ks_tree.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;
import android.widget.ImageView;

import com.timmy.ks_tree.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class BigBitmapActivity extends AppCompatActivity {

    private long lastFrameTimeNanos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_bitmap);
        ImageView imageView = findViewById(R.id.iv);

//        imageView.invalidate();
//        imageView.requestLayout();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
             @Override
            public void doFrame(long frameTimeNanos) {
                //接收到vsync信号
                // frameTimeNanos是当前刷新的时间
                System.out.println("frameTimeNanos:" + frameTimeNanos);
                if (lastFrameTimeNanos == 0) {
                    lastFrameTimeNanos = frameTimeNanos;
                }
                //差值
                long diff = TimeUnit.MILLISECONDS.convert(
                        frameTimeNanos - lastFrameTimeNanos,
                        TimeUnit.MILLISECONDS);
                if (diff > 16.6f) {
                    System.out.println("丢帧了");
                }

                lastFrameTimeNanos = frameTimeNanos;
                //从新注册，往Choreographer中添加监听
                Choreographer.getInstance().postFrameCallback(this);
            }
        });

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