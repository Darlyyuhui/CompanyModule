package com.newbeiyang.snbc.tablelib.ui.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newbeiyang.snbc.tablelib.R;
import com.newbeiyang.snbc.tablelib.common.listener.AddRemoveMultipleListener;

/**
 * 加减按钮，以及输入输出
 * 包名称：com.newbeiyang.snbc.tablelib.ui.widget
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 13:41
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class AddRemoveMultipleView extends LinearLayout {

    private ImageView table_lib_reduce;

    private ImageView table_lib_add;

    private TextView table_lib_result;

    private int min = 1;

    private int max = 8;

    private int cout = min;


    private AddRemoveMultipleListener listener;

    public AddRemoveMultipleView(Context context) {
        super(context);
        init(context);
    }

    public AddRemoveMultipleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddRemoveMultipleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AddRemoveMultipleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        inflater.inflate(R.layout.table_lib_multiple_view, this, true);
        table_lib_reduce = findViewById(R.id.table_lib_reduce);
        table_lib_add = findViewById(R.id.table_lib_add);
        table_lib_result = findViewById(R.id.table_lib_result);
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        table_lib_result.setLayoutParams((new LinearLayout.LayoutParams(width / 6, ViewGroup.LayoutParams.WRAP_CONTENT)));
        table_lib_result.setText(String.valueOf(cout));
        table_lib_reduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cout <= min) {
                    table_lib_reduce.setClickable(false);
                    return;
                } else {
                    table_lib_add.setClickable(true);
                    cout--;
                    table_lib_result.setText(String.valueOf(cout));
                    if (listener !=null) {
                        listener.onMinus();
                    }
                }
            }
        });
        table_lib_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cout >= max) {
                    table_lib_add.setClickable(false);
                    return;
                } else {
                    table_lib_reduce.setClickable(true);
                    cout++;
                    table_lib_result.setText(String.valueOf(cout));
                    if (listener !=null) {
                        listener.onAdd();
                    }
                }
            }
        });
    }




    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getResult() {
        return cout;
    }

    public void setDefaultCount(int cout){
        this.cout = cout;
        table_lib_result.setText(String.valueOf(cout));
        if (cout >= max) {
            table_lib_add.setClickable(false);
        }else {
            table_lib_add.setClickable(true);
        }
        if (cout <= min) {
            table_lib_reduce.setClickable(false);
        } else {
            table_lib_reduce.setClickable(true);
        }
    }



    public void setAddRemoveMultipleListener(AddRemoveMultipleListener addRemoveMultipleListener){
        listener = addRemoveMultipleListener;
    }
}
