package com.mfl.mycore;

import android.annotation.SuppressLint;
import android.util.Log;

import com.mfl.core.mvp.BaseMvpModel;
import com.mfl.core.net.ErrorSubscriber;
import com.mfl.core.net.ResponseListener;
import com.trello.rxlifecycle2.LifecycleTransformer;

public class MainModel  extends BaseMvpModel<MyApiService> {


    @SuppressLint("CheckResult")
    public void jlkjkl(String account, String pwd){
        toSubscribe( apiService.sendVerify(account,pwd).map(new HttpResultFunc<UserBean>()),
                new ErrorSubscriber<>(new ResponseListener<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        Log.e("MainModel", userBean.toString());
                    }

                    @Override
                    public void onFail(String failMessage) {
                        Log.e("MainModel", failMessage);
                    }

                    @Override
                    public void onFinish() {

                    }
                }));

    }
}
