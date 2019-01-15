package com.mfl.core.net;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by Administrator on 2017/10/10.
 */

public class NetLogger implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public Response intercept(Chain chain) throws IOException {
        //请求
        Request request = chain.request();
        Log.e("请求地址","--> " + request.method() + ' ' + request.url() + ' ' );
        RequestBody requestBody = request.body();
        if (requestBody!=null){
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            Log.e("请求参数","--> " + buffer.readString(charset));
        }
        //响应
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
         if (responseBody!=null){
             BufferedSource source = responseBody.source();
             source.request(Long.MAX_VALUE);
             Buffer buffer = source.buffer();
             Charset charset = UTF8;
             MediaType contentType = responseBody.contentType();
             if (contentType != null) {
                 charset = contentType.charset(UTF8);
             }
             Log.e("请求结果","--> " + buffer.clone().readString(charset));
         }
        return response;
    }

}
