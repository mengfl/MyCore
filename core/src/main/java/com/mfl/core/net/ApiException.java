package com.mfl.core.net;

/**
 * API异常处理类
 */
public class ApiException extends RuntimeException {

    public static final int JSON_FORMAT=100;
    public static final int DATA_NULL=200;


    public  ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {
            case JSON_FORMAT:
                message = "数据格式异常";
                break;

        }
        return message;
    }
}

