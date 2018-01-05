package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.bean.topnews.NBAListBean;
import jtli.com.simplereader.http.service.TopNewsService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.NBAPresenter;

public class NBAPresenterImpl extends BasePresenter<NBAPresenter.View> implements NBAPresenter.Presenter {
    private TopNewsService mTopNewsService;

    @Inject
    public NBAPresenterImpl(TopNewsService mTopNewsService) {
        this.mTopNewsService = mTopNewsService;
    }

    @Override
    public void fetchNBAList(int id) {
        invoke(mTopNewsService.fetchNBA(id),new Callback<NBAListBean>());
    }
}
