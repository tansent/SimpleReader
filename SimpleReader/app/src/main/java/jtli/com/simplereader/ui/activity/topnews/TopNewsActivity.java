package jtli.com.simplereader.ui.activity.topnews;

import android.text.TextUtils;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.app.AppConstants;
import jtli.com.simplereader.bean.topnews.NewsDetailBean;
import jtli.com.simplereader.bean.topnews.NewsListBean;
import jtli.com.simplereader.injector.component.activity.DaggerTopNewsComponent;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.presenter.TopNewsPresenter;
import jtli.com.simplereader.presenter.impl.TopNewsPresenterImpl;
import jtli.com.simplereader.ui.activity.base.ZhihuDetailBaseActivity;
import jtli.com.simplereader.utils.GlideUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class TopNewsActivity  extends ZhihuDetailBaseActivity<TopNewsPresenterImpl> implements TopNewsPresenter.ViewActivity  {

    @BindView(R.id.ht_news_content)
    HtmlTextView htNewsContent;
    private String url;

    @Override
    protected void initInject() {
        DaggerTopNewsComponent.builder()
                .topNewsHttpModule(new TopNewsHttpModule())
                .build().injectTopNews(this);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_top_news;
    }

    @Override
    protected void initView() {
        //一个注解就搞定 @BindView(R.id.ht_news_content)
    }

    @Override
    protected void loadData() {
        String id = getIntent().getStringExtra("id");//获取新闻的id
        //获取imgUrl
        url = getIntent().getStringExtra("url");
        mPresenter.getDescrible(id);
    }

    @Override
    public void refreshView(NewsListBean mData) {
        //this method is for TopNewsFragment to show the list
    }

    @Override
    public void refreshActivityView(NewsDetailBean newsDetailBean) {
        setState(AppConstants.STATE_SUCCESS);
        if (TextUtils.isEmpty(newsDetailBean.getBody())){
            return;
        }
        htNewsContent.setHtmlFromString(newsDetailBean.getBody(), new HtmlTextView.LocalImageGetter());
        setToolBar(toolbarZhihuDetail, newsDetailBean.getTitle());
        detailBarCopyright.setText(newsDetailBean.getSource());
        GlideUtils.load(this, url, detailBarImage);
    }
}
