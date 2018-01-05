package jtli.com.simplereader.injector.module.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.MovieTopAdapter;

@Module
public class DoubanMovieTopModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new MovieTopAdapter(new ArrayList());
    }
}
