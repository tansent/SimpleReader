package jtli.com.simplereader.presenter.impl;


import javax.inject.Inject;

import jtli.com.simplereader.bean.douban.HotMovieBean;
import jtli.com.simplereader.http.service.DoubanService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.DoubanHotMoviePresenter;


public class DoubanHotMoviePresenterImpl extends BasePresenter<DoubanHotMoviePresenter.View> implements  DoubanHotMoviePresenter.Presenter {
    private DoubanService mDoubanService;

    @Inject
    public DoubanHotMoviePresenterImpl(DoubanService mDoubanService) {
        this.mDoubanService = mDoubanService;
    }
    @Override
    public void fetchHotMovie() {
        invoke(mDoubanService.fetchHotMovie(),new Callback<HotMovieBean>(){});
    }
}
