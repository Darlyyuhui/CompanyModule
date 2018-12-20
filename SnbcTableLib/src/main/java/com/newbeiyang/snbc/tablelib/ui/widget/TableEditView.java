package com.newbeiyang.snbc.tablelib.ui.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.newbeiyang.snbc.tablelib.R;
import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.TableEditEnum;
import com.newbeiyang.snbc.tablelib.common.listener.OnTableSelectListener;
import com.newbeiyang.snbc.tablelib.common.listener.TableEditListener;
import com.newbeiyang.snbc.tablelib.ui.fragment.BaseTableFragment;
import com.newbeiyang.snbc.tablelib.ui.fragment.TableLibFragment;


/**
 * Table主要展示类，根据Manager中传递的参数进行展示效果
 * 包名称：com.newbeiyang.snbc.tablelib.ui
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 11:12
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TableEditView extends RelativeLayout implements OnTableSelectListener {

    private TableEditListener tableEditListener;

    private FragmentManager fm;

    private BaseTableFragment tableFragment;

    private BaseTableFragment currentFragment;

    private RelativeLayout table_lib_relative;

    public TableEditView(Context context) {
        super(context);
        init(context);
    }


    public TableEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TableEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TableEditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        inflater.inflate(R.layout.table_lib_view, this, true);

        table_lib_relative = findViewById(R.id.table_lib_relative);

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        table_lib_relative.setLayoutParams(new RelativeLayout.LayoutParams(width, width / 2));
        fm = ((FragmentActivity) context).getSupportFragmentManager();
        tableFragment = new TableLibFragment();

        fm.beginTransaction().add(R.id.table_lib_frame, tableFragment).commit();

        tableFragment.setOnTableSelectListener(this);
    }


    public void setTableEditListener(TableEditListener tableEditListener) {
        this.tableEditListener = tableEditListener;
    }


    /**
     * 优化切换Fragment方案。
     *
     * @param fragment 需要展示的Fragment
     * @return FragmentTransaction
     */
    public FragmentTransaction switchFragment(BaseTableFragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                ft.hide(currentFragment);
            }
            ft.add(R.id.table_lib_frame, fragment);
        } else {
            ft.hide(currentFragment).show(fragment);
        }
        Log.d(getClass().getSimpleName(), currentFragment + "进行界面选择切换" + fragment);
        currentFragment = fragment;
        return ft;
    }


    public void setParamer(TableEditBean paramer) {
        tableFragment.setDefaultCount(paramer.getCloumn(), paramer.getRow());
    }

    @Override
    public void onSelect(TableEditBean tableEditBean, TableEditEnum direction) {
        if (tableEditListener != null) {
            tableEditListener.onTableCreate(tableEditBean,direction);
        }
    }
}
