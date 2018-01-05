package jtli.com.simplereader.injector.module.http;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.http.service.WeChatService;
import jtli.com.simplereader.injector.qualifier.WeChatUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class WeChatHttpModule extends BaseHttpModule {
    @Singleton
    @Provides
    @WeChatUrl
    Retrofit provideWeChatRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, WeChatService.API_WeChat);
    }

    @Singleton
    @Provides
    WeChatService provideWeChatService(@WeChatUrl Retrofit retrofit) {
        return retrofit.create(WeChatService.class);
    }

}
