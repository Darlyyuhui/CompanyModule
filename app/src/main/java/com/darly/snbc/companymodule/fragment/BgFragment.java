package com.darly.snbc.companymodule.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.darly.snbc.companymodule.R;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;
import com.newbeiyang.snbc.textlib.ui.fragment.BaseTextFragment;

/**
 * 自定义背景布局
 * 包名称：com.darly.snbc.companymodule.fragment
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 14:14
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class BgFragment extends BaseTextFragment implements View.OnClickListener {

    OnEditSupernatantListener onEditSupernatantListener;

    ImageView id_fragment_iv1;
    ImageView id_fragment_iv2;
    ImageView id_fragment_iv3;
    ImageView id_fragment_iv4;
    ImageView id_fragment_iv5;
    ImageView id_fragment_iv6;
    ImageView id_fragment_iv7;

    @Override
    protected int root() {
        return R.layout.fragment_bg;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_fragment_iv1 = rootView.findViewById(R.id.id_fragment_iv1);
        id_fragment_iv2 = rootView.findViewById(R.id.id_fragment_iv2);
        id_fragment_iv3 = rootView.findViewById(R.id.id_fragment_iv3);
        id_fragment_iv4 = rootView.findViewById(R.id.id_fragment_iv4);
        id_fragment_iv5 = rootView.findViewById(R.id.id_fragment_iv5);
        id_fragment_iv6 = rootView.findViewById(R.id.id_fragment_iv6);
        id_fragment_iv7 = rootView.findViewById(R.id.id_fragment_iv7);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        id_fragment_iv1.setOnClickListener(this);
        id_fragment_iv2.setOnClickListener(this);
        id_fragment_iv3.setOnClickListener(this);
        id_fragment_iv4.setOnClickListener(this);
        id_fragment_iv5.setOnClickListener(this);
        id_fragment_iv6.setOnClickListener(this);
        id_fragment_iv7.setOnClickListener(this);
    }

    @Override
    public void setBgListener(OnEditSupernatantListener listener) {
        super.setBgListener(listener);
        onEditSupernatantListener = listener;
    }

    @Override
    public void resetNatant() {

    }

    @Override
    public void onClick(View v) {
        EditSupernatant supernatant = new EditSupernatant();
        switch (v.getId()) {
            case R.id.id_fragment_iv1:
                supernatant.setResId(R.mipmap.icon_bubble_1_preview);
                break;
            case R.id.id_fragment_iv2:
                supernatant.setResId(R.mipmap.icon_bubble_2_preview);
                break;
            case R.id.id_fragment_iv3:
                supernatant.setResId(R.mipmap.icon_bubble_3_preview);
                break;
            case R.id.id_fragment_iv4:
                supernatant.setResId(R.mipmap.icon_bubble_4_preview);
                break;
            case R.id.id_fragment_iv5:
                supernatant.setResId(R.mipmap.icon_bubble_5_preview);
                break;
            case R.id.id_fragment_iv6:
                supernatant.setResId(R.mipmap.icon_bubble_6_preview);
                break;
            case R.id.id_fragment_iv7:
                supernatant.setResId(R.mipmap.icon_bubble_7_preview);
                break;
        }
        onEditSupernatantListener.getBackGround(supernatant);
    }
}
