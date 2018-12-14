package com.newbeiyang.snbc.textlib.bean;

/**
 * 菜单数据对象集合
 * 包名称：com.newbeiyang.snbc.textlib.bean
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/12 15:33
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class SuperNatantMenu {
    //唯一识别类型
    private String type;
    //菜单名称
    private String name;
    //菜单选中图片
    private int mSelectIcon;
    //菜单默认图片
    private int mNormalIcon;
    //菜单点选资源
    private int selectDrawable;
    public SuperNatantMenu(String type, String name, int mSelectIcon, int mNormalIcon, int selectDrawable) {
        this.type = type;
        this.name = name;
        this.mSelectIcon = mSelectIcon;
        this.mNormalIcon = mNormalIcon;
        this.selectDrawable = selectDrawable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
