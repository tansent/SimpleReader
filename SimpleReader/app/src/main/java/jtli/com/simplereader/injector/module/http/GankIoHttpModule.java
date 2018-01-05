package jtli.com.simplereader.injector.module.http;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.http.service.GankIoService;
import jtli.com.simplereader.injector.qualifier.GankUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class GankIoHttpModule extends BaseHttpModule {
    @Singleton
    @Provides
    @GankUrl
    Retrofit provideGankIoRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankIoService.API_GANKIO);
    }

    @Singleton
    @Provides
    GankIoService provideGankIoService(@GankUrl Retrofit retrofit) {
        return retrofit.create(GankIoService.class);
    }


}
