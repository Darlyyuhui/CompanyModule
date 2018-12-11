package com.darly.snbc.companymodule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darly.snbc.snbcprint.R;

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


    public void setmDatas(List<Integer> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text_font, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        if (mDatas == null) {
            return;
        }
        viewHolder.title.setBackgroundResource(mDatas.get(position));
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
        public final TextView title;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
        }
    }


    public interface OnItemListener {
        void onItemClick(int resId, int position);
    }

}
