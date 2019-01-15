package com.mfl.core.mvp.factory;


import com.mfl.core.mvp.BaseMvpPresenter;
import com.mfl.core.mvp.BaseMvpView;

public class PresenterMvpFactoryImpl<V extends BaseMvpView,P extends BaseMvpPresenter<V>>
        implements PresenterMvpFactory<V,P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;



    public PresenterMvpFactoryImpl(Class<P> mPresenterClass) {

        this.mPresenterClass = mPresenterClass;
    }


    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreateMvp(xx.class)注解");
        }

    }


    /**
     * 根据注解创建Presenter的工厂实现类
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V> 当前View实现的接口类型
     * @param <P> 当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends BaseMvpView, P extends BaseMvpPresenter<V>> PresenterMvpFactoryImpl<V,P> createFactory(Class viewClazz){
        CreateMvp annotation = (CreateMvp) viewClazz.getAnnotation(CreateMvp.class);
        Class<P> aClass = null;
        if(annotation != null){
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V,P>(aClass);
    }

}
