package jtli.com.simplereader.ui.activity.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jtli.com.simplereader.R;
import jtli.com.simplereader.adapter.HomeFragmentPageAdapter;
import jtli.com.simplereader.ui.activity.base.BaseActivity;
import jtli.com.simplereader.ui.fragment.home.child.zhihu.ZhiHuCommentFragment;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhiHuCommentActivity extends BaseActivity {

    @BindView(R.id.toolbar_zhihu_comment)
    Toolbar toolbarZhihuComment;
    @BindView(R.id.tab_zhihu_comment)
    TabLayout tabZhihuComment;
    @BindView(R.id.vp_zhihu_comment)
    ViewPager vpZhihuComment;

    private int shortNum;
    private int longNum;
    private int id;
    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    private HomeFragmentPageAdapter myAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_comment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int allNum = intent.getExtras().getInt("allNum");
        shortNum = intent.getExtras().getInt("shortNum");
        longNum = intent.getExtras().getInt("longNum");
        id = intent.getExtras().getInt("id");
        setToolBar(toolbarZhihuComment,String.format("%d条评论",allNum));
        initFragmentList();
        myAdapter = new HomeFragmentPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        vpZhihuComment.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabZhihuComment.setTabMode(TabLayout.MODE_FIXED);
        tabZhihuComment.setupWithViewPager(vpZhihuComment);

    }

    private void initFragmentList() {
        if (mTitleList.size() != 0) {
            return;
        }
        mTitleList.add(String.format("短评论(%d)",shortNum));
        mFragments.add(ZhiHuCommentFragment.getInstance(true));
        mTitleList.add(String.format("长评论(%d)", longNum));
        mFragments.add(ZhiHuCommentFragment.getInstance(false));
    }


    public int getId() {
        return id;
    }

}
