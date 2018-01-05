package jtli.com.simplereader.ui.activity.topnews;

import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.app.AppConstants;
import jtli.com.simplereader.bean.topnews.NewsDetailBean;
import jtli.com.simplereader.injector.component.activity.DaggerTopNewsComponent;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.presenter.NBADetailPresenter;
import jtli.com.simplereader.presenter.impl.NBADetailPresenterImpl;
import jtli.com.simplereader.ui.activity.base.LoadingBaseActivity;
import jtli.com.simplereader.utils.ColorUtils;
import jtli.com.simplereader.utils.ShareUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class NBAActivity extends LoadingBaseActivity<NBADetailPresenterImpl> implements NBADetailPresenter.View{

    @BindView(R.id.ht_news_content)
    HtmlTextView htNewsContent;

    protected ImageView detailBarImage;
    protected Toolbar toolbarZhihuDetail;
    protected TextView detailBarCopyright;
    private CollapsingToolbarLayout clptoolbar;
    private FloatingActionButton fabNBA;

    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nba_base;
    }


    @Override
    protected void initUI() {
        detailBarImage = (ImageView) findViewById(R.id.detail_bar_image);
        toolbarZhihuDetail = (Toolbar) findViewById(R.id.toolbar_nba_detail);
        detailBarCopyright = (TextView) findViewById(R.id.detail_bar_copyright);
        clptoolbar = (CollapsingToolbarLayout) findViewById(R.id.clp_toolbar);
        fabNBA = (FloatingActionButton) findViewById(R.id.fab_nba);

        setToolBar(toolbarZhihuDetail, "");
    }

    @Override
    protected void initInject() {
        DaggerTopNewsComponent.builder()
                .topNewsHttpModule(new TopNewsHttpModule())
                .build().injectNBA(this);
    }

    @Override
    public int setFrameLayoutId() {
        return R.id.fl_base_content;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_top_news;
    }


    @Override
    protected void initView() {
//        @BindView(R.id.ht_news_content)
//        HtmlTextView htNewsContent;
    }

    @Override
    protected void loadData() {
        String id = getIntent().getStringExtra("id");//获取新闻的id
        //获取imgUrl
        url = getIntent().getStringExtra("url");
        mPresenter.fetchNBADetail(id);
    }


    @Override
    public void refreshView(final NewsDetailBean newsDetailBean) {
        setState(AppConstants.STATE_SUCCESS);
        if (!TextUtils.isEmpty(newsDetailBean.getBody())) {
            htNewsContent.setHtmlFromString(newsDetailBean.getBody(), new HtmlTextView.LocalImageGetter());
            setToolBar(toolbarZhihuDetail, newsDetailBean.getTitle());
            detailBarCopyright.setText(newsDetailBean.getSource());

            Glide.with(this).load(url).asBitmap()
                    .priority(Priority.IMMEDIATE).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                    detailBarImage.setImageBitmap(resource);
                    new Palette.Builder(resource).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            Palette.Swatch swatch = ColorUtils.getMostPopulousSwatch(palette);
                            if (swatch != null) {
                                int color = swatch.getRgb();
                                clptoolbar.setContentScrimColor(color);
                                clptoolbar.setStatusBarScrimColor(ColorUtils.getStatusBarColor(color));
                            }
                        }
                    });
                }
            });
        }

        fabNBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareText(NBAActivity.this,newsDetailBean.getSource(),newsDetailBean.getTitle());
            }
        });

    }
}
