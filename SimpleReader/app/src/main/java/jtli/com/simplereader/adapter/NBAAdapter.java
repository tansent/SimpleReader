package jtli.com.simplereader.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.topnews.NBAListBean;
import jtli.com.simplereader.utils.GlideUtils;
import jtli.com.simplereader.utils.TimeUtil;

public class NBAAdapter extends BaseQuickAdapter<NBAListBean.NBABean, BaseViewHolder> {
    public NBAAdapter(List<NBAListBean.NBABean> data) {
        super(R.layout.item_wechat, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final NBAListBean.NBABean item) {
        ImageView ivAndroidPic = helper.getView(R.id.iv_android_pic);
        // 显示gif图片会很耗内存
        if (item.getImgsrc() != null
                && !TextUtils.isEmpty(item.getImgsrc())) {
            ivAndroidPic.setVisibility(View.VISIBLE);
            GlideUtils.loadMovieTopImg(ivAndroidPic, item.getImgsrc());
        } else {
            ivAndroidPic.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_android_des, item.getTitle());
        helper.setText(R.id.tv_android_who, item.getEname());
        helper.setText(R.id.tv_android_time, TimeUtil.getTime(item.getPtime()));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_android_pic));
            }
        });
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(String id, String imgUrl, View view);}
}
