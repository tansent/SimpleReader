package jtli.com.simplereader.injector.component.activity;


import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.ui.activity.zhihu.ZhiHuDetailActivity;

@Singleton
@Component(modules = { ZhihuHttpModule.class})
public interface ZhihuDetailComponent {
    void injectZhiHuDetail(ZhiHuDetailActivity zhiHuDetailActivity);
}
