package jtli.com.simplereader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jingtian(Tansent).
 */

public class HomeFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> mTitleList;

    public HomeFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public HomeFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitleList){
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null) {
            return mTitleList.get(position);
        } else {
            return "";
        }
    }

}
