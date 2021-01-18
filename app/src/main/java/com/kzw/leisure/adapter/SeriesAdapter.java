package com.kzw.leisure.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kzw.leisure.R;
import com.kzw.leisure.bean.VideoBean;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: kang4
 * Date: 2019/12/3
 * Description:
 */
public class SeriesAdapter extends BaseQuickAdapter<VideoBean.Series, BaseViewHolder> {

    private OnClickListener clickListener;

    public SeriesAdapter() {
        super(R.layout.video_series_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean.Series item) {
        helper.setText(R.id.type, item.getUrlType());
        View view = helper.getView(R.id.recyclerView);
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            SeriesChildAdapter adapter = new SeriesChildAdapter();
            ((RecyclerView) view).setAdapter(adapter);
            adapter.setNewData(item.getList());
            adapter.setOnItemClickListener((adapter1, view1, position) -> {
                List list = adapter1.getData();
                Object object = list.get(position);
                if (object instanceof VideoBean.Series.Url) {
                    if (clickListener != null) {
                        clickListener.Click(((VideoBean.Series.Url) object),item.getList());
                    }
                }
            });
        }
    }

    public interface OnClickListener {
        void Click(VideoBean.Series.Url item,List<VideoBean.Series.Url> list);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.clickListener = listener;
    }
}
