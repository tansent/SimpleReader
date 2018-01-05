package jtli.com.simplereader.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.simplereader.R;
import jtli.com.simplereader.bean.douban.HotMovieBean;
import jtli.com.simplereader.utils.GlideUtils;
import jtli.com.simplereader.utils.StringFormatUtil;


public class MovieLatestAdapter extends BaseQuickAdapter<HotMovieBean.SubjectsBean,BaseViewHolder> {
    public MovieLatestAdapter(List<HotMovieBean.SubjectsBean> data) {
        super(R.layout.item_movie_latest,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper,final HotMovieBean.SubjectsBean item) {
        helper.setText(R.id.tv_one_title,item.getTitle());
        helper.setText(R.id.tv_one_directors,  StringFormatUtil.formatLatestName(item.getDirectors()));
        helper.setText(R.id.tv_one_casts,  StringFormatUtil.formatLatestCastsName(item.getCasts()));
        helper.setText(R.id.tv_one_genres,"评分：" + item.getRating().getAverage());
        helper.setText(R.id.tv_collect_count,item.getCollect_count()+"人看过");
        GlideUtils.loadMovieLatestImg((ImageView) helper.getView(R.id.iv_one_photo),item.getImages().getLarge());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(item, helper.getView(R.id.iv_one_photo));
            }
        });
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(HotMovieBean.SubjectsBean positionData, View view);}
}
