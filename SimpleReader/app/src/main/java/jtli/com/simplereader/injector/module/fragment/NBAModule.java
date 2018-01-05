package jtli.com.simplereader.injector.module.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.NBAAdapter;

@Module
public class NBAModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new NBAAdapter(new ArrayList());
    }
}
