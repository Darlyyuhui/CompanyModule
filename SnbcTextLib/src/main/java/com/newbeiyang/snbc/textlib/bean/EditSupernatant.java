package com.newbeiyang.snbc.textlib.bean;

import android.graphics.Typeface;

/**
 * 文字操作对象参数集合
 * 包名称：com.darly.snbc.snbcprint.bean
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/11 17:16
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class EditSupernatant {
    //背景
    //背景名称
    private String bgName;
    //背景地址
    private int resId;
    //背景展示地址
    private int resShow;
    //字体
    //字体名称
    private String fontName;
    //字体位置
    private Typeface typeface;
    //对齐
    //加黑
    private boolean isBold;
    //斜体
    private boolean isItaic;
    //底线
    private boolean isUnderLine;
    //1左对齐。2居中。3右对齐
    private int gravy;
    //-1左移动。1右移动
    private int move;
    //样式
    //字号名称（五号）
    private String sizeName;
    //字号
    private int size;
    //行距
    private float lineSpacingMultiplier;

    public EditSupernatant() {
    }

    //背景初始化
    public EditSupernatant(String bgName, int resId, int resShow) {
        this.bgName = bgName;
        this.resId = resId;
        this.resShow = resShow;
    }

    //字体初始化
    public EditSupernatant(String fontName, Typeface typeface) {
        this.fontName = fontName;
        this.typeface = typeface;
    }

    //字号初始化
    public EditSupernatant(String sizeName, int size) {
        this.sizeName = sizeName;
        this.size = size;
    }

    //间距初始化
    public EditSupernatant(float lineSpacingMultiplier) {
        this.lineSpacingMultiplier = lineSpacingMultiplier;
    }

    public String getBgName() {
        return bgName;
    }

    public void setBgName(String bgName) {
        this.bgName = bgName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getResShow() {
        return resShow;
    }

    public void setResShow(int resShow) {
        this.resShow = resShow;
    }

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

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean isItaic() {
        return isItaic;
    }

    public void setItaic(boolean itaic) {
        isItaic = itaic;
    }

    public boolean isUnderLine() {
        return isUnderLine;
    }

    public void setUnderLine(boolean underLine) {
        isUnderLine = underLine;
    }

    public int getGravy() {
        return gravy;
    }

    public void setGravy(int gravy) {
        this.gravy = gravy;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getLineSpacingMultiplier() {
        return lineSpacingMultiplier;
    }

    public void setLineSpacingMultiplier(float lineSpacingMultiplier) {
        this.lineSpacingMultiplier = lineSpacingMultiplier;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public void reset(){
        bgName = null;
        resId = 0;
        resShow = 0;
        fontName = null;
        typeface = null;
        isBold = false;
        isItaic = false;
        isUnderLine = false;
        gravy = 0;
        move = 0;
        sizeName = null;
        size = 0;
        lineSpacingMultiplier = 0;
    }
}
