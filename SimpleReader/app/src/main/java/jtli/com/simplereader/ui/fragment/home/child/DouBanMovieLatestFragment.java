package jtli.com.simplereader.ui.fragment.home.child;

import android.animation.Animator;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.animation.BaseAnimation;

import java.util.List;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.MovieLatestAdapter;
import jtli.com.simplereader.bean.douban.HotMovieBean;
import jtli.com.simplereader.injector.component.fragment.DaggerDoubanMovieLatestComponent;
import jtli.com.simplereader.injector.module.fragment.DoubanMovieLatestModule;
import jtli.com.simplereader.injector.module.http.DoubanHttpModule;
import jtli.com.simplereader.presenter.DoubanHotMoviePresenter;
import jtli.com.simplereader.presenter.impl.DoubanHotMoviePresenterImpl;
import jtli.com.simplereader.ui.activity.douban.MovieTopDetailActivity;
import jtli.com.simplereader.ui.fragment.BaseFragment;

/**
 * Created by Jingtian(Tansent).
 */

public class DouBanMovieLatestFragment extends BaseFragment<DoubanHotMoviePresenterImpl> implements DoubanHotMoviePresenter.View {

    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;
    private List<HotMovieBean.SubjectsBean> subjectsBeanList;

    @Override
    protected void initInject() {
        DaggerDoubanMovieLatestComponent.builder()
                .doubanHttpModule(new DoubanHttpModule())
                .doubanMovieLatestModule(new DoubanMovieLatestModule())
                .build().injectDoubanMovieLatest(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        rcvActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvActivity.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{
                };
            }
        });
        ((MovieLatestAdapter) mAdapter).setOnItemClickListener(new MovieLatestAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(HotMovieBean.SubjectsBean positionData, View view) {
                startZhiHuDetailActivity(positionData, view);
            }
        });
    }

    @Override
    protected void loadData() {
        mPresenter.fetchHotMovie();
    }

    @Override
    public void refreshView(HotMovieBean data) {
        subjectsBeanList = data.getSubjects();
        mPresenter.checkState(subjectsBeanList);
        mAdapter.addData(subjectsBeanList);
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
