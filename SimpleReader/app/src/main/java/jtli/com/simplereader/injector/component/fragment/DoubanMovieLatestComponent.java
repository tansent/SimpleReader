package jtli.com.simplereader.injector.component.fragment;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.DoubanMovieLatestModule;
import jtli.com.simplereader.injector.module.http.DoubanHttpModule;
import jtli.com.simplereader.ui.fragment.home.child.DouBanMovieLatestFragment;

@Singleton
@Component(modules = { DoubanHttpModule.class,DoubanMovieLatestModule.class})
public interface DoubanMovieLatestComponent {
    void injectDoubanMovieLatest(DouBanMovieLatestFragment douBanMovieLatestFragment);
}
