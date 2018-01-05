package jtli.com.simplereader.injector.module.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.TopNewsAdapter;

@Module
public class TopNewsModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new TopNewsAdapter(new ArrayList());
    }
}
