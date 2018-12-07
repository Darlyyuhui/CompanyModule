package com.darly.snbc.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;


/**
 * @author zhangyh2 BaseActivity $ 下午2:33:01 TODO
 */
public abstract class BaseActivity extends FragmentActivity {

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @TargetApi(19)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        initParamer();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initGlobalVariable();
        initView(savedInstanceState);
        loadData();
        initListener();
    }
    /**在设置页面信息前，设置参数
     * @author zhangyh2 BaseActivity.java TODO
     */
    protected  void initParamer(){

    }
    /**
     * 下午2:36:27
     *
     * @author zhangyh2 BaseActivity.java TODO
     * 初始化全局的一些变量。而且做好的静态变量。每个Activity里面的变量由自己来进行定义。
     */
    private void initGlobalVariable() {
        // TODO Auto-generated method stub
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
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
     * @author zhangyh2 BaseFragment.java TODO 初始化监听事件
     */
    protected abstract void initListener();


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    public FragmentManager fragmentManager;
    public String TAGSS = "";
    /*
     * 在fragment的管理类中，我们要实现这部操作，而他的主要作用是，当D这个activity回传数据到
     * 这里碎片管理器下面的fragnment中时，往往会经过这个管理器中的onActivityResult的方法。
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* 在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment */
        Fragment f = fragmentManager.findFragmentByTag(TAGSS);
        /* 然后在碎片中调用重写的onActivityResult方法 */
        if (f == null) {
            onActivityResult(requestCode, resultCode, data);
        } else {
            f.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }





}
