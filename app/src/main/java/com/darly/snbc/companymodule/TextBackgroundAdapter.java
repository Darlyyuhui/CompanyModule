package com.darly.snbc.companymodule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.darly.snbc.observer.SupernatantCfg;

import java.util.List;

/**
 * Created by maxiao on 2018/10/19.
 */
public class TextBackgroundAdapter extends RecyclerView.Adapter<TextBackgroundAdapter.ViewHolder> {
    private List<Integer> mDatas;
    private OnItemListener onItemClickListener;

    public TextBackgroundAdapter(List<Integer> data) {
        this.mDatas = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fragment_bg, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        if (mDatas == null) {
            return;
        }
        viewHolder.id_item_fragment_bg_iv.setImageResource(mDatas.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
                onItemClickListener.onItemClick(mDatas.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setItemClickListener(OnItemListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView id_item_fragment_bg_iv;

        public ViewHolder(View v) {
            super(v);
            id_item_fragment_bg_iv = (ImageView) v.findViewById(R.id.id_item_fragment_bg_iv);
            id_item_fragment_bg_iv.setLayoutParams(new ViewGroup.LayoutParams(SupernatantCfg.getWidth()/4,SupernatantCfg.getWidth()/4));
        }
    }


    public interface OnItemListener {
        void onItemClick(int resId, int position);
    }

}
