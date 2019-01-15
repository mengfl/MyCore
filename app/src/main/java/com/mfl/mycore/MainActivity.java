package com.mfl.mycore;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.mfl.core.mvp.BaseActivity;
import com.mfl.core.mvp.factory.CreateMvp;

import com.mfl.core.utils.RxViewOperation;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


@CreateMvp(MainPresenter.class)
public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements MainContract.View{
    private Button button1,button;

    @Override
    protected void addBindView() {

    }

    @Override
    protected int fragmentLayoutId() {
        return 0;
    }

    @Override
    protected int layoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate() {
        super.initOnCreate();

        button= findViewById(R.id.button);
        button1= findViewById(R.id.button1);

    }

    @Override
    protected void setListener() {
        super.setListener();
        rxViewOperation.avoidRepeatClick(button, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                mPresenter.requestLogin("admin","123456");
            }
        });
        rxViewOperation.avoidRepeatClick(button1, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        rxViewOperation.countDownClick(button, 300, new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                button.setEnabled(false);
//            }
//        }, new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//            @Override
//            public void onNext(Long aLong)
//            {
//                button.setText( "剩余" + (300 - aLong) + "秒");
//            }
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//            @Override
//            public void onComplete() {
//                button.setText("获取验证码");
//                button.setEnabled(true);
//            }
//        });
    }
}
