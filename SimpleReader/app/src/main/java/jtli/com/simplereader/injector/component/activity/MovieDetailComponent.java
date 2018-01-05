package jtli.com.simplereader.injector.component.activity;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.http.DoubanHttpModule;
import jtli.com.simplereader.ui.activity.douban.MovieTopDetailActivity;

@Singleton
@Component(modules = { DoubanHttpModule.class})
public interface MovieDetailComponent {
    void injectMovieDetail(MovieTopDetailActivity movieTopDetailActivity);
}
