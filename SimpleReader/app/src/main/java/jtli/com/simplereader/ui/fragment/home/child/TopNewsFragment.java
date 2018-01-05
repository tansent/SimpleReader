package jtli.com.simplereader.ui.fragment.home.child;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blankj.utilcode.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.TopNewsAdapter;
import jtli.com.simplereader.bean.topnews.NewsListBean;
import jtli.com.simplereader.injector.component.fragment.DaggerTopNewsComponent;
import jtli.com.simplereader.injector.module.fragment.TopNewsModule;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.presenter.TopNewsPresenter;
import jtli.com.simplereader.presenter.impl.TopNewsPresenterImpl;
import jtli.com.simplereader.ui.activity.topnews.TopNewsActivity;
import jtli.com.simplereader.ui.fragment.BaseFragment;
import jtli.com.simplereader.view.EasyLoadMoreView;

/**
 * Created by Jingtian(Tansent).
 */

public class TopNewsFragment extends BaseFragment<TopNewsPresenterImpl> implements TopNewsPresenter.View, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity; //recycleView
    private int currentIndex = 0;
    private EasyLoadMoreView easyLoadMoreView;
    private ArrayList<NewsListBean.NewsBean> newsList;
    private int index;

    @Override
    protected void initInject() {
        DaggerTopNewsComponent.builder()
                .topNewsHttpModule(new TopNewsHttpModule())
                .topNewsModule(new TopNewsModule())
                .build().injectTopNews(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        easyLoadMoreView = new EasyLoadMoreView();
        mAdapter.setLoadMoreView(easyLoadMoreView);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN); //.SLIDEIN_LEFT
        mAdapter.setOnLoadMoreListener(this, rcvActivity);
        rcvActivity.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rcvActivity.setAdapter(mAdapter);
        ((TopNewsAdapter)mAdapter).setOnItemClickListener(new TopNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(String id, String imgUrl, View view) {
                startZhiHuDetailActivity(id, imgUrl, view);
            }
        });

    }

    @Override
    protected void loadData() {
        currentIndex = 0;//为了以后写下拉刷新 不然可以直接用这个方法。
        mPresenter.fetchTopNewsList(currentIndex);
    }


    @Override
    public void refreshView(NewsListBean data) {
        LogUtils.e("aaaacurrentIndex"+currentIndex);

        newsList = data.getNewsList();
        mAdapter.addData(newsList);
        index+=1;
        currentIndex = mAdapter.getData().size()-2*index;
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {//默认滑动到最后一个item时候调用此方法，可以通过setAutoLoadMoreSize(int);
        // 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
//         mQuickAdapter.setAutoLoadMoreSize(int);

//        if (currentIndex >= 60) {
//            mAdapter.loadMoreEnd();
//        } else {
//            mPresenter.fetchTopNewsList(currentIndex);
//        }
    }


    private void startZhiHuDetailActivity(String id, String imgUrl, View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), TopNewsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("url", imgUrl);
        /**
         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
         *     <android.support.design.widget.AppBarLayout
         android:transitionName="zhihu_detail_title"
         android:fitsSystemWindows="true">
         */
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                view, getActivity().getResources().getString(R.string.zhihu_detail_title));
        getActivity().startActivity(intent, options.toBundle());
    }
}
