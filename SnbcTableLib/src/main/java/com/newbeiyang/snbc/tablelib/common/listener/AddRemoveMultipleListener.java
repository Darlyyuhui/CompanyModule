package com.newbeiyang.snbc.tablelib.common.listener;

/**
 * 内部增加行列接口
 * 包名称：com.newbeiyang.snbc.tablelib.common.listener
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/18 9:23
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface AddRemoveMultipleListener {
    //增加行列
    void onAdd();
    //減少行列
    void onMinus();

}
