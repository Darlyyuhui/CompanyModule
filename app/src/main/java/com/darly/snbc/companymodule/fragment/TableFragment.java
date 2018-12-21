package com.darly.snbc.companymodule.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.darly.snbc.companymodule.R;
import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.TableEditEnum;
import com.newbeiyang.snbc.tablelib.common.listener.AddRemoveMultipleListener;
import com.newbeiyang.snbc.tablelib.common.listener.OnTableSelectListener;
import com.newbeiyang.snbc.tablelib.common.listener.TableLineListener;
import com.newbeiyang.snbc.tablelib.ui.fragment.BaseTableFragment;
import com.newbeiyang.snbc.tablelib.ui.widget.AddRemoveMultipleView;
import com.newbeiyang.snbc.tablelib.ui.widget.TableLineView;

/**
 * 自定义UI
 * 包名称：com.darly.snbc.companymodule.fragment
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/20 15:24
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TableFragment extends BaseTableFragment implements View.OnClickListener {

    private OnTableSelectListener onTableSelectListener;

    private ImageView id_table_fragment_fragment_vertical;

    private ImageView id_table_fragment_fragment_horizontal;


    private AddRemoveMultipleView id_table_fragment_fragment_cloumn;

    private AddRemoveMultipleView id_table_fragment_fragment_row;


    private TableLineView id_table_fragment_fragment_out_line;

    private TableLineView id_table_fragment_fragment_in_line;

    @Override
    protected int root() {
        return R.layout.fragment_table;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_table_fragment_fragment_vertical = rootView.findViewById(R.id.id_table_fragment_fragment_vertical);
        id_table_fragment_fragment_horizontal = rootView.findViewById(R.id.id_table_fragment_fragment_horizontal);
        id_table_fragment_fragment_cloumn = rootView.findViewById(R.id.id_table_fragment_fragment_cloumn);
        id_table_fragment_fragment_row = rootView.findViewById(R.id.id_table_fragment_fragment_row);

        id_table_fragment_fragment_out_line = rootView.findViewById(R.id.id_table_fragment_fragment_out_line);
        id_table_fragment_fragment_in_line = rootView.findViewById(R.id.id_table_fragment_fragment_in_line);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
//列选项
        id_table_fragment_fragment_cloumn.setAddRemoveMultipleListener(new AddRemoveMultipleListener() {
            @Override
            public void onAdd() {
                TableEditBean bean = new TableEditBean(id_table_fragment_fragment_cloumn.getResult(), 0);
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_ADD);
            }

            @Override
            public void onMinus() {
                TableEditBean bean = new TableEditBean(id_table_fragment_fragment_cloumn.getResult(), 0);
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_MINUS);
            }
        });
        //行选项
        id_table_fragment_fragment_row.setAddRemoveMultipleListener(new AddRemoveMultipleListener() {
            @Override
            public void onAdd() {
                TableEditBean bean = new TableEditBean(0, id_table_fragment_fragment_row.getResult());
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_ADD);
            }

            @Override
            public void onMinus() {
                TableEditBean bean = new TableEditBean(0, id_table_fragment_fragment_row.getResult());
                onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_CLOUMN_ROW_MINUS);
            }
        });
        id_table_fragment_fragment_vertical.setOnClickListener(this);
        id_table_fragment_fragment_horizontal.setOnClickListener(this);

        id_table_fragment_fragment_out_line.setTableLineListener(new TableLineListener() {
            @Override
            public void onChecked(TableEditBean bean) {
                if (onTableSelectListener != null)
                    onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_OUT_LINE);
            }
        }, true);
        id_table_fragment_fragment_in_line.setTableLineListener(new TableLineListener() {
            @Override
            public void onChecked(TableEditBean bean) {
                if (onTableSelectListener != null)
                    onTableSelectListener.onSelect(bean, TableEditEnum.TABLE_IN_LINE);
            }
        }, false);

    }

    @Override
    public void setDefaultCount(int column, int row) {
    }

    @Override
    public void resetFocuss(TableEditBean paramer) {
        id_table_fragment_fragment_cloumn.setDefaultCount(paramer.getCloumn());
        id_table_fragment_fragment_row.setDefaultCount(paramer.getRow());

        id_table_fragment_fragment_out_line.setDefaultConfig(paramer.getExtcolor(),paramer.getExttype());
        id_table_fragment_fragment_in_line.setDefaultConfig(paramer.getIncolor(),paramer.getIntype());
    }

    @Override
    public void setOnTableSelectListener(OnTableSelectListener listener) {
        onTableSelectListener = listener;
    }


    @Override
    public void onClick(View v) {
        TableEditBean bean = new TableEditBean(id_table_fragment_fragment_cloumn.getResult(), id_table_fragment_fragment_row.getResult(), "", id_table_fragment_fragment_in_line.getLineColor(), id_table_fragment_fragment_in_line.getLineRes(), id_table_fragment_fragment_out_line.getLineColor(), id_table_fragment_fragment_out_line.getLineRes());
        if (v.getId() == id_table_fragment_fragment_horizontal.getId()) {
            onTableSelectListener.onSelect(bean, TableEditEnum.HORIZONTAL);
        } else if (v.getId() == id_table_fragment_fragment_vertical.getId()) {
            onTableSelectListener.onSelect(bean, TableEditEnum.VERTICAL);
        }
    }
}
