package jtli.com.simplereader.ui.activity.main;

import android.app.Activity;

import butterknife.OnClick;
import jtli.com.simplereader.R;
import jtli.com.simplereader.ui.activity.base.ToolbarBaseActivity;
import jtli.com.simplereader.webview.WebViewActivity;

/**
 * Created by Jingtian(Tansent).
 */

public class AboutUsActivity extends ToolbarBaseActivity {

    @OnClick(R.id.cv_github)
    public void cvGithub() {
        WebViewActivity.loadUrl(this, "https://github.com/laotan7237/EasyReader", "加载中。。。");
    }

    @Override
    protected void initUI() {
        tvToolbarTitle.setText("关于易读");
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_about_us;
    }

}
