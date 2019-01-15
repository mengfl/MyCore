package com.mfl.core.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;


import com.trello.rxlifecycle2.LifecycleTransformer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxTask {

    /**
     *  执行带泛型普通任务
     */
    public static void doTask(Observable o, Observer s, @NonNull LifecycleTransformer lifecycleTransformer) {
        o.compose(lifecycleTransformer)
                .subscribe(s);
    }
    /***
     * 执行和泛型无关的普通任务(任务线程默认在IO线程,观察线程默认在主线程)
     * @param task
     */
    public static void doTask(RxNoParamsClass task, @NonNull LifecycleTransformer lifecycleTransformer) {
          doTask(task, Schedulers.io(), AndroidSchedulers.mainThread(), lifecycleTransformer);
    }

    /***
     * 执行和泛型无关的普通任务
     * @param task
     * @param subscribeThread 任务线程
     * @param observeThread 观察者线程
     */
    public static void doTask(RxNoParamsClass task, Scheduler subscribeThread, Scheduler observeThread, @NonNull LifecycleTransformer lifecycleTransformer) {

        Observable.create(task).subscribeOn(subscribeThread)
                .unsubscribeOn(subscribeThread)
                .observeOn(observeThread)
                .compose(lifecycleTransformer)
                .subscribe(task);

    }
    public static void doTask(RxNoParamsClass task ) {

        Observable.create(task).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn( AndroidSchedulers.mainThread())
                .subscribe(task);
    }
    /**
     * 普通任务class
     */
    public abstract class RxNoParamsClass implements ObservableOnSubscribe<String>, Observer<String> {

        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            onDoTask();
            emitter.onNext("");
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String t) {
            onTaskCompleted();
        }

        @Override
        public void onError(Throwable e) {
           e.printStackTrace();
             throw  new IllegalStateException("RX异步操作异常"){};
        }

        @Override
        public void onComplete() {

        }

        abstract void onDoTask();

        abstract void onTaskCompleted();

    }


    /**
     * 执行带泛型背压任务(任务线程默认在IO线程,观察线程默认在主线程)
     * @param o
     * @param s
     * @param lifecycleTransformer
     */
    public static void doPressureTask(Flowable o, Subscriber s, @NonNull LifecycleTransformer lifecycleTransformer) {
        doPressureTask(o,s,Schedulers.io(), AndroidSchedulers.mainThread(),lifecycleTransformer);
    }

    public static void doPressureTask(Flowable o, Subscriber s,Scheduler subscribeThread, Scheduler observeThread ,@NonNull LifecycleTransformer lifecycleTransformer) {
        o.subscribeOn(subscribeThread)
                .unsubscribeOn(subscribeThread)
                .observeOn(observeThread)
                .compose(lifecycleTransformer)
                .subscribe(s);
    }

    /**
     * 执行和背压有关的无泛型任务(默认背压模式为LATEST,任务线程默认在IO线程,观察线程默认在主线程)
     * @param rxPressureClass
     * @param lifecycleTransformer
     */
    public static void doPressureTask(RxPressureClass rxPressureClass,@NonNull LifecycleTransformer lifecycleTransformer){
        doPressureTask(rxPressureClass,BackpressureStrategy.LATEST, Schedulers.io(), AndroidSchedulers.mainThread(),lifecycleTransformer);
    }


    /**
     * 执行和背压有关的无泛型任务(默认背压模式为LATEST)
     * @param rxPressureClass
     * @param subscribeThread
     * @param observeThread
     * @param lifecycleTransformer
     */
    public static void doPressureTask(RxPressureClass rxPressureClass,Scheduler subscribeThread, Scheduler observeThread,@NonNull LifecycleTransformer lifecycleTransformer){
        doPressureTask(rxPressureClass,BackpressureStrategy.LATEST, subscribeThread, observeThread,lifecycleTransformer);
    }

    /**
     * 执行和背压有关的无泛型任务(任务线程默认在IO线程,观察线程默认在主线程)
     * @param rxPressureClass
     * @param backpressureStrategy    DROP:缓存池满后丢掉再发的数据，直到缓存之有空间
     *                                LATEST：缓存池满后丢掉再发的数据，但会接收最后一条数据
     *                                BUFFER：缓存池满后继续接收，会导致OOM
     *                                MISSING：不指定策略
     *                                ERROR：缓存池满后抛出MissingBackpressureException异常
     * @param lifecycleTransformer
     */
    public static void doPressureTask(RxPressureClass rxPressureClass,BackpressureStrategy backpressureStrategy,@NonNull LifecycleTransformer lifecycleTransformer){
          doPressureTask(rxPressureClass,backpressureStrategy, Schedulers.io(), AndroidSchedulers.mainThread(),lifecycleTransformer);
    }
    /**
     * 执行和背压有关的无泛型任务
     * @param rxPressureClass
     * @param backpressureStrategy
     * @param subscribeThread
     * @param observeThread
     * @param lifecycleTransformer
     */
    public static void doPressureTask(RxPressureClass rxPressureClass,BackpressureStrategy backpressureStrategy,Scheduler subscribeThread, Scheduler observeThread,@NonNull LifecycleTransformer lifecycleTransformer){

        Flowable.create(rxPressureClass,backpressureStrategy)
                .subscribeOn(subscribeThread)
                .unsubscribeOn(subscribeThread)
                .observeOn(observeThread)
                .compose(lifecycleTransformer).subscribe(rxPressureClass);
    }

    /**
     * 背压任务clas
     */
    public abstract class RxPressureClass implements FlowableOnSubscribe<String>,FlowableSubscriber<String>{

        @Override
        public void subscribe(FlowableEmitter<String> emitter) throws Exception {
               onDoTask();
               emitter.onNext("");
        }

        @Override
        public void onSubscribe(Subscription s) {
            s.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(String s) {
            onTaskCompleted();
        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
            throw  new IllegalStateException("RX异步操作异常"){};
        }

        @Override
        public void onComplete() {

        }
        abstract void onDoTask();

        abstract void onTaskCompleted();
    }
}
