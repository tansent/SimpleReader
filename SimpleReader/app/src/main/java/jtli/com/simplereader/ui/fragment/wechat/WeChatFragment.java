package jtli.com.simplereader.ui.fragment.wechat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.app.AppConstants;
import jtli.com.simplereader.bean.wechat.WXItemBean;
import jtli.com.simplereader.injector.component.fragment.DaggerWeChatComponent;
import jtli.com.simplereader.injector.module.fragment.WeChatModule;
import jtli.com.simplereader.injector.module.http.WeChatHttpModule;
import jtli.com.simplereader.presenter.WeChatPresenter;
import jtli.com.simplereader.presenter.impl.WeChatPresenterImpl;
import jtli.com.simplereader.rx.RxBus;
import jtli.com.simplereader.ui.fragment.BaseFragment;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jingtian(Tansent).
 */

public class WeChatFragment extends BaseFragment<WeChatPresenterImpl> implements WeChatPresenter.View {

    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;

    private static final int NUM_OF_PAGE = 20;
    private int currentPage = 1;

    private CompositeSubscription searshSubscription;

    @Override
    protected void initInject() {
        DaggerWeChatComponent.builder()
                .weChatHttpModule(new WeChatHttpModule())
                .weChatModule(new WeChatModule())
                .build().injectWeChat(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        rcvActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvActivity.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        // 第一次必然走 mPresenter.fetchWeChatHot(NUM_OF_PAGE, currentPage);
        // 只有mAdapter有了值，rxBus才能启动
        // 可更改方法是 在主页面，取消搜索框
        mPresenter.fetchWeChatHot(NUM_OF_PAGE, currentPage);
        if (this.searshSubscription == null) {
            registerEvent();
        }

    }

    @Override
    public void refreshView(List<WXItemBean> data) {
        mAdapter.setNewData(data);
    }

    public void registerEvent() {
        Subscription mSubscription = RxBus.getDefault().toObservable(AppConstants.WECHA_SEARCH, String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mPresenter.fetchWXHotSearch(20, 1, s);
                    }
                });
        if (this.searshSubscription == null) {
            searshSubscription = new CompositeSubscription();
        }
        searshSubscription.add(mSubscription);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (this.searshSubscription != null && searshSubscription.hasSubscriptions()) {
            this.searshSubscription.unsubscribe();
        }
    }

}
