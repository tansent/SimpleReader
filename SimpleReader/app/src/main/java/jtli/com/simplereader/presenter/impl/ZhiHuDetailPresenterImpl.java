package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.bean.zhihu.DetailExtraBean;
import jtli.com.simplereader.bean.zhihu.ZhihuDetailBean;
import jtli.com.simplereader.http.service.ZhiHuService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.ZhiHuDetailPresenter;

/**
 * Created by Jingtian(Tansent).
 * 用来做中转，
 * 1.得到数据（这里得到数据很直接，不用再写BO类）
 * 2.告诉view去做什么
 */

public class ZhiHuDetailPresenterImpl extends BasePresenter<ZhiHuDetailPresenter.View> implements ZhiHuDetailPresenter.Presenter  {
    private ZhiHuService mZhiHuService;

    @Inject
    public ZhiHuDetailPresenterImpl(ZhiHuService mZhiHuService) {
        this.mZhiHuService = mZhiHuService;
    }


    @Override
    public void fetchDetailInfo(int id) {
        invoke(mZhiHuService.fetchDetailInfo(id),new Callback<ZhihuDetailBean>(){
//            @Override
//            public void onResponse(ZhihuDetailBean data) {
//                super.onResponse(data);
//            }
            @Override
            public void onResponse(ZhihuDetailBean data) {
                mView.refreshView(data);
            }
        });
    }

    @Override
    public void fetchDetailExtraInfo(int id) {
        invoke(mZhiHuService.fetchDetailExtraInfo(id),new Callback<DetailExtraBean>(){
            @Override
            public void onResponse(DetailExtraBean data) {
                mView.showExtraInfo(data);
            }
        });
    }
}
