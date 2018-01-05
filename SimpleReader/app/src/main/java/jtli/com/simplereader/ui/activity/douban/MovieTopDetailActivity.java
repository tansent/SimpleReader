package jtli.com.simplereader.ui.activity.douban;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.douban.HotMovieBean;
import jtli.com.simplereader.bean.douban.MovieDetailBean;
import jtli.com.simplereader.bean.douban.PersonBean;
import jtli.com.simplereader.injector.component.activity.DaggerMovieDetailComponent;
import jtli.com.simplereader.injector.module.http.DoubanHttpModule;
import jtli.com.simplereader.presenter.DoubanMovieDetailPresenter;
import jtli.com.simplereader.presenter.impl.DoubanMovieDetailPresenterImpl;
import jtli.com.simplereader.ui.activity.base.LoadingBaseActivity;
import jtli.com.simplereader.utils.GlideUtils;
import jtli.com.simplereader.utils.StringFormatUtil;
import jtli.com.simplereader.view.HorizontalScrollView;
import jtli.com.simplereader.webview.WebViewActivity;

/**
 * Created by Jingtian(Tansent).
 */

public class MovieTopDetailActivity  extends LoadingBaseActivity<DoubanMovieDetailPresenterImpl> implements DoubanMovieDetailPresenter.View  {

    private ImageView ivBaseTitlebarBg;
    private Toolbar toolbarDoubanDetail;
    private ImageView ivOnePhoto;
    private TextView tvOneRatingRate;
    private TextView tvOneRatingNumber;
    private TextView tvOneGenres;
    private TextView tvOneDay;
    private TextView tvOneCity;
    private HotMovieBean.SubjectsBean subjectsBean;
    private String id;
    private TextView tvFormerly;

    private String mMoreUrl;
    private String mMovieName;

    @BindView(R.id.nsv_movie_top_detail)
    NestedScrollView nsvMovieTopDetail;

    @BindView(R.id.hs_film)
    HorizontalScrollView hsFilm;

    @BindView(R.id.tv_movie_top_detail)
    TextView tvMovieTopDetail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_top_detail;
    }

    @Override
    public int setFrameLayoutId() {
        return R.id.fl_douban_detail_content;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_movie_top;
    }

    /**
     * 对应整体框的UI，R.layout.activity_movie_top_detail
     */
    @Override
    protected void initUI() {
        final AppBarLayout appbarMovieTopChild = (AppBarLayout) findViewById(R.id.appbar_movie_top_child);
        ivBaseTitlebarBg = (ImageView) findViewById(R.id.img_item_bg);
        ivOnePhoto = (ImageView) findViewById(R.id.iv_one_photo);
        tvOneRatingRate = (TextView) findViewById(R.id.tv_one_rating_rate);
        tvOneRatingNumber = (TextView) findViewById(R.id.tv_one_rating_number);
        tvOneGenres = (TextView) findViewById(R.id.tv_one_genres);
        tvOneDay = (TextView) findViewById(R.id.tv_one_day);
        tvOneCity = (TextView) findViewById(R.id.tv_one_city);
        tvFormerly = (TextView) findViewById(R.id.tv_formerly);
        toolbarDoubanDetail = (Toolbar) findViewById(R.id.toolbar_douban_detail);
        initToolBar(toolbarDoubanDetail, "");
        appbarMovieTopChild.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (appbarMovieTopChild.getBottom() > toolbarDoubanDetail.getBottom() ) {
                    toolbarDoubanDetail.setBackgroundColor(getResources().getColor(R.color.translucent));
                } else {
                    toolbarDoubanDetail.setBackgroundResource(R.color.colorTheme);
                }
            }
        });
        subjectsBean = (HotMovieBean.SubjectsBean) getIntent().getSerializableExtra("bean");
        if (subjectsBean != null) {
            toolbarDoubanDetail.setTitle(subjectsBean.getTitle());
            toolbarDoubanDetail.setSubtitleTextColor(Color.WHITE);
            setImgHeaderBg(subjectsBean.getImages().getMedium());
            GlideUtils.loadMovieTopImg(ivOnePhoto, subjectsBean.getImages().getLarge());
            tvOneRatingRate.setText("评分：" + subjectsBean.getRating().getAverage());
            tvOneGenres.setText("类型：" + StringFormatUtil.formatGenres(subjectsBean.getGenres()));
            tvOneDay.setText("上映日期：" + subjectsBean.getYear());
            //电影详情的id
            id = subjectsBean.getId();
        }
    }

    @Override
    protected void initInject() {
        DaggerMovieDetailComponent.builder()
                .doubanHttpModule(new DoubanHttpModule())
                .build().injectMovieDetail(this);
    }

    /**
     * 对应framelayout的UI  R.id.fl_douban_detail_content -> R.layout.activity_movie_top;
     */
    @Override
    protected void initView() {
        //use ButterKnife to do all the work
    }

    @Override
    protected void loadData() {
        if (!TextUtils.isEmpty(id))
            mPresenter.fetchMovieDetail(id);
    }


    /**
     * set data for either or both "R.layout.activity_movie_top" and "R.layout.activity_movie_top_detail"
     * @param data
     */
    @Override
    public void refreshView(MovieDetailBean data) {
        mMoreUrl = data.getAlt();
        mMovieName = data.getTitle();
        tvFormerly.setText("原名：" + data.getOriginal_title());
        tvOneRatingNumber.setText(data.getRatings_count() + "人评分");
        tvOneCity.setText("制作国家/地区：" + data.getCountries() + "");

        List<PersonBean> castsList = data.getCasts();
        for (final PersonBean personBean : castsList) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ConvertUtils.dp2px(120), ConvertUtils.dp2px(200)));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.loadMovieTopImg(imageView,personBean.getAvatars().getLarge());
            hsFilm.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebViewActivity.loadUrl(MovieTopDetailActivity.this,personBean.getAlt(),"加载中。。。");
                }
            });
        }
        tvMovieTopDetail.setText(data.getSummary());

    }


    private void initToolBar(Toolbar toolbarDoubanDetail, String title) {
        setSupportActionBar(toolbarDoubanDetail);
        toolbarDoubanDetail.setTitle(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }
        toolbarDoubanDetail.setTitleTextColor(Color.WHITE);
        toolbarDoubanDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbarDoubanDetail.setTitleTextAppearance(this, R.style.ToolBar_Title);
        toolbarDoubanDetail.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);
        toolbarDoubanDetail.inflateMenu(R.menu.base_header_menu);
        toolbarDoubanDetail.setOverflowIcon(getResources().getDrawable(R.mipmap.actionbar_more));
        toolbarDoubanDetail.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionbar_more:// 更多信息
                        setTitleClickMore();
                        break;
                }
                return true;
            }
        });
    }

    private void setTitleClickMore() {
        WebViewActivity.loadUrl(MovieTopDetailActivity.this, mMoreUrl, mMovieName);
    }

    /**
     * 加载titlebar背景
     */
    private void setImgHeaderBg(String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            // 高斯模糊背景  参数：12,5
            Glide.with(this).load(imgUrl)
                    .error(R.mipmap.stackblur_default)
                    .bitmapTransform(new BlurTransformation(this, 12, 5)).into(ivBaseTitlebarBg);
        }
    }

}
