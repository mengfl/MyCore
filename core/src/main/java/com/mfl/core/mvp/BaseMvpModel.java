package com.mfl.core.mvp;


import com.mfl.core.net.ApiException;
import com.mfl.core.net.HttpHelper;
import com.mfl.core.net.HttpResult;
import com.mfl.core.utils.LoggerUtil;
import com.mfl.core.utils.RxTask;
import com.trello.rxlifecycle2.LifecycleTransformer;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 基类mvpmodel
 *
 * @param <T>
 */
public class BaseMvpModel<T> {
    protected T apiService;
    protected LifecycleTransformer lifecycleTransformer;
    public BaseMvpModel() {
        apiService = (T) HttpHelper.getInstance().getApiService();
    }

    public void setLifecycleTransformer(LifecycleTransformer lifecycleTransformer) {
        this.lifecycleTransformer = lifecycleTransformer;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

        @Override
        public T apply(@NonNull HttpResult<T> tHttpResult) throws ApiException {

            if (tHttpResult == null) {
                throw new ApiException(ApiException.JSON_FORMAT);
            } else if (HttpResult.SUCCESS != tHttpResult.getStatus()) {
                throw new ApiException(tHttpResult.getMessage());
            } else {
                return tHttpResult.getResult();
            }
        }
    }


    /**
     * 添加线程管理并订阅
     *
     * @param o
     * @param s
     */
    protected void toSubscribe(Flowable o, Subscriber s) {
        if (lifecycleTransformer==null){
            throw new NullPointerException("要在BaseMvpmodel中使用rxjava，请先给LifecycleTransformer赋值");
        }else {
            RxTask.doPressureTask(o,s,lifecycleTransformer);
        }
    }
    public void destory(){

        apiService=null;
        lifecycleTransformer=null;
    }
}
