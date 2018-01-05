package jtli.com.simplereader.presenter;

import java.util.List;

import jtli.com.simplereader.bean.zhihu.CommentBean;

/**
 * Created by Jingtian(Tansent).
 */

public interface ZhihuCommentPresenter {

    interface View extends BaseView<List<CommentBean.CommentsBean>> {
    }

    interface Presenter{
        void fetchLongCommentInfo(int id);
        void fetchShortCommentInfo(int id);
    }

}
