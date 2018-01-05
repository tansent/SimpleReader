package jtli.com.simplereader.injector.module.http;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.http.service.ZhiHuService;
import jtli.com.simplereader.injector.qualifier.ZhihuUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class ZhihuHttpModule extends BaseHttpModule {
    @Provides
    @Singleton
    @ZhihuUrl
    Retrofit provideZhiHuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhiHuService.HOST);
    }
    @Provides
    @Singleton
    ZhiHuService provideZhihuService(@ZhihuUrl Retrofit retrofit) {
        return retrofit.create(ZhiHuService.class);
    }

}
