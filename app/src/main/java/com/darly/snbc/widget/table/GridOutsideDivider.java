package com.darly.snbc.widget.table;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 外边框线条
 * 包名称：com.darly.snbc.widget.table
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/18 14:51
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class GridOutsideDivider extends RecyclerView.ItemDecoration {
    private int mLineWidth = 5;
    private Paint mPaint;
    private int mSpacing;
    private int mSpanCount;


    public GridOutsideDivider(int mSpacing, int mLineWidth, int color) {
        this(0, mSpacing, mLineWidth, color);
    }

    public GridOutsideDivider(int mSpanCount, int mSpacing, int mLineWidth, int color) {
        this.mSpanCount = mSpanCount;
        this.mSpacing = mSpacing;
        this.mLineWidth = mLineWidth;
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mLineWidth);
        this.mPaint.setColor(color);
    }

    private void draw(Canvas paramCanvas, RecyclerView paramRecyclerView) {
        int i = paramRecyclerView.getChildCount();
        if (i > 0) {
            View localView = paramRecyclerView.getChildAt(0);
            View paramView = paramRecyclerView.getChildAt(i - 1);
            RecyclerView.LayoutParams localLayoutParams1 = (RecyclerView.LayoutParams) localView.getLayoutParams();
            RecyclerView.LayoutParams localLayoutParams2 = (RecyclerView.LayoutParams) paramView.getLayoutParams();
            paramCanvas.drawRect(new RectF(localView.getLeft() - localLayoutParams1.leftMargin - this.mSpacing + this.mLineWidth / 2.0F, localView.getTop() - localLayoutParams1.topMargin - this.mSpacing + this.mLineWidth / 2.0F, paramView.getRight() + localLayoutParams2.rightMargin + this.mSpacing - this.mLineWidth / 2.0F, paramView.getBottom() + localLayoutParams2.bottomMargin + this.mSpacing - this.mLineWidth / 2.0F), this.mPaint);
        }
    }

    public int geSpanCount() {
        return this.mSpanCount;
    }

    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
        int i = paramRecyclerView.getAdapter().getItemCount();
        int j = paramRecyclerView.getChildAdapterPosition(paramView);
        if (j < this.mSpanCount) {
            paramRect.top = this.mSpacing;
        }
        if (j >= i - this.mSpanCount) {
            paramRect.bottom = this.mSpacing;
        }
        if (j % this.mSpanCount == 0) {
            paramRect.left = this.mSpacing;
        }
        if (j % this.mSpanCount == this.mSpanCount - 1) {
            paramRect.right = this.mSpacing;
        }
    }

    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
        super.onDraw(paramCanvas, paramRecyclerView, paramState);
        draw(paramCanvas, paramRecyclerView);
    }

    public void setSpanCount(int paramInt) {
        this.mSpanCount = paramInt;
    }

}
