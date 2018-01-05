package jtli.com.simplereader.injector.module.activity;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.ZhihuDailyListAdapter;

/**
 * Created by Jingtian(Tansent).
 */
@Module
public class ZhihuDailyListModule {
    @Provides
    @Singleton
    public ZhihuDailyListAdapter provideAdapter(){
        return new ZhihuDailyListAdapter(new ArrayList());
    }
}
