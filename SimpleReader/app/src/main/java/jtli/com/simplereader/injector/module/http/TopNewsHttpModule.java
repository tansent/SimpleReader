package jtli.com.simplereader.injector.module.http;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.http.service.TopNewsService;
import jtli.com.simplereader.injector.qualifier.TopNewsUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class TopNewsHttpModule extends BaseHttpModule {
    @Singleton
    @Provides
    @TopNewsUrl
    Retrofit provideTopNewsRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, TopNewsService.API_TOPNEWS);
    }

    @Singleton
    @Provides
    TopNewsService provideTopNewsService(@TopNewsUrl Retrofit retrofit) {
        return retrofit.create(TopNewsService.class);
    }

}
