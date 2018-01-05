package jtli.com.simplereader.presenter;

import jtli.com.simplereader.bean.topnews.NewsDetailBean;


public interface NBADetailPresenter {
    interface View extends BaseView<NewsDetailBean> {
    }

    interface Presenter{
        void fetchNBADetail(String id);
    }
}
