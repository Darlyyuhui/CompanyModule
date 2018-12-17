package com.newbeiyang.snbc.tablelib.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.newbeiyang.snbc.tablelib.R;
import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.listener.OnTableSelectListener;
import com.newbeiyang.snbc.tablelib.ui.widget.AddRemoveMultipleView;

/**
 * 包名称：com.newbeiyang.snbc.tablelib.ui.fragment
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 11:22
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TableLibFragment extends BaseTableFragment implements View.OnClickListener {

    private OnTableSelectListener onTableSelectListener;

    private ImageView table_lib_fragment_vertical;

    private ImageView table_lib_fragment_horizontal;


    private AddRemoveMultipleView table_lib_fragment_cloumn;
    private AddRemoveMultipleView table_lib_fragment_row;

    @Override
    protected int root() {
        return R.layout.fragment_table_lib;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        table_lib_fragment_vertical = rootView.findViewById(R.id.table_lib_fragment_vertical);
        table_lib_fragment_horizontal = rootView.findViewById(R.id.table_lib_fragment_horizontal);
        table_lib_fragment_cloumn = rootView.findViewById(R.id.table_lib_fragment_cloumn);
        table_lib_fragment_row = rootView.findViewById(R.id.table_lib_fragment_row);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        table_lib_fragment_vertical.setOnClickListener(this);
        table_lib_fragment_horizontal.setOnClickListener(this);

    }

    @Override
    public void setOnTableSelectListener(OnTableSelectListener listener) {
        onTableSelectListener = listener;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == table_lib_fragment_horizontal.getId() || v.getId() == table_lib_fragment_vertical.getId()) {
            TableEditBean bean = new TableEditBean(table_lib_fragment_cloumn.getResult(), table_lib_fragment_row.getResult(), "", R.color.table_lib_count, R.color.table_lib_show_nor, 1, 1);
            onTableSelectListener.onSelect(bean);
        }
    }
}
