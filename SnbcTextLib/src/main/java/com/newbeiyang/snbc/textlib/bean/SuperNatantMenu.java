package com.newbeiyang.snbc.textlib.bean;

/**
 * 包名称：com.newbeiyang.snbc.textlib.bean
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/12 15:33
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class SuperNatantMenu {

    private String name;
    private int mSelectIcon;
    private int mNormalIcon;
    private int selectDrawable;

    public SuperNatantMenu(String name, int mSelectIcon, int mNormalIcon, int selectDrawable) {
        this.name = name;
        this.mSelectIcon = mSelectIcon;
        this.mNormalIcon = mNormalIcon;
        this.selectDrawable = selectDrawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmSelectIcon() {
        return mSelectIcon;
    }

    public void setmSelectIcon(int mSelectIcon) {
        this.mSelectIcon = mSelectIcon;
    }

    public int getmNormalIcon() {
        return mNormalIcon;
    }

    public void setmNormalIcon(int mNormalIcon) {
        this.mNormalIcon = mNormalIcon;
    }

    public int getSelectDrawable() {
        return selectDrawable;
    }

    public void setSelectDrawable(int selectDrawable) {
        this.selectDrawable = selectDrawable;
    }
}
