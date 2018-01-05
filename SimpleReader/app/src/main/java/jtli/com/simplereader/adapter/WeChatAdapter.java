package jtli.com.simplereader.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.wechat.WXItemBean;
import jtli.com.simplereader.utils.GlideUtils;
import jtli.com.simplereader.webview.WebViewActivity;

/**
 * Created by Jingtian(Tansent).
 */

public class WeChatAdapter extends BaseQuickAdapter<WXItemBean, BaseViewHolder> {
    public WeChatAdapter(List<WXItemBean> data) {
        super(R.layout.item_wechat, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final WXItemBean item) {
        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_android_pic),item.getPicUrl());
        helper.setText(R.id.tv_android_des,item.getTitle());
        helper.setText(R.id.tv_android_who,item.getDescription());
        helper.setText(R.id.tv_android_time,item.getCtime());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.loadUrl(mContext,item.getUrl(),item.getTitle());
            }
        });
    }

}
