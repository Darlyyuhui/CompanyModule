package com.newbeiyang.snbc.tablelib;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.listener.TableEditListener;
import com.newbeiyang.snbc.tablelib.ui.fragment.BaseTableFragment;
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

    private boolean debug;

    private String logName = "TableLib";

    public TableEditManager(boolean debug, Context context, String logName) {
        this.context = context;
        this.debug = debug;
        this.logName = logName;
    }

    //第一步，初始化管理类
    public TableEditManager init(ViewGroup parent) {
        if (parent == null){
            if (debug) {
                Log.d(logName, "参数传递为空");
            }
        }else {
            view = new TableEditView(context);
            view.setVisibility(View.GONE);
            parent.addView(view);
            if (debug) {
                Log.d(logName, "添加父组件成功");
            }
        }
        return this;
    }

    //第二步，初始化监听
    public TableEditManager setListener(TableEditListener tableEditListener) {
        if (tableEditListener == null){
            if (debug) {
                Log.d(logName, "参数传递为空");
            }
        }else {
            view.setTableEditListener(tableEditListener);
            if (debug) {
                Log.d(logName, "管理类设置监听成功");
            }
        }
        return this;
    }


    public TableEditManager setParamer(TableEditBean paramer) {
        if (paramer == null) {
            if (debug) {
                Log.d(logName, "参数传递为空");
            }
        } else {
            view.setParamer(paramer);
        }
        return this;
    }

    /**
     * 设置自定义布局，传递进入Fragment，进行自定义布局展示。但是回调方法还是走历史路径
     *
     * @param fragment   自定义的布局
     * @return   TableEditManager 返回管理类
     */
    public TableEditManager setView(BaseTableFragment fragment) {
        if (fragment == null) {
            if (debug) {
                Log.d(logName, "参数传递为空");
            }
        } else {
            //自定义背景界面
            view.setFragmentView(fragment);
            Log.d(getClass().getSimpleName() , "自定义背景界面设置成功");
        }
        return this;
    }


    public void show() {
        view.setVisibility(View.VISIBLE);
    }

    public void dismiss() {
        view.setVisibility(View.GONE);
    }

    public void resetFocuss(TableEditBean paramer) {
        if (paramer == null) {
            if (debug) {
                Log.d(logName, "参数传递为空");
            }
            return;
        }
        view.resetFocuss(paramer);
    }
}
