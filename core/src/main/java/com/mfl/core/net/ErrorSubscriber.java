package com.mfl.core.net;

import com.google.gson.JsonSyntaxException;
import com.mfl.core.utils.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.SocketException;
import java.util.concurrent.TimeoutException;

import io.reactivex.annotations.NonNull;


/**
 * 自定义被观察者，用于控制progressdialog及任务完成后的处理
 */
public class ErrorSubscriber<T>   implements  Subscriber<T> {

    private boolean isError=true;  //是否需要控制error操作
    private ResponseListener<T> mResponseListener;

    private Subscription subscription;


    public ErrorSubscriber(ResponseListener<T> mResponseListener) {
        this(mResponseListener, true);
    }


    public ErrorSubscriber(ResponseListener<T> mResponseListener, boolean isErrorControll) {
        this.mResponseListener = mResponseListener;
        this.isError=isErrorControll;
    }

    @Override
    public void onSubscribe(Subscription s) {
        subscription=s;
        s.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(@NonNull T t) {
        mResponseListener.onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        mResponseListener.onFinish();
        if (e instanceof ApiException){
            mResponseListener.onFail(e.getMessage());
        }
        if (isError){
            if(e instanceof JsonSyntaxException){
                ToastUtils.showShortSafe("格式异常");
            }else if(e instanceof TimeoutException){
                ToastUtils.showShortSafe("连接超时");
            }else if(e instanceof SocketException){
                ToastUtils.showShortSafe("连接异常");
            }else if(e instanceof ClassCastException){
                ToastUtils.showShortSafe("类型转换异常");
            }
        }
    }

    @Override
    public void onComplete() {
          mResponseListener.onFinish();
    }



}
