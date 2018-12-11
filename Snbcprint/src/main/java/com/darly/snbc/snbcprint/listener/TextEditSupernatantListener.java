package com.darly.snbc.snbcprint.listener;

import com.darly.snbc.snbcprint.bean.EditSupernatant;
import com.darly.snbc.snbcprint.common.SuperNatantEnum;

/**
 * 文本编辑浮层接口
 * 包名称：com.darly.snbc.snbcprint.listener
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 9:40
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface TextEditSupernatantListener {
    //文字操作回调。
    void onSelectSupernatant(EditSupernatant supernatant, SuperNatantEnum type);
}
