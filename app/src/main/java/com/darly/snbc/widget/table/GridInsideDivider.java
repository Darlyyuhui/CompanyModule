package com.darly.snbc.widget.table;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 内边框线条
 * 包名称：com.darly.snbc.widget.table
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/18 14:46
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class GridInsideDivider extends RecyclerView.ItemDecoration {
    private int mLineWidth;
    private Paint mPaint;
    private int mSpacing;
    private int mSpanCount;

    public GridInsideDivider(int mSpacing, int mLineWidth, int color) {
        this(0, mSpacing, mLineWidth, color);
    }

    public GridInsideDivider(int mSpanCount, int mSpacing, int mLineWidth, int color) {
        this.mSpanCount = mSpanCount;
        this.mSpacing = mSpacing;
        this.mLineWidth = mLineWidth;
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mLineWidth);
        this.mPaint.setColor(color);
    }

    private void draw(Canvas paramCanvas, RecyclerView paramRecyclerView) {
        int j = paramRecyclerView.getChildCount();
        int i = 0;
        while (i < j) {
            Object localObject = paramRecyclerView.getChildAt(i);
            RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams) ((View) localObject).getLayoutParams();
            int k = ((View) localObject).getLeft();
            int m = localLayoutParams.leftMargin;
            int n = ((View) localObject).getRight();
            int i1 = localLayoutParams.rightMargin;
            int i2;
            float f;
            if (i < j - this.mSpanCount) {
                i2 = ((View) localObject).getBottom() + localLayoutParams.bottomMargin;
                f = (i2 + (this.mSpacing + i2)) / 2.0F;
                DashPathEffect localDashPathEffect = new DashPathEffect(new float[]{6.0F, 4.0F}, 0.0F);
                this.mPaint.setPathEffect(localDashPathEffect);
                paramCanvas.drawLine(k - m, f, n + i1, f, this.mPaint);
            }
            k = ((View) localObject).getTop();
            m = localLayoutParams.topMargin;
            n = ((View) localObject).getBottom();
            i1 = localLayoutParams.bottomMargin;
            if (i % this.mSpanCount != this.mSpanCount - 1) {
                i2 = ((View) localObject).getRight() + localLayoutParams.rightMargin;
                f = (i2 + (this.mSpacing + i2)) / 2.0F;
                localObject = new DashPathEffect(new float[]{6.0F, 4.0F}, 0.0F);
                this.mPaint.setPathEffect((PathEffect) localObject);
                paramCanvas.drawLine(f, k - m, f, n + i1, this.mPaint);
            }
            i += 1;
        }
    }

    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
        int i = paramRecyclerView.getAdapter().getItemCount();
        int j = paramRecyclerView.getChildAdapterPosition(paramView);
        if (i > 0) {
            if (j < i - this.mSpanCount) {
                paramRect.bottom = this.mSpacing;
            }
            if (j % this.mSpanCount == this.mSpanCount - 1) {
                return;
            }
            paramRect.right = this.mSpacing;
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState) {
        super.onDraw(paramCanvas, paramRecyclerView, paramState);
        draw(paramCanvas, paramRecyclerView);
    }

    public void setSpanCount(int paramInt) {
        this.mSpanCount = paramInt;
    }

}
