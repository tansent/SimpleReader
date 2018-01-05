package jtli.com.simplereader.ui.fragment.home.child.zhihu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.zhihu.CommentBean;
import jtli.com.simplereader.injector.component.fragment.DaggerZhihuCommentComponent;
import jtli.com.simplereader.injector.module.fragment.ZhihuCommentModule;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.presenter.ZhihuCommentPresenter;
import jtli.com.simplereader.presenter.impl.ZhihuCommentPresenterImpl;
import jtli.com.simplereader.ui.activity.zhihu.ZhiHuCommentActivity;
import jtli.com.simplereader.ui.fragment.BaseFragment;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhiHuCommentFragment extends BaseFragment<ZhihuCommentPresenterImpl> implements ZhihuCommentPresenter.View  {

    @BindView(R.id.rv_zhihu_comment)
    RecyclerView rvZhihuComment;

    private boolean isShort;

    public static ZhiHuCommentFragment getInstance(boolean isShort) {
        ZhiHuCommentFragment instance = new ZhiHuCommentFragment();
        instance.isShort = isShort;
        return instance;
    }

    @Override
    protected void initInject() {
        DaggerZhihuCommentComponent.builder()
                .zhihuHttpModule(new ZhihuHttpModule())
                .zhihuCommentModule(new ZhihuCommentModule())
                .build().injectZhihuComment(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_comment;
    }

    @Override
    protected void initView() {
        rvZhihuComment.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void loadData() {
        ZhiHuCommentActivity mZhiHuCommentActivity = (ZhiHuCommentActivity) getActivity();
        int id = mZhiHuCommentActivity.getId();
        if (isShort) {//懒加载在可见的时候加载，会让非静态变量最终都是同一个值所以只能用静态变量。
            mPresenter.fetchShortCommentInfo(id);
        } else {
            mPresenter.fetchLongCommentInfo(id);
        }
    }

    @Override
    public void refreshView(List<CommentBean.CommentsBean> list) {
        mAdapter.setNewData(list);
        rvZhihuComment.setAdapter(mAdapter);
    }
}
