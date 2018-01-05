package jtli.com.simplereader.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.zhihu.DailyListBean;
import jtli.com.simplereader.bean.zhihu.ThemeChildListBean;
import jtli.com.simplereader.utils.GlideUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhihuDailyListAdapter extends BaseQuickAdapter<DailyListBean.StoriesBean ,BaseViewHolder> {

    public ZhihuDailyListAdapter(List<DailyListBean.StoriesBean> data) {
        super(R.layout.item_theme,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DailyListBean.StoriesBean item) {

        if (item.getImages()!=null&&item.getImages().size()>0){
            GlideUtils.load(mContext,item.getImages().get(0), (ImageView) helper.getView(R.id.iv_theme_item_image));
        }
        TextView tvThemeItemTitle= helper.getView(R.id.tv_theme_item_title);
        tvThemeItemTitle.setText(item.getTitle());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onItemClick(item.getId(),helper.getView(R.id.iv_theme_item_image));
                }
            }
        });
    }

    private ZhihuDailyListAdapter.OnItemClick onItemClick;
    public interface OnItemClick{
        void onItemClick(int id,View view);
    }
    public void setOnZhihuThemeItemClick(ZhihuDailyListAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

}
