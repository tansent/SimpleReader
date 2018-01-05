package jtli.com.simplereader.presenter;


import jtli.com.simplereader.bean.topnews.NBAListBean;

public interface NBAPresenter {

    interface View extends BaseView<NBAListBean> {
    }

    interface Presenter {
        void fetchNBAList(int id);
    }

}
