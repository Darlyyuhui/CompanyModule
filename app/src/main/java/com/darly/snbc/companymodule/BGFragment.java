package com.darly.snbc.companymodule;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.darly.snbc.snbcprint.bean.EditSupernatant;
import com.darly.snbc.snbcprint.fragment.BaseTextFragment;
import com.darly.snbc.snbcprint.listener.OnEditSupernatantListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义背景列表
 * 包名称：com.darly.snbc.companymodule
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 16:05
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class BGFragment extends BaseTextFragment {

    private OnEditSupernatantListener textBackgroundListener;

    private RecyclerView id_fragment_bg_rv;

    private TextBackgroundAdapter adapter;

    @Override
    protected int root() {
        return R.layout.fragment_bg_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_fragment_bg_rv = rootView.findViewById(R.id.id_fragment_bg_rv);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(GridLayoutManager.HORIZONTAL);
        id_fragment_bg_rv.setLayoutManager(manager);
    }

    @Override
    protected void loadData() {
        List<Integer> data = new ArrayList<>();
        data.add(R.mipmap.icon_bubble_text);
        data.add(R.mipmap.icon_bubble_normal);
        data.add(R.mipmap.icon_bubble_1);
        data.add(R.mipmap.icon_bubble_2);
        data.add(R.mipmap.icon_bubble_3);
        data.add(R.mipmap.icon_bubble_4);
        data.add(R.mipmap.icon_bubble_5);
        data.add(R.mipmap.icon_bubble_6);
        data.add(R.mipmap.icon_bubble_7);

        adapter = new TextBackgroundAdapter(data);
        id_fragment_bg_rv.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        adapter.setItemClickListener(new TextBackgroundAdapter.OnItemListener() {
            @Override
            public void onItemClick(int resId, int position) {
                EditSupernatant editSupernatant = new EditSupernatant();
                editSupernatant.setResId(resId);
                textBackgroundListener.getBackGround(editSupernatant);
            }
        });
    }

    @Override
    public void setBgListener(OnEditSupernatantListener listener) {
        super.setBgListener(listener);
        textBackgroundListener = listener;
    }



}
