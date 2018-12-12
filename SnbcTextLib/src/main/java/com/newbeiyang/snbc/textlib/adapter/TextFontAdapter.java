package com.newbeiyang.snbc.textlib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newbeiyang.snbc.textlib.R;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;

import java.util.List;

/**
 * Created by maxiao on 2018/10/19.
 */
public class TextFontAdapter extends RecyclerView.Adapter<TextFontAdapter.ViewHolder> {
    private List<EditSupernatant> mDatas;
    private OnShowItemClickListener onItemClickListener;

    public TextFontAdapter(List<EditSupernatant> data) {
        this.mDatas = data;
    }

    public void setDatas(List<EditSupernatant> mDatas) {
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
        final EditSupernatant recover = mDatas.get(position);
        if (recover == null) {
            return;
        }
        viewHolder.title.setText(recover.getFontName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
                onItemClickListener.onItemClick(recover, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setItemClickListener(OnShowItemClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
        }
    }


    public interface OnShowItemClickListener {
        void onItemClick(EditSupernatant recover, int position);
    }

}
