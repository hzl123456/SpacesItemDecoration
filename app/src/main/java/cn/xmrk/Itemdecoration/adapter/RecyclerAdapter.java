package cn.xmrk.Itemdecoration.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.xmrk.Itemdecoration.R;
import cn.xmrk.Itemdecoration.pojo.AdapterInfo;

/**
 * 作者：请叫我百米冲刺 on 2016/12/6 下午1:33
 * 邮箱：mail@hezhilin.cc
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<AdapterInfo> mDatas;

    public RecyclerAdapter(@NonNull List<AdapterInfo> mDatas) {
        this.mDatas = mDatas;
    }

    public List<AdapterInfo> getmDatas() {
        return mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerAdapter.ViewHolder(View.inflate(parent.getContext(), R.layout.item_recycler, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AdapterInfo info = mDatas.get(position);
//        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
//            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
//            layoutParams.setFullSpan(position % 3 == 0);
//        }
        if (!TextUtils.isEmpty(info.message)) {
            holder.tvInfo.setText(info.message);
        } else {
            holder.tvInfo.setText(R.string.long_text);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvInfo = (TextView) itemView.findViewById(R.id.tv_info);
        }
    }
}
