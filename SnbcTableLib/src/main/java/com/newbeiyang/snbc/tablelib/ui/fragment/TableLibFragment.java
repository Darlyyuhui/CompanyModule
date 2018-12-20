package com.newbeiyang.snbc.tablelib.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.newbeiyang.snbc.tablelib.R;
import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.TableEditEnum;
import com.newbeiyang.snbc.tablelib.common.listener.AddRemoveMultipleListener;
import com.newbeiyang.snbc.tablelib.common.listener.OnTableSelectListener;
import com.newbeiyang.snbc.tablelib.common.listener.TableLineListener;
import com.newbeiyang.snbc.tablelib.ui.widget.AddRemoveMultipleView;
import com.newbeiyang.snbc.tablelib.ui.widget.TableLineView;

/**
 * 默认UI界面的Table生成器
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


    private TableLineView table_lib_fragment_out_line;

    private TableLineView table_lib_fragment_in_line;

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

        table_lib_fragment_out_line = rootView.findViewById(R.id.table_lib_fragment_out_line);
        table_lib_fragment_in_line = rootView.findViewById(R.id.table_lib_fragment_in_line);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        //列选项
        table_lib_fragment_cloumn.setAddRemoveMultipleListener(new AddRemoveMultipleListener() {
            @Override
            public void onAdd() {
                TableEditBean bean = new TableEditBean(table_lib_fragment_cloumn.getResult(), 0);
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_ADD);
            }

            @Override
            public void onMinus() {
                TableEditBean bean = new TableEditBean(table_lib_fragment_cloumn.getResult(), 0);
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_MINUS);
            }
        });
        //行选项
        table_lib_fragment_row.setAddRemoveMultipleListener(new AddRemoveMultipleListener() {
            @Override
            public void onAdd() {
                TableEditBean bean = new TableEditBean(0, table_lib_fragment_row.getResult());
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_ADD);
            }

            @Override
            public void onMinus() {
                TableEditBean bean = new TableEditBean(table_lib_fragment_cloumn.getResult(), 0);
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_MINUS);
            }
        });
        table_lib_fragment_vertical.setOnClickListener(this);
        table_lib_fragment_horizontal.setOnClickListener(this);

        table_lib_fragment_out_line.setTableLineListener(new TableLineListener() {
            @Override
            public void onChecked(TableEditBean bean) {
                if (onTableSelectListener != null)
                    onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_OUT_LINE);
            }
        }, true);
        table_lib_fragment_in_line.setTableLineListener(new TableLineListener() {
            @Override
            public void onChecked(TableEditBean bean) {
                if (onTableSelectListener != null)
                    onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_IN_LINE);
            }
        }, false);

    }

    @Override
    public void setDefaultCount(int column, int row) {
        table_lib_fragment_cloumn.setDefaultCount(column);
        table_lib_fragment_row.setDefaultCount(row);
    }

    @Override
    public void setOnTableSelectListener(OnTableSelectListener listener) {
        onTableSelectListener = listener;
    }


    @Override
    public void onClick(View v) {
        TableEditBean bean = new TableEditBean(table_lib_fragment_cloumn.getResult(), table_lib_fragment_row.getResult(), "", table_lib_fragment_in_line.getLineColor(), table_lib_fragment_in_line.getLineRes(), table_lib_fragment_out_line.getLineColor(), table_lib_fragment_out_line.getLineRes());
        if (v.getId() == table_lib_fragment_horizontal.getId()) {
            onTableSelectListener.onSelect(bean, TableEditEnum.HORIZONTAL);
        } else if (v.getId() == table_lib_fragment_vertical.getId()) {
            onTableSelectListener.onSelect(bean, TableEditEnum.VERTICAL);
        }
    }
}
