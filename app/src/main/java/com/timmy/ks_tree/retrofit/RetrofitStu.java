package com.timmy.ks_tree.retrofit;

import com.timmy.ks_tree.bean.Banner;
import com.timmy.ks_tree.bean.HttpResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitStu {

    public void testRetrofit() {
        System.out.println("-----------");
        //创建Retrofit对象实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
//                .addCallAdapterFactory()
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /**
         * 使用动态代理创建请求API类
         */
        ApiServer apiServer = retrofit.create(ApiServer.class);

        /**
         * getBanner() 方法通过注解配置网络请求参数，并创建网络请求对象，
         * 返回的Call 内部包含了请求的信息，包括：
         * 1.请求url，method
         * headers，请求体等
         */
        Call<HttpResult<List<Banner>>> bannerCall = apiServer.getBanner();

        /**
         * 发起网络请求，服务端的响应数据是字节流，通过Retrofit框架自动转换成我们我们需要的对象实例
         */
        bannerCall.enqueue(new Callback<HttpResult<List<Banner>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<Banner>>> call, Response<HttpResult<List<Banner>>> response) {
                HttpResult<List<Banner>> body = response.body();
                List<Banner> bannerList = body.data;
                System.out.println(bannerList.toString());
            }

            @Override
            public void onFailure(Call<HttpResult<List<Banner>>> call, Throwable t) {

            }
        });
    }
}
