package com.darly.snbc.snbcprint.bean;

/**
 * 文本编辑浮层对齐粗细选择类
 * 包名称：com.darly.snbc.snbcprint.bean
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 9:40
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class AlignmentThickness {

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
}
