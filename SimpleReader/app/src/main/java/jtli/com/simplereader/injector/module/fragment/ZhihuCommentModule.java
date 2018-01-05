package jtli.com.simplereader.injector.module.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.simplereader.adapter.ZhiHuCommentAdapter;
import jtli.com.simplereader.bean.zhihu.CommentBean;

/**
 * Created by Jingtian(Tansent).
 */
@Module
public class ZhihuCommentModule {

    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new ZhiHuCommentAdapter(new ArrayList<CommentBean.CommentsBean>());
    }

}
