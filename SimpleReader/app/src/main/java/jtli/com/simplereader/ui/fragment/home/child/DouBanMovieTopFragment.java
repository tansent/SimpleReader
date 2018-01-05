package jtli.com.simplereader.ui.fragment.home.child;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.MovieTopAdapter;
import jtli.com.simplereader.bean.douban.HotMovieBean;
import jtli.com.simplereader.injector.component.fragment.DaggerDoubanMovieTopComponent;
import jtli.com.simplereader.injector.module.fragment.DoubanMovieTopModule;
import jtli.com.simplereader.injector.module.fragment.TopNewsModule;
import jtli.com.simplereader.injector.module.http.DoubanHttpModule;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.presenter.DouBanMovieTopPresenter;
import jtli.com.simplereader.presenter.impl.DouBanMovieTopPresenterImpl;
import jtli.com.simplereader.ui.activity.douban.MovieTopDetailActivity;
import jtli.com.simplereader.ui.fragment.BaseFragment;
import jtli.com.simplereader.view.EasyLoadMoreView;

/**
 * Created by Jingtian(Tansent).
 */

public class DouBanMovieTopFragment extends BaseFragment<DouBanMovieTopPresenterImpl> implements DouBanMovieTopPresenter.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;
    private List<HotMovieBean.SubjectsBean> subjectsList;

    private EasyLoadMoreView easyLoadMoreView;
    private static final int TOTAL_COUNTER = 10;
    private int mCurrentCounter = 0;

    @Override
    protected void initInject() {
        DaggerDoubanMovieTopComponent.builder()
                .doubanHttpModule(new DoubanHttpModule())
                .doubanMovieTopModule(new DoubanMovieTopModule())
                .build().injectDoubanMovieTop(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
//        mAdapter = new MovieTopAdapter(subjectsList);
        //load more
        easyLoadMoreView = new EasyLoadMoreView();
        mAdapter.setLoadMoreView(easyLoadMoreView);
        mAdapter.setOnLoadMoreListener(this, rcvActivity);

        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rcvActivity.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rcvActivity.setAdapter(mAdapter);
        ((MovieTopAdapter) mAdapter).setOnItemClickListener(new MovieTopAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(HotMovieBean.SubjectsBean positionData, View view) {
                startZhiHuDetailActivity(positionData, view);
            }
        });
    }

    @Override
    protected void loadData() {
        mPresenter.fetchMovieTop250(mCurrentCounter, mCurrentCounter + TOTAL_COUNTER >= 250 ? 250 : mCurrentCounter + TOTAL_COUNTER);
    }

    @Override
    public void refreshView(HotMovieBean data) {
        subjectsList = data.getSubjects();
        mPresenter.checkState(subjectsList);
        mAdapter.addData(subjectsList);
        mCurrentCounter = mAdapter.getData().size();
        mAdapter.loadMoreComplete();
    }

    @Override
    public void showLoadMoreError() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mCurrentCounter >= 250) {
            mAdapter.loadMoreEnd();
        } else {
            loadData();
        }
    }

    private void startZhiHuDetailActivity(HotMovieBean.SubjectsBean positionData, View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), MovieTopDetailActivity.class);
        intent.putExtra("bean", positionData);
        /**
         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
         *     <android.support.design.widget.AppBarLayout
         android:transitionName="zhihu_detail_title"
         android:fitsSystemWindows="true">
         跳转到目标ImageView不能是addview进来的
         */
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                view, getActivity().getResources().getString(R.string.douban_detail_iamge));
        getActivity().startActivity(intent, options.toBundle());
    }


}
