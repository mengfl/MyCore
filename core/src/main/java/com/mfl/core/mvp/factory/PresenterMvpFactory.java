package com.mfl.core.mvp.factory;


import com.mfl.core.mvp.BaseMvpPresenter;
import com.mfl.core.mvp.BaseMvpView;

public interface PresenterMvpFactory<V extends BaseMvpView,P extends BaseMvpPresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();



}
