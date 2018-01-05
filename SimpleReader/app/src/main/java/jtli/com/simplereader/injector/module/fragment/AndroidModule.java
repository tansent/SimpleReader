package jtli.com.simplereader.injector.module.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.GankIoAndroidAdapter;

@Module
public class AndroidModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new GankIoAndroidAdapter(new ArrayList());
    }
}
