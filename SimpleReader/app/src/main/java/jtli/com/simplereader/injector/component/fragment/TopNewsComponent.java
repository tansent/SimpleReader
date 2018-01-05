package jtli.com.simplereader.injector.component.fragment;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.TopNewsModule;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.ui.fragment.home.child.TopNewsFragment;

@Singleton
@Component(modules = { TopNewsHttpModule.class,TopNewsModule.class})
public interface TopNewsComponent {
    void injectTopNews(TopNewsFragment topNewsFragment);
}
