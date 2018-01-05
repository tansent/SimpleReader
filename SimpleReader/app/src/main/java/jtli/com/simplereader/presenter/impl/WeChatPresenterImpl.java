package jtli.com.simplereader.presenter.impl;

import java.util.List;

import javax.inject.Inject;

import jtli.com.simplereader.app.AppConstants;
import jtli.com.simplereader.bean.wechat.WXHttpResponse;
import jtli.com.simplereader.bean.wechat.WXItemBean;
import jtli.com.simplereader.http.service.WeChatService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.WeChatPresenter;

/**
 * Created by Jingtian(Tansent).
 */

public class WeChatPresenterImpl  extends BasePresenter<WeChatPresenter.View>  implements WeChatPresenter.Presenter {

    private WeChatService mWeChatService;

    @Inject
    public WeChatPresenterImpl(WeChatService mWeChatService) {
        this.mWeChatService = mWeChatService;
    }

    @Override
    public void fetchWeChatHot(int num, int page) {
        invoke(mWeChatService.fetchWXHot(AppConstants.KEY_API,num, page), new Callback<WXHttpResponse<List<WXItemBean>>>() {
            @Override
            public void onResponse(WXHttpResponse<List<WXItemBean>> data) {
                List<WXItemBean> newslist = data.getNewslist();
                checkState(newslist);
                mView.refreshView(newslist);
            }
        });
    }

    @Override
    public void fetchWXHotSearch(int num, int page, String word) {
        invoke(mWeChatService.fetchWXHotSearch(AppConstants.KEY_API,num, page, word), new Callback<WXHttpResponse<List<WXItemBean>>>() {
            @Override
            public void onResponse(WXHttpResponse<List<WXItemBean>> data) {
                List<WXItemBean> newslist = data.getNewslist();
                checkState(newslist);
                mView.refreshView(newslist);
            }
        });
    }

}
