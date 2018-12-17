package com.newbeiyang.snbc.tablelib.ui.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.newbeiyang.snbc.tablelib.R;


/**
 * 包名称：com.newbeiyang.snbc.tablelib.ui.widget
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 15:46
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TableLineView extends LinearLayout {
    public TableLineView(Context context) {
        super(context);
        init(context);
    }

    public TableLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TableLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TableLineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    /**
     * 界面初始化
     *
     * @param context 父类引用
     */
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.table_lib_line_view, this, true);
    }
}
