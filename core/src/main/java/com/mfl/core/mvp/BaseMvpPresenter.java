package com.mfl.core.mvp;


import android.arch.lifecycle.LifecycleOwner;


/**
 *  基类presenter
 *  注：如果在子类中定义了baseMvpModel的子类，需重写destoryModel()方法销毁basemvpmodel来避免内存泄漏
 * @param <V>
 */
public class BaseMvpPresenter<V extends BaseMvpView> implements IPresenter {

    private V mView;
    private boolean isAttchView;
    protected LifecycleOwner owner;

    @Override
    public void onCreate(LifecycleOwner owner) {
        this.owner=owner;
    }

    @Override
    public void onDestory(LifecycleOwner owner) {
        onDetachMvpView();
        destoryModel();
        this.owner=null;
    }

   protected  void destoryModel(){

   }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        if ( isAttchView) {
            isAttchView = false;
        }
        mView = null;
    }

    /**
     * 绑定View
     */
    public void onAttachMvpView(V mvpView) {
        mView = mvpView;
        isAttchView=true;
    }
    /**
     * 获取V层接口View
     *
     * @return 返回当前MvpView
     */
    public V getMvpView() {
        if (isAttchView){
            return mView;
        }
        return null;
    }
}
