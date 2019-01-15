package com.mfl.core.net;

import com.mfl.core.constant.NetConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/9.
 */

public class HttpHelper<T >{

    private T t;
    private static HttpHelper mInstance;
   private int TIME_OUT=NetConstant.TIME_OUT;
   private String baseUrl=NetConstant.BASE_URL;
    public static HttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (HttpHelper.class) {
                if (mInstance == null)
                    mInstance = new HttpHelper();
            }
        }
        return mInstance;
    }
    Retrofit retrofit;
    OkHttpClient okHttpClient;
    private HttpHelper() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetLogger())
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

         retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public void setApiService(Class<T> tClass) {
         t=retrofit.create(tClass);
    }
    public T getApiService() {
        return t;
    }

    public int getTIME_OUT() {
        return TIME_OUT;
    }

    public void setTIME_OUT(int TIME_OUT) {
        this.TIME_OUT = TIME_OUT;

    }

    public String getBaseUrl() {
        return baseUrl == null ? "" : baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl == null ? "" : baseUrl;
    }
}