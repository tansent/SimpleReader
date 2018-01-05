package jtli.com.simplereader.presenter.impl;

import javax.inject.Inject;

import jtli.com.simplereader.bean.zhihu.CommentBean;
import jtli.com.simplereader.http.service.ZhiHuService;
import jtli.com.simplereader.http.utils.Callback;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.presenter.ZhihuCommentPresenter;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhihuCommentPresenterImpl extends BasePresenter<ZhihuCommentPresenter.View> implements ZhihuCommentPresenter.Presenter {

    private ZhiHuService mZhiHuService;

    //在<>里面的类，必须先inject到dependency tree里面，指明参数，才能用dependency tree里的内容
    @Inject
    public ZhihuCommentPresenterImpl(ZhiHuService mZhiHuService) {
        this.mZhiHuService = mZhiHuService;
    }

    @Override
    public void fetchLongCommentInfo(int id) {
        invoke(mZhiHuService.fetchLongCommentInfo(id),new Callback<CommentBean>(){
            @Override
            public void onResponse(CommentBean data) {
                checkState(data.getComments());
                mView.refreshView(data.getComments());
            }
        });
    }

    @Override
    public void fetchShortCommentInfo(int id) {
        invoke(mZhiHuService.fetchShortCommentInfo(id),new Callback<CommentBean>(){
            @Override
            public void onResponse(CommentBean data) {
                checkState(data.getComments());
                mView.refreshView(data.getComments());
            }
        });
    }
}
