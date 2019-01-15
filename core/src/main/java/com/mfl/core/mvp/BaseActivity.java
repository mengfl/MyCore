package com.mfl.core.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.Gson;
import com.mfl.core.mvp.factory.PresenterMvpFactoryImpl;
import com.mfl.core.utils.ActivityUtils;
import com.mfl.core.utils.RxViewOperation;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BaseActivity<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends FragmentActivity
        implements LifecycleProvider<ActivityEvent> ,BaseMvpView{

    protected P mPresenter;
    protected RxViewOperation rxViewOperation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResourceID());
        addBindView();
        ActivityUtils.getAppManager().addActivity(this);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        getLifecycle().addObserver(getMvpPresenter());
        rxViewOperation=new RxViewOperation(bindLifecycle());
        initOnCreate();
        setListener();
    }

    protected abstract void addBindView();


    protected abstract int fragmentLayoutId();

    protected abstract int layoutResourceID();

    protected void initOnCreate() {

    }

    protected void setListener() {

    }

    protected void initResume() {

    }
    private P getMvpPresenter() {
        if (mPresenter == null) {
            mPresenter = PresenterMvpFactoryImpl.<V, P>createFactory(getClass()).createMvpPresenter();
            mPresenter.onAttachMvpView((V) this);
        }
        return mPresenter;
    }
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult

    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        initResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        ActivityUtils.getAppManager().finishActivity(this);
        rxViewOperation.onDestory();
        rxViewOperation=null;
        super.onDestroy();

    }

    //============================================================================================

    public void skipActivity(BaseActivity aty, Class<?> cls) {
        this.showActivity(aty, cls);
        aty.finish();
    }
    public void showActivity(BaseActivity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    private AtomicInteger mAtomicInteger = new AtomicInteger();
    private List<BaseFragment> mFragmentStack = new ArrayList<BaseFragment>();
    private Map<BaseFragment, FragmentStackEntity> mFragmentEntityMap = new HashMap<BaseFragment, FragmentStackEntity>();

    public static class FragmentStackEntity {
        private FragmentStackEntity() {
        }

        private boolean isSticky = false;


    }
    public final <T extends BaseFragment> void startFragment(Class<T> clazz) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(null, targetFragment, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final <T extends BaseFragment> void startFragment(Class<T> clazz, boolean stickyStack) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(null, targetFragment, stickyStack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public <T extends BaseFragment> void startFragment(T nowFragment, T targetFragment, boolean stickyStack) {


        FragmentStackEntity fragmentStackEntity = new FragmentStackEntity();
        fragmentStackEntity.isSticky = stickyStack;

        targetFragment.setStackEntity(fragmentStackEntity);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (nowFragment != null) {
            if (mFragmentEntityMap.get(nowFragment).isSticky) {
                nowFragment.onPause();
                nowFragment.onStop();
                fragmentTransaction.hide(nowFragment);
            } else {
                fragmentTransaction.remove(nowFragment);
                fragmentTransaction.commit();
                mFragmentStack.remove(nowFragment);
                mFragmentEntityMap.remove(nowFragment);
                fragmentTransaction = fragmentManager.beginTransaction();
            }
        }
        String fragmentTag = targetFragment.getClass().getSimpleName() + mAtomicInteger.incrementAndGet();

        if (mFragmentStack.size()>1){
            BaseFragment outFragment = mFragmentStack.get(mFragmentStack.size() - 1);
            mFragmentStack.remove(outFragment);
            mFragmentEntityMap.remove(outFragment);
            fragmentTransaction.remove(outFragment);

        }
        fragmentTransaction.replace(fragmentLayoutId(), targetFragment, fragmentTag);
        fragmentTransaction.commitAllowingStateLoss();

        mFragmentStack.add(targetFragment);
        mFragmentEntityMap.put(targetFragment, fragmentStackEntity);


    }
}
