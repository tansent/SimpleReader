package jtli.com.simplereader.http.service;

import java.util.List;

import jtli.com.simplereader.bean.wechat.WXHttpResponse;
import jtli.com.simplereader.bean.wechat.WXItemBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeChatService {

    String API_WeChat = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> fetchWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> fetchWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

    /**
     *     https://api.tianapi.com/meinv/?key=APIKEY&num=10
     *     获取广告图片
     */



}
