package jtli.com.simplereader.presenter.impl;


import java.util.List;

import javax.inject.Inject;

import jtli.com.simplereader.bean.gankio.GankIoDataBean;
import jtli.com.simplereader.http.service.GankIoService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.GankIoAndroidPresenter;


public class GankIoAndroidPresenterImpl extends BasePresenter<GankIoAndroidPresenter.View> implements GankIoAndroidPresenter.Presenter {
    private GankIoService mGankIoService;

    @Inject
    public GankIoAndroidPresenterImpl(GankIoService mGankIoService) {
        this.mGankIoService = mGankIoService;
    }


    @Override
    public void fetchGankIoData(int page, int pre_page) {
        invoke(mGankIoService.getGankIoData("Android",page,pre_page),new Callback<GankIoDataBean>(){
            @Override
            public void onResponse(GankIoDataBean data) {
                List<GankIoDataBean.ResultBean> results = data.getResults();
                checkState(results);
                mView.refreshView(results);

            }
        });
    }
}
