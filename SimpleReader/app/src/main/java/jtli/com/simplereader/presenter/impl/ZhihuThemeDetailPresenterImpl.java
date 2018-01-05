package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.bean.zhihu.SectionChildListBean;
import jtli.com.simplereader.bean.zhihu.ThemeChildListBean;
import jtli.com.simplereader.http.service.ZhiHuService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.ZhihuThemeDetailPresenter;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhihuThemeDetailPresenterImpl extends BasePresenter<ZhihuThemeDetailPresenter.View> implements ZhihuThemeDetailPresenter.Presenter {
    private ZhiHuService mZhiHuService;

    @Inject
    public ZhihuThemeDetailPresenterImpl(ZhiHuService mZhiHuService) {
        this.mZhiHuService = mZhiHuService;
    }

    //interface
    @Override
    public void fetchThemeChildList(int id) {
        invoke(mZhiHuService.fetchThemeChildList(id), new Callback<ThemeChildListBean>() {
            @Override
            public void onResponse(ThemeChildListBean data) {
                mView.refreshView(data);
            }
        });
    }

    @Override
    public void fetchSectionChildList(int id) {
        invoke(mZhiHuService.fetchSectionChildList(id), new Callback<SectionChildListBean>() {
            @Override
            public void onResponse(SectionChildListBean data) {
                mView.refreshSectionData(data);
            }
        });
    }
}
