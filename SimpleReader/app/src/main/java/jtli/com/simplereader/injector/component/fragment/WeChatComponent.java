package jtli.com.simplereader.injector.component.fragment;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.WeChatModule;
import jtli.com.simplereader.injector.module.http.WeChatHttpModule;
import jtli.com.simplereader.ui.fragment.wechat.WeChatFragment;

@Singleton
@Component(modules = { WeChatHttpModule.class,WeChatModule.class})
public interface WeChatComponent {
    void injectWeChat(WeChatFragment weChatFragment);
}
