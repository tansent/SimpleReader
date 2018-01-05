package jtli.com.simplereader.ui.activity.zhihu;


import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.ZhihuDailyListAdapter;
import jtli.com.simplereader.adapter.ZhihuThemeAdapter;
import jtli.com.simplereader.bean.zhihu.DailyListBean;
import jtli.com.simplereader.injector.component.activity.DaggerZhihuDailyListComponent;
import jtli.com.simplereader.injector.component.activity.ZhihuDailyListComponent;
import jtli.com.simplereader.injector.component.fragment.DaggerZhihuHomeComponent;
import jtli.com.simplereader.injector.module.activity.ZhihuDailyListModule;
import jtli.com.simplereader.injector.module.fragment.ZhihuHomeModule;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.presenter.ZhihuDailyListPresenter;
import jtli.com.simplereader.presenter.impl.ZhihuDailyListPresenterImpl;
import jtli.com.simplereader.ui.activity.base.LoadingBaseActivity;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhihuDailyListActivity extends LoadingBaseActivity<ZhihuDailyListPresenterImpl> implements ZhihuDailyListPresenter.View  {
    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;
    @Inject
    ZhihuDailyListAdapter zhihuDailyListAdapter;

    protected Toolbar toolbar_zhuhu_daily;

    List<DailyListBean.StoriesBean> storyBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_daily_list;
    }

    @Override
    protected void initUI() {
        toolbar_zhuhu_daily = (Toolbar) findViewById(R.id.toolbar_zhuhu_daily);
    }

    @Override
    protected void initInject() {
        DaggerZhihuDailyListComponent.builder()
                .zhihuHttpModule(new ZhihuHttpModule())
                .zhihuDailyListModule(new ZhihuDailyListModule())
                .build().injectZhihuDailyList(this);
    }

    @Override
    public int setFrameLayoutId() {
        return R.id.fl_zhihu_daily_list;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {

        zhihuDailyListAdapter = new ZhihuDailyListAdapter(storyBeanList);
        zhihuDailyListAdapter.setOnZhihuThemeItemClick(new ZhihuDailyListAdapter.OnItemClick() {
            @Override
            public void onItemClick(int id, View view) {
                startZhiHuDetailActivity(id, view);
            }
        });
        rcvActivity.setAdapter(zhihuDailyListAdapter);
        rcvActivity.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void loadData() {
        mPresenter.fetchZhihuDailyList();
    }

    @Override
    public void refreshView(List<DailyListBean.StoriesBean> data) {
        storyBeanList = data;
        zhihuDailyListAdapter.setNewData(data);
        zhihuDailyListAdapter.notifyDataSetChanged();
        setToolBar(toolbar_zhuhu_daily, "知乎日报");

    }


    private void startZhiHuDetailActivity(int id, View view) {
        Intent intent = new Intent();
        intent.setClass(this, ZhiHuDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("isNotTransition", true);
        /**
         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
         *     <android.support.design.widget.AppBarLayout
         android:transitionName="zhihu_detail_title"
         android:fitsSystemWindows="true">
         */
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                view, this.getResources().getString(R.string.zhihu_detail_title));
        startActivity(intent, options.toBundle());
    }

}
