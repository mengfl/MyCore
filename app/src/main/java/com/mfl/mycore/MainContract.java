package com.mfl.mycore;

import com.mfl.core.mvp.BaseMvpPresenter;
import com.mfl.core.mvp.BaseMvpView;

public interface MainContract {
    interface  View extends BaseMvpView {

    }
    abstract  class  Presenter extends BaseMvpPresenter<View> {
         abstract void requestLogin(String account,String pwd);
    }
}
