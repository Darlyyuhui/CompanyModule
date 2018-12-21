package com.newbeiyang.snbc.tablelib.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.listener.OnTableSelectListener;

/**
 * 基础父类Fragment
 * 包名称：com.newbeiyang.snbc.tablelib.ui
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 11:21
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public abstract class BaseTableFragment extends Fragment {

    public View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(root(), container, false);
        return rootView;
    }

    protected abstract int root();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        initGlobe();
        initView(savedInstanceState);

        loadData();

        initListener();

    }

    /**
     * 下午4:06:44
     *
     * @author zhangyh2 TODO
     */
    private void initGlobe() {
        // TODO Auto-generated method stub
    }

    /**
     * @param savedInstanceState 下午2:34:08
     * @author zhangyh2 BaseActivity.java TODO 初始化控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 下午2:34:10
     *
     * @author zhangyh2 BaseActivity.java TODO 加载数据
     */
    protected abstract void loadData();

    /**
     * 下午2:42:02
     *
     * @author zhangyh2 BaseFragment.java TODO 初始化坚挺事件
     */
    protected abstract void initListener();

    public abstract void setDefaultCount(int column, int row);

    public abstract void resetFocuss(TableEditBean paramer);

    public abstract void setOnTableSelectListener(OnTableSelectListener listener);

}
