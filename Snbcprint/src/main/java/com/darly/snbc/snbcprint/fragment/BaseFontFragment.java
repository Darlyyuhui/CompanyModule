package com.darly.snbc.snbcprint.fragment;

import android.support.v4.app.Fragment;

import com.darly.snbc.snbcprint.bean.FontRecover;
import com.darly.snbc.snbcprint.listener.TextFontListener;

import java.util.List;

/**
 * 基础Fragment类，定义一些方法
 * 包名称：com.darly.snbc.snbcprint.fragment
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 16:16
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public abstract class BaseFontFragment extends Fragment {

    public abstract void setFontData(List<FontRecover> fontData);

    public abstract void setFont(TextFontListener callBack);
}
