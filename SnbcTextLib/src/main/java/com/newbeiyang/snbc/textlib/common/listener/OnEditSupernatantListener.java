package com.newbeiyang.snbc.textlib.common.listener;

import com.newbeiyang.snbc.textlib.bean.EditSupernatant;

/**
 * 文字操作对外提供用户接口。
 * 包名称：com.darly.snbc.snbcprint.bean
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/11 17:16
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface OnEditSupernatantListener {
    //背景对外接口
    void getBackGround(EditSupernatant background);
    //字体对外接口
    void getTypeface(EditSupernatant font);
    //对齐对外接口
    void getAlign(EditSupernatant align);
}
