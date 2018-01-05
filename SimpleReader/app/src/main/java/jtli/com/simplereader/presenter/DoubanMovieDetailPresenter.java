package jtli.com.simplereader.presenter;

import jtli.com.simplereader.bean.douban.MovieDetailBean;

public interface DoubanMovieDetailPresenter {
    interface View extends BaseView<MovieDetailBean> {
    }

    interface Presenter {
        void fetchMovieDetail(String id);
    }
}
