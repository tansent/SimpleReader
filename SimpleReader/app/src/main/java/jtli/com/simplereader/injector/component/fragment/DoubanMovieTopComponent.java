package jtli.com.simplereader.injector.component.fragment;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.DoubanMovieTopModule;
import jtli.com.simplereader.injector.module.http.DoubanHttpModule;
import jtli.com.simplereader.ui.fragment.home.child.DouBanMovieTopFragment;

@Singleton
@Component(modules = { DoubanHttpModule.class,DoubanMovieTopModule.class})
public interface DoubanMovieTopComponent {
    void injectDoubanMovieTop(DouBanMovieTopFragment douBanMovieTopFragment);
}
