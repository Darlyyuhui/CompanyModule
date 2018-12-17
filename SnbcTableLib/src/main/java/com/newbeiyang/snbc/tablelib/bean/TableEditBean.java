package com.newbeiyang.snbc.tablelib.bean;

/**
 * Table库封装返回对象类
 * 包名称：com.newbeiyang.snbc.tablelib.bean
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 10:44
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TableEditBean {
    //列数
    private int cloumn;
    //行数
    private int row;
    //内部汉字
    private String texts;
    //内边框颜色
    private int incolor;
    //内边框类型
    private int intype;
    //外边框颜色
    private int extcolor;
    //外边框类型
    private int exttype;

    public TableEditBean(int cloumn, int row, String texts, int incolor, int intype, int extcolor, int exttype) {
        this.cloumn = cloumn;
        this.row = row;
        this.texts = texts;
        this.incolor = incolor;
        this.intype = intype;
        this.extcolor = extcolor;
        this.exttype = exttype;
    }

    public int getCloumn() {
        return cloumn;
    }

    public void setCloumn(int cloumn) {
        this.cloumn = cloumn;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public int getIncolor() {
        return incolor;
    }

    public void setIncolor(int incolor) {
        this.incolor = incolor;
    }

    public int getIntype() {
        return intype;
    }

    public void setIntype(int intype) {
        this.intype = intype;
    }

    public int getExtcolor() {
        return extcolor;
    }

    public void setExtcolor(int extcolor) {
        this.extcolor = extcolor;
    }

    public int getExttype() {
        return exttype;
    }

    public void setExttype(int exttype) {
        this.exttype = exttype;
    }
}
