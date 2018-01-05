package jtli.com.simplereader.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.topnews.NewsListBean;
import jtli.com.simplereader.utils.DensityUtil;
import jtli.com.simplereader.utils.GlideUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class TopNewsAdapter extends BaseQuickAdapter<NewsListBean.NewsBean,BaseViewHolder> {

    public TopNewsAdapter(List<NewsListBean.NewsBean> data) {
        super(R.layout.item_top_news,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final NewsListBean.NewsBean item) {
        if (helper.getLayoutPosition() % 2 == 0) { //.getPosition()
            DensityUtil.setViewMargin(helper.itemView, false, 0, 0, 0, 40);
        } else {
            DensityUtil.setViewMargin(helper.itemView, false, 5, 0, 0, 40);
        }
        helper.setText(R.id.tv_item_top_news,item.getTitle());
        GlideUtils.loadImage(3,item.getImgsrc(), (ImageView) helper.getView(R.id.iv_item_top_news));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
            }
        });

    }

    public interface OnItemClickListener {
        void onItemClickListener(String id, String imgUrl,View view);}

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



}
