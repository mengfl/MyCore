package com.mfl.core;

import android.content.Context;

import com.mfl.core.constant.DirectoryConstant;
import com.mfl.core.constant.NetConstant;
import com.mfl.core.net.HttpHelper;
import com.mfl.core.utils.ContextUtils;
import com.mfl.core.utils.LoggerUtil;

public class Core {

    public static void init(Context context,String softName,boolean isShowLog) {
        ContextUtils.init(context);
        LoggerUtil.init(isShowLog);
        DirectoryConstant.init(context,softName);
    }

    /**
     * 配置网络
     * @param baseUrl  地址
     * @param timeOut   超时时间
     * @param apiService  retrofit使用的api接口
     */
    public static void initNet(String baseUrl,int timeOut,Class apiService){
        if (baseUrl.endsWith("/")){
            NetConstant.BASE_URL=baseUrl;
        }else {
            NetConstant.BASE_URL= baseUrl.concat("/");
        }
        NetConstant.TIME_OUT=timeOut;
        HttpHelper.getInstance().setApiService(apiService);
    }

}
