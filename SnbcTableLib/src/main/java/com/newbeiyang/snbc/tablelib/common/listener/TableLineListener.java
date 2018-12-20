package com.newbeiyang.snbc.tablelib.common.listener;

import com.newbeiyang.snbc.tablelib.bean.TableEditBean;

/**
 * 内部修改边框接口
 * 包名称：com.newbeiyang.snbc.tablelib.common.listener
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/18 9:23
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface TableLineListener {
    //修改边框
    void onChecked(TableEditBean bean);
}
