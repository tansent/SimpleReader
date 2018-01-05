package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.bean.zhihu.DailyListBean;
import jtli.com.simplereader.bean.zhihu.ThemeChildListBean;
import jtli.com.simplereader.http.service.ZhiHuService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.ZhihuDailyListPresenter;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhihuDailyListPresenterImpl  extends BasePresenter<ZhihuDailyListPresenter.View> implements ZhihuDailyListPresenter.Presenter  {

    private ZhiHuService mZhiHuService;

    @Inject
    public ZhihuDailyListPresenterImpl(ZhiHuService mZhiHuService) {
        this.mZhiHuService = mZhiHuService;
    }

    @Override
    public void fetchZhihuDailyList() {
        invoke(mZhiHuService.fetchDailyList(), new Callback<DailyListBean>() {
            @Override
            public void onResponse(DailyListBean data) {
                mView.refreshView(data.getStories());
            }
        });
    }
}
