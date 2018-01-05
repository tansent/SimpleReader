package jtli.com.simplereader.injector.component.fragment;


import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.NBAModule;
import jtli.com.simplereader.injector.module.http.TopNewsHttpModule;
import jtli.com.simplereader.ui.fragment.wechat.NBAFragment;

@Singleton
@Component(modules = { TopNewsHttpModule.class,NBAModule.class})
public interface NBAComponent {
    void injectNBA(NBAFragment nbaFragment);
}
