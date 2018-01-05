package jtli.com.simplereader.injector.component.fragment;


import javax.inject.Singleton;

import dagger.Component;
import jtli.com.simplereader.injector.module.fragment.AndroidModule;
import jtli.com.simplereader.injector.module.http.GankIoHttpModule;
import jtli.com.simplereader.ui.fragment.gank.AndroidFragment;

@Singleton
@Component(modules = { GankIoHttpModule.class,AndroidModule.class})
public interface AndroidComponent {
    void injectAndroid(AndroidFragment androidFragment);
}
