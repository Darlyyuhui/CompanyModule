package com.darly.snbc.companymodule.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.darly.snbc.companymodule.R;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;
import com.newbeiyang.snbc.textlib.ui.fragment.BaseTextFragment;

/**
 * 自定对其体布局
 * 包名称：com.darly.snbc.companymodule.fragment
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 14:16
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class AlignFragment extends BaseTextFragment implements View.OnClickListener {

    OnEditSupernatantListener onEditSupernatantListener;

    Button id_fragment_size1;
    Button id_fragment_size2;
    Button id_fragment_size3;
    Button id_fragment_size4;
    Button id_fragment_size5;

    @Override
    protected int root() {
        return R.layout.fragment_align;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_fragment_size1 = rootView.findViewById(R.id.id_fragment_size1);
        id_fragment_size2 = rootView.findViewById(R.id.id_fragment_size2);
        id_fragment_size3 = rootView.findViewById(R.id.id_fragment_size3);
        id_fragment_size4 = rootView.findViewById(R.id.id_fragment_size4);
        id_fragment_size5 = rootView.findViewById(R.id.id_fragment_size5);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        id_fragment_size1.setOnClickListener(this);
        id_fragment_size2.setOnClickListener(this);
        id_fragment_size3.setOnClickListener(this);
        id_fragment_size4.setOnClickListener(this);
        id_fragment_size5.setOnClickListener(this);
    }

    @Override
    public void setAlignListener(OnEditSupernatantListener listener) {
        super.setAlignListener(listener);
        onEditSupernatantListener = listener;
    }

    @Override
    public void resetNatant() {

    }

    @Override
    public void onClick(View v) {
        EditSupernatant supernatant = new EditSupernatant();
        switch (v.getId()) {
            case R.id.id_fragment_size1:
                supernatant.setSize(12);
                break;
            case R.id.id_fragment_size2:
                supernatant.setSize(18);
                break;
            case R.id.id_fragment_size3:
                supernatant.setSize(24);
                break;
            case R.id.id_fragment_size4:
                supernatant.setSize(30);
                break;
            case R.id.id_fragment_size5:
                supernatant.setSize(99);
                break;
        }
        onEditSupernatantListener.getAlign(supernatant);
    }
}
