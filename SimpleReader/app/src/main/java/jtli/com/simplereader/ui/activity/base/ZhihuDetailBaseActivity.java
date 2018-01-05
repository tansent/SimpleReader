package jtli.com.simplereader.ui.activity.base;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import jtli.com.simplereader.R;
import jtli.com.simplereader.presenter.BasePresenter;

/**
 * 只要是头部和这个一样的都可以用这个Activity,底下可以自己定义。
 */

public abstract class ZhihuDetailBaseActivity <T extends BasePresenter> extends LoadingBaseActivity<T> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_base;
    }

    @Override
    public int setFrameLayoutId() {
        return R.id.fl_base_content;
    }

    protected ImageView detailBarImage;
    protected Toolbar toolbarZhihuDetail;
    protected TextView detailBarCopyright;
    @Override
    protected void initUI() {
        detailBarImage = (ImageView) findViewById(R.id.detail_bar_image);
        toolbarZhihuDetail = (Toolbar) findViewById(R.id.toolbar_zhihu_detail);
        detailBarCopyright = (TextView) findViewById(R.id.detail_bar_copyright);
        setToolBar(toolbarZhihuDetail, "");
    }


}
