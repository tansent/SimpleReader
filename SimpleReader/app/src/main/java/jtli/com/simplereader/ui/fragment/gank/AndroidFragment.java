package jtli.com.simplereader.ui.fragment.gank;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.gankio.GankIoDataBean;
import jtli.com.simplereader.injector.component.fragment.DaggerAndroidComponent;
import jtli.com.simplereader.injector.module.fragment.AndroidModule;
import jtli.com.simplereader.injector.module.http.GankIoHttpModule;
import jtli.com.simplereader.presenter.GankIoAndroidPresenter;
import jtli.com.simplereader.presenter.impl.GankIoAndroidPresenterImpl;
import jtli.com.simplereader.ui.fragment.BaseFragment;
import jtli.com.simplereader.view.EasyLoadMoreView;

/**
 * Created by Jingtian(Tansent).
 *
 * 接口 SwipeRefreshLayout.OnRefreshListener 用于下拉刷新
 * 接口 BaseQuickAdapter.RequestLoadMoreListener 用于底部加载更多
 */

public class AndroidFragment extends BaseFragment<GankIoAndroidPresenterImpl> implements GankIoAndroidPresenter.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.rv_android)
    RecyclerView rvAndroid;

    @BindView(R.id.srl_android)
    SwipeRefreshLayout srlAndroid;

    private int page;
    private final static int PRE_PAGE = 10;
    private List<GankIoDataBean.ResultBean> data;

    private boolean isRefresh = false;

    @Override
    protected void initInject() {
        DaggerAndroidComponent.builder()
                .gankIoHttpModule(new GankIoHttpModule())
                .androidModule(new AndroidModule())
                .build().injectAndroid(this);
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
        srlAndroid.setOnRefreshListener(this);
        mAdapter.setLoadMoreView(new EasyLoadMoreView());
        mAdapter.setOnLoadMoreListener(this,rvAndroid);
    }

    @Override
    protected void loadData() {
        mPresenter.fetchGankIoData(page,PRE_PAGE);
    }


    @Override
    public void refreshView(List<GankIoDataBean.ResultBean> data) {
        if (isRefresh){
            srlAndroid.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
            isRefresh = false;
            mAdapter.setNewData(data);
        }else{
            srlAndroid.setEnabled(true);
            page++;
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        page = 0;
        isRefresh =true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.fetchGankIoData(page,PRE_PAGE);
    }

    @Override
    public void onLoadMoreRequested() {
        if (page >= 6) {
            mAdapter.loadMoreEnd();
            srlAndroid.setEnabled(true);
        } else {
            loadData();
            srlAndroid.setEnabled(false);
        }
    }
}
