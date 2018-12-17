package com.newbeiyang.snbc.tablelib.common.listener;

import com.newbeiyang.snbc.tablelib.bean.TableEditBean;

/**
 * 表格选择回调接口
 * 包名称：com.newbeiyang.snbc.tablelib.common.listener
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 11:10
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface TableEditListener {
    //Table点击后回调，根据返回参数建立表格
    void onTableCreate(TableEditBean tableEditBean);

}
