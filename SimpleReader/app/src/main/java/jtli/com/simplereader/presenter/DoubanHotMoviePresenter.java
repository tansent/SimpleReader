package jtli.com.simplereader.presenter;


import jtli.com.simplereader.bean.douban.HotMovieBean;

public interface DoubanHotMoviePresenter {
    interface View extends BaseView<HotMovieBean> {
    }

    interface Presenter {
        void fetchHotMovie();
    }
}
