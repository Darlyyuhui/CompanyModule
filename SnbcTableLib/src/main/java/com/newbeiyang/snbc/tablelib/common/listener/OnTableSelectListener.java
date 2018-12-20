package com.newbeiyang.snbc.tablelib.common.listener;

import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.TableEditEnum;

/**
 * Fragment中选中Table后，传递出去。
 * 包名称：com.newbeiyang.snbc.tablelib.common.listener
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 11:32
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface OnTableSelectListener {

    void onSelect(TableEditBean tableEditBean,TableEditEnum direction);

}
