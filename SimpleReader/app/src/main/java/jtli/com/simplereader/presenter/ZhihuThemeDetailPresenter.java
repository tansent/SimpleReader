package jtli.com.simplereader.presenter;


import jtli.com.simplereader.bean.zhihu.SectionChildListBean;
import jtli.com.simplereader.bean.zhihu.ThemeChildListBean;


public interface ZhihuThemeDetailPresenter {

    interface View extends BaseView<ThemeChildListBean> {
        void refreshSectionData(SectionChildListBean data);
    }

    interface Presenter{
        void fetchThemeChildList(int id);
        void fetchSectionChildList(int id);
    }
}
