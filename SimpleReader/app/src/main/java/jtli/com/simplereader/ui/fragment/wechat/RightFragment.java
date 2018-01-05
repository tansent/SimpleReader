package jtli.com.simplereader.ui.fragment.wechat;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.HomeFragmentPageAdapter;
import jtli.com.simplereader.app.AppConstants;
import jtli.com.simplereader.ui.fragment.BaseFragment;

/**
 * Created by Jingtian(Tansent).
 * 这个fragment是为了搭个架子，不需要数据，不用用到泛型
 */

public class RightFragment extends BaseFragment {

    @BindView(R.id.tab_gank)
    TabLayout tabGank;
    @BindView(R.id.vp_gank)
    ViewPager vpGank;

    private HomeFragmentPageAdapter myAdapter;
    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);

    @Override
    protected void initInject() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView() {
        initFragmentList();
        myAdapter = new HomeFragmentPageAdapter(getChildFragmentManager(), mFragments, mTitleList);
        vpGank.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabGank.setTabMode(TabLayout.MODE_FIXED);
        tabGank.setupWithViewPager(vpGank);
    }

    @Override
    protected void loadData() {
        setState(AppConstants.STATE_SUCCESS);
    }

    private void initFragmentList() {
        if (mTitleList.size() != 0) {
            return;
        }
        mTitleList.add("WeChat");
        mTitleList.add("NBA");
        mFragments.add(new WeChatFragment());
        mFragments.add(new NBAFragment());
    }
}
