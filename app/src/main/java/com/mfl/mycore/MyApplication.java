package com.mfl.mycore;

import android.app.Application;

import com.mfl.core.Core;
import com.mfl.core.net.HttpHelper;
import com.mfl.core.utils.ContextUtils;
import com.mfl.core.utils.LoggerUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

         Core.init(this,"mfl_core",true);
         Core.initNet("http://192.168.10.57",15,MyApiService.class);
    }
}
