package com.mfl.core.mvp;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfl.core.mvp.factory.PresenterMvpFactoryImpl;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BaseFragment<V extends BaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements LifecycleProvider<FragmentEvent> {
    protected BaseActivity mActivity;
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();
    protected P mPresenter;
    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Nullable
    @Override
    public View onCreateView(@android.support.annotation.NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResourceID(), container, false);
        getLifecycle().addObserver(getMvpPresenter());
        initOnCreate();
        setListener();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        initResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    private void initResume() {

    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
        mActivity=null;
    }
    private P getMvpPresenter() {
        if (mPresenter == null) {
            mPresenter = PresenterMvpFactoryImpl.<V, P>createFactory(getClass()).createMvpPresenter();
            mPresenter.onAttachMvpView((V) this);
        }
        return mPresenter;
    }
    public abstract int layoutResourceID();
    protected void initOnCreate() {

    }
    protected void setListener() {

    }


    //============================================================================================

    private BaseActivity.FragmentStackEntity mStackEntity;
    public final void setStackEntity(@NonNull BaseActivity.FragmentStackEntity stackEntity) {
        this.mStackEntity = stackEntity;
    }

    public final <T extends BaseFragment> void startFragment(Class<T> clazz) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final <T extends BaseFragment> void startFragment(Class<T> clazz, boolean stickyStack) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, stickyStack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private <T extends BaseFragment> void startFragment(T targetFragment, boolean stickyStack) {
        mActivity.startFragment(this, targetFragment, stickyStack);
    }

}