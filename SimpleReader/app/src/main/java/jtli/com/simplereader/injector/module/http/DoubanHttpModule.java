package jtli.com.simplereader.injector.module.http;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.http.service.DoubanService;
import jtli.com.simplereader.injector.qualifier.DoubanUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class DoubanHttpModule extends BaseHttpModule {

    @Singleton
    @Provides
    @DoubanUrl
    Retrofit provideDoubanRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, DoubanService.API_DOUBAN);
    }

    @Singleton
    @Provides
    DoubanService provideDoubanService(@DoubanUrl Retrofit retrofit) {
        return retrofit.create(DoubanService.class);
    }

}
