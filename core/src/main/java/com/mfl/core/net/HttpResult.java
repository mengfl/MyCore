package com.mfl.core.net;

/**
 *  json解析基类
 */
public class HttpResult <T> {
    public static final int SUCCESS=0;
    public static final int FAIL=0;

    private  int  status;
    private String message;
    private T result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
