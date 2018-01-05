package jtli.com.simplereader.injector.component.fragment;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.ZhihuHomeModule;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.ui.fragment.home.child.zhihu.ZhiHuHomeFragment;

/**
 * Created by Jingtian(Tansent).
 */

@Singleton
@Component(modules = {ZhihuHttpModule.class, ZhihuHomeModule.class})
public interface ZhihuHomeComponent {
    void injectZhihuhome(ZhiHuHomeFragment zhiHuFragment);
}
