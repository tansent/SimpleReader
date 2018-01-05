package jtli.com.simplereader.injector.component;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.app.App;
import jtli.com.simplereader.injector.module.AppModule;

/**
 * Created by Jingtian(Tansent).
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context
}
