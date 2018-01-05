package jtli.com.simplereader.presenter;


import java.util.List;

import jtli.com.simplereader.bean.wechat.WXItemBean;


public interface WeChatPresenter {
    interface View extends BaseView<List<WXItemBean>> {
    }

    interface Presenter {
        void fetchWeChatHot(int num, int page);
        void fetchWXHotSearch(int num, int page, String word);
    }
}
