package com.newbeiyang.snbc.textlib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.newbeiyang.snbc.textlib.R;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.observer.SupernatantCfg;

import java.util.List;

/**
 * 文字背景适配器
 * 包名称：com.darly.snbc.snbcprint.adapter
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 13:40
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextBackgroundAdapter extends RecyclerView.Adapter<TextBackgroundAdapter.ViewHolder> {
    private List<EditSupernatant> mDatas;
    private OnItemListener onItemClickListener;

    public TextBackgroundAdapter(List<EditSupernatant> data) {
        this.mDatas = data;
    }

    public void setDatas(List<EditSupernatant> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
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
        final EditSupernatant supernatant = mDatas.get(position);
        viewHolder.id_item_fragment_bg_iv.setImageResource(supernatant.getResShow());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
                onItemClickListener.onItemClick(supernatant.getResId(), position);
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
            id_item_fragment_bg_iv.setLayoutParams(new LinearLayout.LayoutParams(SupernatantCfg.getWidth() / 6, SupernatantCfg.getWidth() / 6));
        }
    }


    public interface OnItemListener {
        void onItemClick(int resId, int position);
    }

}
