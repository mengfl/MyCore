package com.mfl.mycore;



public class MainPresenter extends MainContract.Presenter {
    private MainModel mainModel;
    public MainPresenter() {
        mainModel=new MainModel();
    }

    @Override
    void requestLogin(String account, String pwd) {
          mainModel.setLifecycleTransformer(getMvpView().bindLifecycle());
          mainModel.jlkjkl(account,pwd);
    }

    @Override
    protected void destoryModel() {
        super.destoryModel();
        mainModel.destory();
        mainModel=null;
    }
}
