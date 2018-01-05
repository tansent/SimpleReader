package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.bean.douban.MovieDetailBean;
import jtli.com.simplereader.http.service.DoubanService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.DoubanMovieDetailPresenter;


public class DoubanMovieDetailPresenterImpl extends BasePresenter<DoubanMovieDetailPresenter.View> implements DoubanMovieDetailPresenter.Presenter {
    private DoubanService mDoubanService;

    @Inject
    public DoubanMovieDetailPresenterImpl(DoubanService mDoubanService) {
        this.mDoubanService = mDoubanService;
    }

    //only get data from the internet. Not invoked by the view
    @Override
    public void fetchMovieDetail(String id) {
        invoke(mDoubanService.fetchMovieDetail(id),new Callback<MovieDetailBean>(){
        });
    }
}
