package jtli.com.simplereader.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.zhihu.SectionChildListBean;
import jtli.com.simplereader.utils.GlideUtils;

/**
 * Created by Jingtian(Tansent).
 */

public class ZhihuSectionAdapter  extends BaseQuickAdapter<SectionChildListBean.StoriesBean,BaseViewHolder> {
    public ZhihuSectionAdapter(List<SectionChildListBean.StoriesBean> data) {
        super(R.layout.item_theme,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SectionChildListBean.StoriesBean item) {

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

    private  OnItemClick onItemClick;
    public interface OnItemClick{
        void onItemClick(int id, View view);
    }
    public void setOnZhihuThemeItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }
}
