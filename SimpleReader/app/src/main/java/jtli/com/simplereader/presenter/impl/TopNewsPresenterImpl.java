package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.app.AppConstants;
import jtli.com.simplereader.bean.topnews.NewsDetailBean;
import jtli.com.simplereader.bean.topnews.NewsListBean;
import jtli.com.simplereader.http.Stateful;
import jtli.com.simplereader.http.service.TopNewsService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.TopNewsPresenter;
import jtli.com.simplereader.utils.NewsJsonUtils;
import jtli.com.simplereader.utils.OkHttpUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class TopNewsPresenterImpl extends BasePresenter<TopNewsPresenter.View> implements TopNewsPresenter.Presenter {

    private TopNewsService mTopNewsService;

    @Inject
    public TopNewsPresenterImpl(TopNewsService mTopNewsService) {
        this.mTopNewsService = mTopNewsService;
    }

    @Override
    public void fetchTopNewsList(int id) {
        invoke(mTopNewsService.fetchNews(id),new Callback<NewsListBean>(){
            @Override
            public void onResponse(NewsListBean data) {
                checkState(data.getNewsList());
                mView.refreshView(data);
            }
        });
    }

    //------------------for TopNewsActivity

    public void getDescrible(final String docid) {
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                if (response==null){
                    if (mView instanceof Stateful)
                        ((Stateful) mView).setState(AppConstants.STATE_EMPTY);
                }
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                ((TopNewsPresenter.ViewActivity) mView).refreshActivityView(newsDetailBean);
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
