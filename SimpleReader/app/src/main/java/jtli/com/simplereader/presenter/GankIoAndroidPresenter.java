package jtli.com.simplereader.presenter;


import java.util.List;

import jtli.com.simplereader.bean.gankio.GankIoDataBean;


public interface GankIoAndroidPresenter {

    interface View extends BaseView<List<GankIoDataBean.ResultBean>> {
    }

    interface Presenter{
        void fetchGankIoData(int page, int pre_page);
    }
}
