package com.mfl.core.net;

/**
 * 网络结束监听
 */
public interface ResponseListener<T> {
    void onSuccess(T t);
    void onFail(String failMessage);
    void onFinish();
}
