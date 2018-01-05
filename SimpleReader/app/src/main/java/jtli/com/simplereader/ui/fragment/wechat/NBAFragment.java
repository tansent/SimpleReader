package jtli.com.simplereader.ui.fragment.wechat;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.NBAAdapter;
import jtli.com.simplereader.bean.topnews.NBAListBean;
import jtli.com.simplereader.injector.component.fragment.DaggerNBAComponent;
import jtli.com.simplereader.injector.module.fragment.NBAModule;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.presenter.NBAPresenter;
import jtli.com.simplereader.presenter.impl.NBAPresenterImpl;
import jtli.com.simplereader.ui.activity.topnews.NBAActivity;
import jtli.com.simplereader.ui.fragment.BaseFragment;
import jtli.com.simplereader.view.EasyLoadMoreView;

/**
 * Created by Jingtian(Tansent).
 */

public class NBAFragment extends BaseFragment<NBAPresenterImpl> implements NBAPresenter.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener  {
    @BindView(R.id.rv_android)
    RecyclerView rvAndroid;

    @BindView(R.id.srl_android)
    SwipeRefreshLayout srlAndroid;

    private int index = 0;
    private boolean isRefresh = false;

    @Override
    protected void initInject() {
        DaggerNBAComponent.builder()
                .topNewsHttpModule(new TopNewsHttpModule())
                .nBAModule(new NBAModule())
                .build().injectNBA(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initView() {
        srlAndroid.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        rvAndroid.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAndroid.setAdapter(mAdapter);
        srlAndroid.setOnRefreshListener(this); //允许下拉刷新
//        mAdapter.setLoadMoreView(new EasyLoadMoreView()); //允许加载更多
//        mAdapter.setOnLoadMoreListener(this, rvAndroid);

        ((NBAAdapter)mAdapter).setOnItemClickListener(new NBAAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(String id, String imgUrl, View view) {
                startNBADetailActivity(id, imgUrl, view);
            }
        });
    }

    @Override
    protected void loadData() {
        mPresenter.fetchNBAList(index);
    }

    @Override
    public void refreshView(NBAListBean mData) {
        List<NBAListBean.NBABean> nbaBeenList = mData.getT1348649145984();
        if (isRefresh) { //下拉刷新
            srlAndroid.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
            isRefresh = false;
            mAdapter.setNewData(nbaBeenList);
        } else { //加载更多
            srlAndroid.setEnabled(true);
            index += 20;
            mAdapter.addData(nbaBeenList);
            mAdapter.loadMoreComplete();
        }
    }

    /**
     * 下拉刷新的具体实现
     * 若不实现此方法，进度条会不停地转
     */
    @Override
    public void onRefresh() {
        index = 0;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.fetchNBAList(index);
    }

    @Override
    public void onLoadMoreRequested() {
        if (index >= 60) {
            mAdapter.loadMoreEnd();
            srlAndroid.setEnabled(true);
        } else {
            loadData();
            srlAndroid.setEnabled(false);
        }
    }

    private void startNBADetailActivity(String id, String imgUrl, View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NBAActivity.class);
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
