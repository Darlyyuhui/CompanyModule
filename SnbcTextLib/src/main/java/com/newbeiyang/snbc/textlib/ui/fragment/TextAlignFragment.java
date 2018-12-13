package com.newbeiyang.snbc.textlib.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.newbeiyang.snbc.textlib.R;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.bean.SuperNatantMenu;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;
import com.newbeiyang.snbc.textlib.common.log.SuperNatantLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 文字对齐界面
 * 包名称：com.darly.snbc.snbcprint.fragment.align
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 13:56
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextAlignFragment extends BaseTextFragment implements TabLayout.OnTabSelectedListener,CompoundButton.OnCheckedChangeListener {

    private OnEditSupernatantListener textAlignListener;

    private TabLayout id_supernatant_align_tab;

    private TabLayout id_supernatant_space_size_tab;

    private TabLayout id_supernatant_space_lib_tab;

    private CheckBox id_supernatant_checkbox_cu;
    private CheckBox id_supernatant_checkbox_itle;
    private CheckBox id_supernatant_checkbox_uline;


    private ImageView id_supernatant_iv_left;
    private ImageView id_supernatant_iv_right;

    private EditSupernatant supernatant = new EditSupernatant();


    @Override
    protected int root() {
        return R.layout.fragment_text_align;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_supernatant_align_tab = rootView.findViewById(R.id.id_supernatant_align_tab);
        id_supernatant_space_size_tab = rootView.findViewById(R.id.id_supernatant_space_size_tab);
        id_supernatant_space_lib_tab = rootView.findViewById(R.id.id_supernatant_space_lib_tab);

        id_supernatant_checkbox_cu = rootView.findViewById(R.id.id_supernatant_checkbox_cu);
        id_supernatant_checkbox_itle = rootView.findViewById(R.id.id_supernatant_checkbox_itle);
        id_supernatant_checkbox_uline = rootView.findViewById(R.id.id_supernatant_checkbox_uline);
        id_supernatant_iv_left = rootView.findViewById(R.id.id_supernatant_iv_left);
        id_supernatant_iv_right = rootView.findViewById(R.id.id_supernatant_iv_right);
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
        List<EditSupernatant> menus = new ArrayList<EditSupernatant>();
        Collections.addAll(menus, new EditSupernatant("七号", 12)
                , new EditSupernatant("六号", 18)
                , new EditSupernatant("五号", 22)
                , new EditSupernatant("四号", 26)
                , new EditSupernatant("三号", 30)
                , new EditSupernatant("二号", 34)
                , new EditSupernatant("一号", 38)
                , new EditSupernatant("小初", 40));

        for (EditSupernatant size : menus) {
            TabLayout.Tab tab = id_supernatant_space_size_tab.newTab();
            tab.setText(size.getSizeName());
            tab.setTag(size);
            id_supernatant_space_size_tab.addTab(tab);
        }

        List<EditSupernatant> libs = new ArrayList<EditSupernatant>();
        Collections.addAll(libs, new EditSupernatant(0.5f)
                , new EditSupernatant(1.0f)
                , new EditSupernatant(1.5f)
                , new EditSupernatant(2.0f)
                , new EditSupernatant(2.5f)
                , new EditSupernatant(3.0f)
                , new EditSupernatant(3.5f)
                , new EditSupernatant(4.0f)
                , new EditSupernatant(5.0f)
                , new EditSupernatant(6.0f));
        for (EditSupernatant lib : libs) {
            TabLayout.Tab tab = id_supernatant_space_lib_tab.newTab();
            tab.setText(String.valueOf(lib.getLineSpacingMultiplier()));
            tab.setTag("LIB");
            id_supernatant_space_lib_tab.addTab(tab);
        }
        SuperNatantLog.d(getClass().getSimpleName() + "界面UI初始化完成");
    }

    @Override
    protected void initListener() {
        id_supernatant_align_tab.addOnTabSelectedListener(this);
        id_supernatant_space_size_tab.addOnTabSelectedListener(this);
        id_supernatant_space_lib_tab.addOnTabSelectedListener(this);

        id_supernatant_checkbox_cu.setOnCheckedChangeListener(this);
        id_supernatant_checkbox_itle.setOnCheckedChangeListener(this);
        id_supernatant_checkbox_uline.setOnCheckedChangeListener(this);
        id_supernatant_iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supernatant.setMove(-1);
                textAlignListener.getAlign(supernatant);
            }
        });
        id_supernatant_iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supernatant.setMove(1);
                textAlignListener.getAlign(supernatant);
            }
        });
    }


    @Override
    public void updateAlign(List<EditSupernatant> fontSize, List<EditSupernatant> spacing) {
        super.updateAlign(fontSize, spacing);
        id_supernatant_space_size_tab.removeAllTabs();
        for (EditSupernatant size : fontSize) {
            TabLayout.Tab tab = id_supernatant_space_size_tab.newTab();
            tab.setText(size.getSizeName());
            tab.setTag(size);
            id_supernatant_space_size_tab.addTab(tab);
        }
        id_supernatant_space_lib_tab.removeAllTabs();
        for (EditSupernatant lib : spacing) {
            TabLayout.Tab tab = id_supernatant_space_lib_tab.newTab();
            tab.setText(String.valueOf(lib.getLineSpacingMultiplier()));
            tab.setTag("LIB");
            id_supernatant_space_lib_tab.addTab(tab);
        }

    }

    @Override
    public void setAlignListener(OnEditSupernatantListener listener) {
        super.setAlignListener(listener);
        textAlignListener = listener;
    }

    @Override
    public void resetNatant() {
        if (supernatant!=null) {
            supernatant.reset();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        SuperNatantLog.d(getClass().getSimpleName() + "点击Tab");
        Object ob = tab.getTag();
        if (ob instanceof EditSupernatant) {
            //字号
            EditSupernatant si = (EditSupernatant) ob;
            supernatant.setSize(si.getSize());
        } else {
            if ("LEFT".equals(ob)) {
                //点击左对齐
                supernatant.setGravy(1);
            } else if ("MIDDLE".equals(ob)) {
                //点击居中
                supernatant.setGravy(2);
            } else if ("RIGHT".equals(ob)) {
                //点击右对齐
                supernatant.setGravy(3);
            } else if ("LIB".equals(ob)) {
                //间距
                supernatant.setLineSpacingMultiplier(Float.parseFloat(tab.getText().toString()));
            }
        }
        SuperNatantLog.d(getClass().getSimpleName() + "启动回调方案");
        supernatant.setMove(0);
        textAlignListener.getAlign(supernatant);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == id_supernatant_checkbox_cu.getId()){
            if (isChecked){
                supernatant.setBold(true);
            }else {
                supernatant.setBold(false);
            }
        }
        if (buttonView.getId() == id_supernatant_checkbox_itle.getId()){
            if (isChecked){
                supernatant.setItaic(true);
            }else {
                supernatant.setItaic(false);
            }
        }
        if (buttonView.getId() == id_supernatant_checkbox_uline.getId()){
            if (isChecked){
                supernatant.setUnderLine(true);
            }else {
                supernatant.setUnderLine(false);
            }
        }
        SuperNatantLog.d(getClass().getSimpleName() + "启动回调方案");
        supernatant.setMove(0);
        textAlignListener.getAlign(supernatant);
    }
}
