package com.mfl.core.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class RxViewOperation {
    private final int repeatClickTime=2;   //暴力点击间隔
    private @NonNull LifecycleTransformer lifecycleTransformer;

    public RxViewOperation(@NonNull LifecycleTransformer lifecycleTransformer) {
        this.lifecycleTransformer = lifecycleTransformer;
    }

    /**
     * 防止暴力点击
     * @param view
     */
    @SuppressLint("CheckResult")
    public void avoidRepeatClick(View view,Consumer clickConsumer){
        RxView.clicks(view).throttleFirst(repeatClickTime,TimeUnit.SECONDS)
                .compose(lifecycleTransformer)
                .subscribe(clickConsumer);
    }

    public void moreListener(View view,Consumer consumer){

        Observable<Object> observable=RxView.clicks(view).share();
        observable.subscribe(consumer);

    }

    /**
     * 倒计时点击
     * @param view
     * @param countDownTime   倒计时前的准备工作
     * @param observer   倒计时过程中和完成后的工作
     */
    @SuppressLint("CheckResult")
    public void countDownClick(View view, final int countDownTime, Consumer countDownConsumer
            , final Observer observer ){

          RxView.clicks(view).throttleFirst(countDownTime,TimeUnit.SECONDS)
          .doOnNext(countDownConsumer)
                  .compose(lifecycleTransformer)
                  .subscribe(new Consumer() {
                      @Override
                      public void accept(Object o) throws Exception {
                          Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                                  .take(countDownTime) .compose(lifecycleTransformer)
                                  .subscribe(observer);

                      }
                  });
    }
    public void onDestory(){
        lifecycleTransformer=null;
    }
}
