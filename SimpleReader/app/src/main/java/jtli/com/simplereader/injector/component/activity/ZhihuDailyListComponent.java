package jtli.com.simplereader.injector.component.activity;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.activity.ZhihuDailyListModule;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.ui.activity.zhihu.ZhihuDailyListActivity;

/**
 * Created by Jingtian(Tansent).
 */

@Singleton
@Component(modules = { ZhihuHttpModule.class,ZhihuDailyListModule.class})
public interface ZhihuDailyListComponent {
    void injectZhihuDailyList(ZhihuDailyListActivity zhiHuDailyListActivity);

}
