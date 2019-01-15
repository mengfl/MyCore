package com.mfl.core.mvp.factory;



import com.mfl.core.mvp.BaseMvpPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateMvp {
    Class<? extends BaseMvpPresenter> value();

}


