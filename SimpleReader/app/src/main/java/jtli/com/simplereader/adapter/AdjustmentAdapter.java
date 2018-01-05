package jtli.com.simplereader.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jtli.com.simplereader.R;

/**
 * Created by quantan.liu on 2017/3/26.
 */

public class AdjustmentAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public AdjustmentAdapter(List<String> data) {
        super(R.layout.item_adjustment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adjustment_name, item);
    }
}
