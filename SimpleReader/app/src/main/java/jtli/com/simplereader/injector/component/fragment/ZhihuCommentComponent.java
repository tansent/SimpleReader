package jtli.com.simplereader.injector.component.fragment;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.ZhihuCommentModule;
import jtli.com.simplereader.injector.module.http.ZhihuHttpModule;
import jtli.com.simplereader.ui.fragment.home.child.zhihu.ZhiHuCommentFragment;

/**
 * Created by Jingtian(Tansent).
 */
@Singleton
@Component(modules = { ZhihuHttpModule.class,ZhihuCommentModule.class})
public interface ZhihuCommentComponent {
    void injectZhihuComment(ZhiHuCommentFragment zhiHuCommentFragment);
}
