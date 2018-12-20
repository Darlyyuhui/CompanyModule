package com.darly.snbc.widget.table.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.darly.snbc.companymodule.R;
import com.newbeiyang.snbc.textlib.common.observer.SupernatantCfg;

import java.util.List;

/**
 * Created by maxiao on 2018/10/19.
 */
public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void itemClick(TextView tv,int position);
    }

    private List<String> mDatas;
    private static int row;

    private OnItemClickListener listener;
    public TableAdapter(List<String> data, int row) {
        this.mDatas = data;
        this.row = row;
    }

    public void setDatas(List<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_excel_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        if (mDatas == null) {
            return;
        }
        final String recover = mDatas.get(position);
        if (recover == null) {
            return;
        }
        viewHolder.title.setText(recover);
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.itemClick(viewHolder.title,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public final LinearLayout id_excel_liner;

        public ViewHolder(View v) {
            super(v);
            id_excel_liner = v.findViewById(R.id.id_excel_liner);
            title = (TextView) v.findViewById(R.id.id_excel_edit);
            if (row > 1) {
                TableRow.LayoutParams rl = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SupernatantCfg.getWidth() / (2 * row));
                id_excel_liner.setLayoutParams(rl);
            }
        }
    }

    public void setNewData(List<String> data, int row) {
        this.mDatas = data;
        this.row = row;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public List<String> getData() {
        return mDatas;
    }


    public String getItem(int position) {
        return mDatas.get(position);
    }
}
