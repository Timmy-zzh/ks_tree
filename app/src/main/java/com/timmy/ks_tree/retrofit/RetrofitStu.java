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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Call<HttpResult<List<Banner>>> bannerCall = apiServer.getBanner();

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
