package jtli.com.simplereader.presenter;

import jtli.com.simplereader.bean.douban.HotMovieBean;

public interface DouBanMovieTopPresenter {
    interface View extends BaseView<HotMovieBean> {
        void showLoadMoreError();
    }

    interface Presenter {
        void fetchMovieTop250(int start, int count);
    }
}
