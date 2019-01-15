package com.mfl.core.mvp;


import com.trello.rxlifecycle2.LifecycleTransformer;

public interface BaseMvpView {
    LifecycleTransformer bindLifecycle();
}
