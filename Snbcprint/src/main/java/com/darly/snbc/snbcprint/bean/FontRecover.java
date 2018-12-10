package com.darly.snbc.snbcprint.bean;

import android.graphics.Typeface;

/**
 * 字体信息
 * 包名称：com.darly.snbc.snbcprint.bean
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 16:05
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class FontRecover {

    private String fontName;

    private Typeface typeface;

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}
