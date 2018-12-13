package com.newbeiyang.snbc.textlib.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.newbeiyang.snbc.textlib.R;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;

/**
 * 文字对齐界面
 * 包名称：com.darly.snbc.snbcprint.fragment.align
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 13:56
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextAlignFragment extends BaseTextFragment implements TabLayout.OnTabSelectedListener {

    private OnEditSupernatantListener textAlignListener;

    private TabLayout id_supernatant_align_tab;

    @Override
    protected int root() {
        return R.layout.fragment_text_align;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_supernatant_align_tab = rootView.findViewById(R.id.id_supernatant_align_tab);
    }

    @Override
    protected void loadData() {
        TabLayout.Tab left = id_supernatant_align_tab.newTab();
        left.setTag("LEFT");
        left.setIcon(R.drawable.dr_natant_text_left_select);
        id_supernatant_align_tab.addTab(left);

        TabLayout.Tab middle = id_supernatant_align_tab.newTab();
        middle.setTag("MIDDLE");
        middle.setIcon(R.drawable.dr_natant_text_middle_select);
        id_supernatant_align_tab.addTab(middle);

        TabLayout.Tab right = id_supernatant_align_tab.newTab();
        right.setTag("RIGHT");
        right.setIcon(R.drawable.dr_natant_text_right_select);
        id_supernatant_align_tab.addTab(right);
    }

    @Override
    protected void initListener() {
        id_supernatant_align_tab.addOnTabSelectedListener(this);
    }


    @Override
    public void setAlignListener(OnEditSupernatantListener listener) {
        super.setAlignListener(listener);
        textAlignListener = listener;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if ("LEFT".equals(tab.getTag())){
            //点击左对齐
        }else if ("MIDDLE".equals(tab.getTag())){
            //点击居中
        }else if ("RIGHT".equals(tab.getTag())){
            //点击右对齐
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
