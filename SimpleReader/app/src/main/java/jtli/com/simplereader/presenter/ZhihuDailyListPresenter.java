package jtli.com.simplereader.presenter;

import java.util.List;

import jtli.com.simplereader.bean.zhihu.DailyListBean;
import jtli.com.simplereader.bean.zhihu.SectionChildListBean;
import jtli.com.simplereader.bean.zhihu.ThemeChildListBean;

/**
 * Created by Jingtian(Tansent).
 */

public interface ZhihuDailyListPresenter {

    interface View extends BaseView<List<DailyListBean.StoriesBean>> {
    }

    interface Presenter{
        void fetchZhihuDailyList();
    }

}
