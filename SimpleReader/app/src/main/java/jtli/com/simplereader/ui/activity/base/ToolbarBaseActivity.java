package jtli.com.simplereader.ui.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jtli.com.simplereader.R;

/**
 * Created by Jingtian(Tansent).
 */

public abstract class ToolbarBaseActivity extends BaseActivity {

    protected Toolbar toolbarBaseActivity;
    private Unbinder bind;
    private FrameLayout flToolbarBase;
    private View contentView;
    protected TextView tvToolbarTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toolbar_base;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarBaseActivity = (Toolbar) findViewById(R.id.toolbar_base_activity);
        flToolbarBase = (FrameLayout) findViewById(R.id.fl_toolbar_base);
        findViewById(R.id.iv_base_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        contentView = LayoutInflater.from(this).inflate(getContentLayoutId(), null);
        flToolbarBase.addView(contentView);
        bind = ButterKnife.bind(this,contentView);
        initUI();
    }

    /**
     * 用于初始化UI至少要初始化toolbar，如果还需要别的方法可以自己加
     */
    protected abstract void initUI();

    public abstract int getContentLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    /**
     * 颜色变化过度
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    public Object evaluateColor(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (startA + (int) (fraction * (endA - startA))) << 24 |
                (startR + (int) (fraction * (endR - startR))) << 16 |
                (startG + (int) (fraction * (endG - startG))) << 8 |
                (startB + (int) (fraction * (endB - startB)));
    }

}
