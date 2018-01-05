package jtli.com.simplereader.injector.module.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.ZhiHuAdapter;
import jtli.com.simplereader.bean.zhihu.HomeListBean;

@Module
public class ZhihuHomeModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new ZhiHuAdapter(new ArrayList<HomeListBean>());//new ArrayList()这样子也可以，不过这里我们为了给自己看就写了泛型。
    }
}
