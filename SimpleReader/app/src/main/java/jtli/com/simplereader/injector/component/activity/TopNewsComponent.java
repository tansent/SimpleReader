package jtli.com.simplereader.injector.component.activity;


import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.ui.activity.topnews.NBAActivity;
import jtli.com.simplereader.ui.activity.topnews.TopNewsActivity;

/**
 * Created by quantan.liu on 2017/4/8.
 */
@Singleton
@Component(modules = { TopNewsHttpModule.class})
public interface TopNewsComponent {
    void injectTopNews(TopNewsActivity topNewsActivity);
    void injectNBA(NBAActivity nbaActivity);
}
