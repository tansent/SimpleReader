package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.app.AppConstants;
import jtli.com.simplereader.bean.topnews.NewsDetailBean;
import jtli.com.simplereader.http.Stateful;
import jtli.com.simplereader.http.service.TopNewsService;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.NBADetailPresenter;
import jtli.com.simplereader.utils.NewsJsonUtils;
import jtli.com.simplereader.utils.OkHttpUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class NBADetailPresenterImpl  extends BasePresenter<NBADetailPresenter.View> implements NBADetailPresenter.Presenter  {

    private TopNewsService mTopNewsService;

    @Inject
    public NBADetailPresenterImpl(TopNewsService mTopNewsService) {
        this.mTopNewsService = mTopNewsService;
    }

    @Override
    public void fetchNBADetail(final String id) {
        String url = getDetailUrl(id);
        //这里没有用rxJava，而是用OkHttp直接进行网络请求
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                if (response==null){
                    if (mView instanceof Stateful)
                        ((Stateful) mView).setState(AppConstants.STATE_EMPTY);
                }
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, id);
                mView.refreshView(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer("http://c.m.163.com/nc/article/");
        sb.append(docId).append("/full.html");
        return sb.toString();
    }

}
