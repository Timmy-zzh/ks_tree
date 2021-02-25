package com.timmy.ks_tree.retrofit;

import com.timmy.ks_tree.bean.Banner;
import com.timmy.ks_tree.bean.HttpResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 定义访问服务器 api
 * 使用WanAndroid API
 * -https://www.wanandroid.com/blog/show/2
 */
public interface ApiServer {
    /**
     * 1.2 首页banner
     * https://www.wanandroid.com/banner/json
     * 方法：GET
     */
    @GET("banner/json")
    Call<HttpResult<List<Banner>>> getBanner();
}
