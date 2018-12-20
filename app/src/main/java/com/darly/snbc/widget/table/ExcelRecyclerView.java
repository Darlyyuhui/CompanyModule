package com.darly.snbc.widget.table;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.darly.snbc.widget.table.adapter.TableAdapter;

import java.util.ArrayList;

/**
 * 表格生成控件
 * 包名称：com.darly.snbc.widget.table
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/18 14:39
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ExcelRecyclerView extends RecyclerView {

    int MAX_COLUMN = 8;
    int MAX_ROW = 8;
    private LinearLayoutManager layoutManager;
    private TableAdapter mAdapter;
    private int mColumn = 3;
    private GridInsideDivider mInsideDivider;
    private GridOutsideDivider mOutsideDivider;
    private int mRow = 3;

    public ExcelRecyclerView(@NonNull Context context) {
        super(context);
        setLayerType(1, null);
        setHasFixedSize(true);
        setNestedScrollingEnabled(false);
    }


    private void createData() {
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < this.mColumn) {
            int j = 0;
            while (j < this.mRow) {
                if (i == 0 && j == 0) {
                    localArrayList.add("请点击输入文字");
                } else {
                    localArrayList.add("");
                }
                j += 1;
            }
            i += 1;
        }
        this.mAdapter.setNewData(localArrayList, mRow);
    }

    public void addColumn() {
        if (this.mColumn > this.MAX_COLUMN) {
            return;
        }
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < this.mRow) {
            int j = 0;
            while (j < this.mColumn + 1) {
                if (j == this.mColumn) {
                    localArrayList.add("");
                } else {
                    localArrayList.add(this.mAdapter.getData().get(this.mColumn * i + j));
                }
                j += 1;
            }
            i += 1;
        }
        this.mColumn += 1;
        setLayoutManager(new GridLayoutManager(getContext(), this.mColumn));
        this.mAdapter.setNewData(localArrayList, mRow);
        setInsideDivider(this.mInsideDivider);
        setOutsideDivider(this.mOutsideDivider);

    }

    public void addRow() {
        if (this.mRow > this.MAX_ROW) {
            return;
        }
        int i = 0;
        while (i < this.mColumn) {
            this.mAdapter.getData().add("");
            i += 1;
        }
        this.mRow += 1;
        setLayoutManager(new GridLayoutManager(getContext(), this.mColumn));
        this.mAdapter.setNewData(mAdapter.getData(), mRow);
        setInsideDivider(this.mInsideDivider);
        setOutsideDivider(this.mOutsideDivider);
    }

    public void deleteColumn() {
        if (this.mColumn < 2) {
            return;
        }
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        while (i < this.mRow) {
            int j = 0;
            while (j < this.mColumn) {
                if (j != this.mColumn - 1) {
                    localArrayList.add(this.mAdapter.getItem(this.mColumn * i + j));
                }
                j += 1;
            }
            i += 1;
        }
        this.mColumn -= 1;
        setLayoutManager(new GridLayoutManager(getContext(), this.mColumn));
        this.mAdapter.setNewData(localArrayList, mRow);
        setInsideDivider(this.mInsideDivider);
        setOutsideDivider(this.mOutsideDivider);
    }

    public void deleteRow() {
        if (this.mRow < 2) {
            return;
        }
        int i = 0;
        while (i < this.mColumn) {
            this.mAdapter.getData().remove(this.mAdapter.getData().size() - 1);
            i += 1;
        }
        this.mRow -= 1;
        setLayoutManager(new GridLayoutManager(getContext(), this.mColumn));
        this.mAdapter.setNewData(mAdapter.getData(), mRow);
        setInsideDivider(this.mInsideDivider);
        setOutsideDivider(this.mOutsideDivider);
    }


    public void initAdapter(int row, int column) {
        this.mRow = row;
        this.mColumn = column;
        this.layoutManager = new GridLayoutManager(getContext(), this.mColumn);
        setLayoutManager(this.layoutManager);
        this.mAdapter = new TableAdapter(null, row);
        setAdapter(this.mAdapter);
        setInsideDivider(new GridInsideDivider(this.mColumn, 4, 4, Color.parseColor("#000000")));
        setOutsideDivider(new GridOutsideDivider(this.mColumn, 4, 4, Color.parseColor("#000000")));
        createData();
    }

    public void setInsideDivider(GridInsideDivider paramGridInsideDivider) {
        if (paramGridInsideDivider == null) {
            throw new RuntimeException("can not be null ");
        }
        if (this.mInsideDivider != null) {
            removeItemDecoration(this.mInsideDivider);
        }
        this.mInsideDivider = paramGridInsideDivider;
        this.mInsideDivider.setSpanCount(this.mColumn);
        addItemDecoration(this.mInsideDivider);
    }

    public void setOutsideDivider(GridOutsideDivider paramGridOutsideDivider) {
        if (paramGridOutsideDivider == null) {
            throw new RuntimeException("can not be null ");
        }
        if (this.mOutsideDivider != null) {
            removeItemDecoration(this.mOutsideDivider);
        }
        this.mOutsideDivider = paramGridOutsideDivider;
        this.mOutsideDivider.setSpanCount(this.mColumn);
        addItemDecoration(this.mOutsideDivider);
    }


    public void setOnItemClickListener(TableAdapter.OnItemClickListener listener) {
        mAdapter.setOnItemClickListener(listener);
    }
}
