package com.timmy.ks_tree.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.timmy.ks_tree.R;

import java.io.IOException;
import java.io.InputStream;

public class BitmapActivity extends AppCompatActivity {

    private TextView tvContent;
    private ImageView imageView;
    private ImageView ivSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_study);
        tvContent = findViewById(R.id.tv_content);
        imageView = findViewById(R.id.iv);
        ivSwitch = findViewById(R.id.iv_switch);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 2;
//        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_glode, options);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_glode);

        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        Log.e("Tim", "outWidth:" + outWidth + " ,outHeight:" + outHeight);
        if (bitmap == null) {
            Log.e("Tim", "bitmap == null");
        }
        imageView.setImageBitmap(bitmap);
        //求bitmap占用的内存大小
        int byteCount = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            byteCount = bitmap.getAllocationByteCount();
            byteCount = bitmap.getByteCount();
        }
        Log.e("Tim", "byteCount:" + byteCount);
        tvContent.setText(String.valueOf(byteCount));

//        loadAssets();
        loadDefaultBitmap();
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


    int resIndex = 0;
    int[] resIds = {R.mipmap.ic_1, R.mipmap.ic_2};
    Bitmap reuseBitmap;

    private void loadDefaultBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        reuseBitmap = BitmapFactory.decodeResource(getResources(), resIds[resIndex]);
    }

    public void switchBitmap(View view) {
        ivSwitch.setImageBitmap(getBitmap());
    }

    private Bitmap getBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resIds[resIndex % 2]);

        if (canUseBeforeBitmap(reuseBitmap, options)) {
            Log.e("Tim", "reuse");
            options.inMutable = true;
            options.inBitmap = reuseBitmap;
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resIds[resIndex++ % 2], options);
    }

    /**
     * 当新图片的内存 《= 可复用图片占用内存时，才返回true
     *
     * @param oldBitmap     已经存在的图片
     * @param targetOptions 新图片的options
     * @return
     */
    private boolean canUseBeforeBitmap(Bitmap oldBitmap, BitmapFactory.Options targetOptions) {
        int width = targetOptions.outWidth / Math.max(targetOptions.inSampleSize, 1);
        int height = targetOptions.outHeight / Math.max(targetOptions.inSampleSize, 1);
        //新图片的大小
        int byteCount = width * height * getBytePerPixel(targetOptions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return byteCount <= oldBitmap.getAllocationByteCount();
        } else {
            return byteCount <= oldBitmap.getByteCount();
        }
    }

    private int getBytePerPixel(BitmapFactory.Options targetOptions) {
        int bytePerPixel;// 每个像素需要几个字节存储
        switch (targetOptions.inPreferredConfig) {
            case ALPHA_8:
                bytePerPixel = 1;
                break;
            case RGB_565:
            case ARGB_4444:
                bytePerPixel = 2;
                break;
            default:
                bytePerPixel = 4;
                break;
        }
        return bytePerPixel;
    }
}