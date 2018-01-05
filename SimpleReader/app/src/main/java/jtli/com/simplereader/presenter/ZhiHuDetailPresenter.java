package jtli.com.simplereader.presenter;

import jtli.com.simplereader.bean.zhihu.DetailExtraBean;
import jtli.com.simplereader.bean.zhihu.ZhihuDetailBean;

/**
 * Created by Jingtian(Tansent).
 */

public interface ZhiHuDetailPresenter  {

    interface View extends BaseView<ZhihuDetailBean> {
        void showExtraInfo(DetailExtraBean detailExtraBean);
    }

    interface Presenter{
        void fetchDetailInfo(int id);
        void fetchDetailExtraInfo(int id);
    }
}
