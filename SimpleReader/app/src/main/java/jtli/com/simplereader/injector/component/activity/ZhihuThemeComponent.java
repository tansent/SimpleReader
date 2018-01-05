package jtli.com.simplereader.injector.component.activity;


import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.activity.ZhihuThemeModule;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.ui.activity.zhihu.ZhihuThemeActivity;

/**
 * Created by quantan.liu on 2017/4/8.
 */
@Singleton
@Component(modules = { ZhihuHttpModule.class,ZhihuThemeModule.class})
public interface ZhihuThemeComponent {
    void injectZhiHuTheme(ZhihuThemeActivity zhihuThemeActivity);
}
