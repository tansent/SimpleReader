package jtli.com.simplereader.ui.activity.zhihu;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.utils.ConvertUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.ZhihuSectionAdapter;
import jtli.com.simplereader.adapter.ZhihuThemeAdapter;
import jtli.com.simplereader.bean.zhihu.SectionChildListBean;
import jtli.com.simplereader.bean.zhihu.ThemeChildListBean;
import jtli.com.simplereader.injector.component.activity.DaggerZhihuThemeComponent;
import jtli.com.simplereader.injector.module.activity.ZhihuThemeModule;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.presenter.ZhihuThemeDetailPresenter;
import jtli.com.simplereader.presenter.impl.ZhihuThemeDetailPresenterImpl;
import jtli.com.simplereader.ui.activity.base.ZhihuThemeBaseActivity;
import jtli.com.simplereader.utils.GlideUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhihuThemeActivity extends ZhihuThemeBaseActivity<ZhihuThemeDetailPresenterImpl> implements ZhihuThemeDetailPresenter.View {
    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;
    @Inject
    ZhihuThemeAdapter zhihuThemeAdapter;

    private int sectionId;
    private ZhihuSectionAdapter zhihuSectionAdapter;
    private List storiesList;
    private int id;

    //super class
    @Override
    protected void initView() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        if (sectionId>0) {
            zhihuSectionAdapter = new ZhihuSectionAdapter(storiesList);
            zhihuSectionAdapter.setOnZhihuThemeItemClick(new ZhihuSectionAdapter.OnItemClick() {
                @Override
                public void onItemClick(int id, View view) {
                    startZhiHuDetailActivity(id, view);
                }
            });
            rcvActivity.setAdapter(zhihuSectionAdapter);
        }
        else {
            zhihuThemeAdapter = new ZhihuThemeAdapter(storiesList);
            zhihuThemeAdapter.setOnZhihuThemeItemClick(new ZhihuThemeAdapter.OnItemClick() {
                @Override
                public void onItemClick(int id, View view) {
                    startZhiHuDetailActivity(id, view);
                }
            });
            rcvActivity.setAdapter(zhihuThemeAdapter);
        }
        rcvActivity.setLayoutManager(new LinearLayoutManager(this));

        appbarThemeChild.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipeRefresh.setEnabled(true);
                } else {
                    swipeRefresh.setEnabled(false);
                    float rate = (float) (ConvertUtils.dp2px(256) + verticalOffset * 2) / ConvertUtils.dp2px(256);
                    if (rate >= 0)
                        ivThemeChildOrigin.setAlpha(rate);
                }
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (sectionId>0){
                    mPresenter.fetchSectionChildList(sectionId);
                }else {
                    mPresenter.fetchThemeChildList(id);
                }
            }
        });

    }


    @Override
    protected void initInject() {
        DaggerZhihuThemeComponent.builder()
                .zhihuHttpModule(new ZhihuHttpModule())
                .zhihuThemeModule(new ZhihuThemeModule())
                .build().injectZhiHuTheme(this);
    }



    @Override
    protected void loadData() {
        id = getIntent().getIntExtra("id", 0);
        sectionId = getIntent().getIntExtra("section_id",0);
        if (sectionId>0){
            mPresenter.fetchSectionChildList(sectionId);
        }else {
            mPresenter.fetchThemeChildList(id);
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_recyclerview;
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

    //interface
    @Override
    public void refreshView(ThemeChildListBean data) {
        storiesList = data.getStories();
        zhihuThemeAdapter.setNewData(storiesList);
        zhihuThemeAdapter.notifyDataSetChanged();
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        setToolBar(toolbarThemeBase, data.getName());
        GlideUtils.load(this, data.getBackground(), ivThemeChildOrigin);
        Glide.with(this).load(data.getBackground()).bitmapTransform(new BlurTransformation(this)).into(ivThemeChildBlur);
        tvThemeChildDes.setText(data.getDescription());
    }

    @Override
    public void refreshSectionData(SectionChildListBean data) {
        storiesList = data.getStories();
        zhihuSectionAdapter.setNewData(storiesList);
        zhihuSectionAdapter.notifyDataSetChanged();
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        setToolBar(toolbarThemeBase, data.getName());
        tvThemeChildDes.setText(data.getName());
    }


}
