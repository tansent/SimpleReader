package jtli.com.simplereader.injector.module.activity;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.ZhihuThemeAdapter;

/**
 * Created by Jingtian(Tansent).
 */

@Module
public class ZhihuThemeModule {
    @Provides
    @Singleton
    public ZhihuThemeAdapter provideAdapter() {
        return new ZhihuThemeAdapter(new ArrayList());
    }
}
