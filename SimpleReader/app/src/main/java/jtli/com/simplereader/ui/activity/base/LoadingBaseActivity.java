package jtli.com.simplereader.ui.activity.base;

import android.os.Bundle;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jtli.com.simplereader.http.Stateful;
import jtli.com.simplereader.presenter.BasePresenter;
import jtli.com.simplereader.view.LoadingPage;

/**
 * Created by Jingtian(Tansent).
 */

public abstract class LoadingBaseActivity<T extends BasePresenter> extends BaseActivity implements Stateful {

    protected LoadingPage mLoadingPage;
    @Inject
    protected T mPresenter;
    private Unbinder bind;
    protected FrameLayout flBaseContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initInject();
        mPresenter.attachView(this);
        flBaseContent = (FrameLayout) findViewById(setFrameLayoutId());
        if (mLoadingPage == null) {
            mLoadingPage = new LoadingPage(this) {
                @Override
                protected void initView() {
                    bind = ButterKnife.bind(LoadingBaseActivity.this, contentView);
                    LoadingBaseActivity.this.initView();
                }

                @Override
                protected void loadData() {
                    LoadingBaseActivity.this.loadData();
                }

                @Override
                protected int getLayoutId() {
                    return LoadingBaseActivity.this.getContentLayoutId();
                }
            };
        }
        flBaseContent.addView(mLoadingPage);
        loadData();
    }

    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
    /**
     * 用于子类初始化findviewbyid的。
     * 这里初始化的Id是为了子类能公用的。
     */
    protected abstract void initUI();

    /**
     * dagger2注入
     */
    protected abstract void initInject();

    /**
     * 获取子类FrameLayout的Id是孙子类的容器
     *
     * @return
     */
    public abstract int setFrameLayoutId();

    /**
     * 3
     * 子类关于View的操作(如setAdapter)都必须在这里面，会因为页面状态不为成功，而binding还没创建就引用而导致空指针。
     * loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
     */
    protected abstract void initView();


    /**
     * 1
     * 根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
     * * 如果是静态页面不需要网络请求的在子类的loadData方法中添加以下2行即可
     * mLoadingPage.state = STATE_SUCCESS;
     * mLoadingPage.showPage();
     * 或者调用setState(AppConstants.STATE_SUCCESS)
     */
    protected abstract void loadData();

    /**
     * 2
     * 网络请求成功在去加载布局
     *
     * @return
     */
    public abstract int getContentLayoutId();



}
