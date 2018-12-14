package com.darly.snbc.companymodule.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.darly.snbc.companymodule.R;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;
import com.newbeiyang.snbc.textlib.ui.fragment.BaseTextFragment;

/**
 * 自定义字体布局
 * 包名称：com.darly.snbc.companymodule.fragment
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 14:16
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class FontFragment extends BaseTextFragment implements View.OnClickListener{

    OnEditSupernatantListener onEditSupernatantListener;

    Button id_fragment_bt1;
    Button id_fragment_bt2;
    Button id_fragment_bt3;
    Button id_fragment_bt4;
    Button id_fragment_bt5;
    Button id_fragment_bt6;
    Button id_fragment_bt7;
    Button id_fragment_bt8;
    Button id_fragment_bt9;


    @Override
    protected int root() {
        return R.layout.fragment_font;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_fragment_bt1 = rootView.findViewById(R.id.id_fragment_bt1);
        id_fragment_bt2 = rootView.findViewById(R.id.id_fragment_bt2);
        id_fragment_bt3 = rootView.findViewById(R.id.id_fragment_bt3);
        id_fragment_bt4 = rootView.findViewById(R.id.id_fragment_bt4);
        id_fragment_bt5 = rootView.findViewById(R.id.id_fragment_bt5);
        id_fragment_bt6 = rootView.findViewById(R.id.id_fragment_bt6);
        id_fragment_bt7 = rootView.findViewById(R.id.id_fragment_bt7);
        id_fragment_bt8 = rootView.findViewById(R.id.id_fragment_bt8);
        id_fragment_bt9 = rootView.findViewById(R.id.id_fragment_bt9);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        id_fragment_bt1.setOnClickListener(this);
        id_fragment_bt2.setOnClickListener(this);
        id_fragment_bt3.setOnClickListener(this);
        id_fragment_bt4.setOnClickListener(this);
        id_fragment_bt5.setOnClickListener(this);
        id_fragment_bt6.setOnClickListener(this);
        id_fragment_bt7.setOnClickListener(this);
        id_fragment_bt8.setOnClickListener(this);
        id_fragment_bt9.setOnClickListener(this);
    }

    @Override
    public void setFontListener(OnEditSupernatantListener listener) {
        super.setFontListener(listener);
        onEditSupernatantListener = listener;
    }

    @Override
    public void resetNatant() {

    }

    @Override
    public void onClick(View v) {
        EditSupernatant supernatant = new EditSupernatant();
        switch (v.getId()){
            case R.id.id_fragment_bt1:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_1.ttf"));
                break;
            case R.id.id_fragment_bt2:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_2.ttf"));
                break;
            case R.id.id_fragment_bt3:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_3.ttf"));
                break;
            case R.id.id_fragment_bt4:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_4.ttf"));
                break;
            case R.id.id_fragment_bt5:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_5.ttf"));
                break;
            case R.id.id_fragment_bt6:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_6.ttf"));
                break;
            case R.id.id_fragment_bt7:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_7.ttf"));
                break;
            case R.id.id_fragment_bt8:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_8.ttf"));
                break;
            case R.id.id_fragment_bt9:
                supernatant.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"font_9.ttf"));
                break;
        }
        onEditSupernatantListener.getTypeface(supernatant);
    }
}
