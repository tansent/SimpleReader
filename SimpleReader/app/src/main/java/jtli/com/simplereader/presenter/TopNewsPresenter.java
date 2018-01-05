package jtli.com.simplereader.presenter;


import jtli.com.simplereader.bean.topnews.NewsDetailBean;
import jtli.com.simplereader.bean.topnews.NewsListBean;

public interface TopNewsPresenter {

    interface View extends BaseView<NewsListBean>{
    }

    interface Presenter{
        void fetchTopNewsList(int id);
    }
    interface ViewActivity extends View {
        void refreshActivityView(NewsDetailBean newsDetailBean);
    }

}
