package jtli.com.simplereader.ui.activity.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ToastUtils;

import java.util.List;

import butterknife.OnClick;
import jtli.com.simplereader.R;
import jtli.com.simplereader.ui.activity.base.ToolbarBaseActivity;
import jtli.com.simplereader.utils.LoginUtils;
import jtli.com.simplereader.webview.WebViewActivity;

/**
 * Created by Jingtian(Tansent).
 */

public class FeedbackActivity extends ToolbarBaseActivity {
    @Override
    protected void initUI() {
        tvToolbarTitle.setText("意见反馈");
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_feedback;
    }

    @OnClick({R.id.tv_qq, R.id.tv_issues})
    public void isLogin(final View view) {
        LoginUtils.setIlogin(new LoginUtils.ILogin() {
            @Override
            public void onlogin() {
                onViewClick(view.getId());
            }
        },this);
    }

    private void onViewClick(int id) {
        switch (id) {
            case R.id.tv_qq:
                qq();
                break;
            case R.id.tv_issues:
                issues();
                break;
        }
    }

    private String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1497578806&version=1";
    public void qq() {
        if (hasQQClientAvailable(this)) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
        } else {
            ToastUtils.showShortToast("您没安装QQ，请先安装QQ客户端");
        }
    }

    public void issues() {
        WebViewActivity.loadUrl(this, "https://github.com/tansent/SimpleReader/issues", "加载中。。。");
    }


    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean hasQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                LogUtils.e("pn = " + pn);
                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


}
