package com.timmy.ks_tree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class Test {

//    public static void main(String[] args) {
//        Test test = new Test();
//        test.testRetrofit();

//        test1();
//        testOkhttp();
//        testGlide();
//        getSync("");
//        getAsync("");
//        testLock();
//        testThreadPool();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            testCollect();
//        }
//    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void testCollect() {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(3, "333");
        sparseArray.put(1, "111");
        sparseArray.put(2, "222");
        sparseArray.put(2, "213424");
        System.out.println(sparseArray.toString());
        String s = sparseArray.get(1);
        sparseArray.remove(1);

        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put("bbb", "222");
        arrayMap.put("aaa", "111");
        arrayMap.put("ccc", "3333");
        arrayMap.put("aaa", "45643");
        System.out.println(arrayMap.toString());
    }

    public void testGlide(Activity activity) {
        Glide.with(activity)
                .load("")
                .into(new ImageView(activity));
    }

    public void testOkhttp() {
        //1。创建OkHttpClient 对象，开发中使用单例
//        OkHttpClient client = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.build();

        //2。创建一个请求，每个请求是一个对象
        Request request = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();

        //3.1.同步请求
//        try {
//            Response response = client.newCall(request).execute();
//            String String = response.body().string();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //3.2.异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Tim", "onFailure:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("Tim", response.body().string());
            }
        });
    }

    public void test1(Activity activity) {
        activity.startActivity(new Intent());

        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };

        Message message = new Message();
        Message.obtain();
        handler.obtainMessage();

        handler.sendMessage(message);

        TextView textView = new TextView(activity);
        textView.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Context context = activity;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("", 0);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        getSync("");
        getAsync("");
        testLock();
        testThreadPool();
    }

    private void testThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4,
                100,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.AbortPolicy());
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void testLock() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(null, "123");

        Hashtable<Integer, String> hashtable = new Hashtable<>();
        hashMap.put(1, "234");

        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(2, "345");
    }

    private void getSync(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1.创建OkHttpClient对象
                    OkHttpClient client = new OkHttpClient();

                    //2.创建请求对象 Request
                    Request request = new Request.Builder()
                            .url(url)
                            .get()
                            .build();

                    //3.根据request创建 Call对象
                    Call call = client.newCall(request);

                    //4.调用execute方法，同步方式请求响应Response
                    Response response = call.execute();
                    if (response.isSuccessful()) {
//                        String string = response.body().string();
                        //获取响应数据，切换到主线程进行界面更新
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getAsync(String url) {
        //1.创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();

        //2.创建请求对象 Request
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        //3.根据request创建 Call对象
        Call call = client.newCall(request);

        //4.调用enqueue方法，异步请求网络，响应数据通过callback返回
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取响应数据，当前在子线程，需要切换到主线程进行界面更新
            }
        });
    }

}
