package com.newbeiyang.snbc.tablelib;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.newbeiyang.snbc.tablelib.common.listener.TableEditListener;
import com.newbeiyang.snbc.tablelib.ui.widget.TableEditView;

/**
 * 自定义控件布局管理类，在这里可以进行界面参数设置。
 * 包名称：com.newbeiyang.snbc.tablelib
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 10:43
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TableEditManager {

    private TableEditView view;

    private Context context;

    public TableEditManager(boolean debug, Context context, String logName) {
        this.context = context;
    }

    //第一步，初始化管理类
    public TableEditManager init(ViewGroup parent) {
        view = new TableEditView(context);
        view.setVisibility(View.GONE);
        parent.addView(view);
        Log.d(getClass().getSimpleName(), "添加父组件成功");
        return this;
    }

    //第二步，初始化监听
    public TableEditManager setListener(TableEditListener tableEditListener) {
        view.setTableEditListener(tableEditListener);
        Log.d(getClass().getSimpleName(), "管理类设置监听成功");
        return this;
    }

    public void show(){
        view.setVisibility(View.VISIBLE);
    }

    public void dismiss(){
        view.setVisibility(View.GONE);
    }

}
