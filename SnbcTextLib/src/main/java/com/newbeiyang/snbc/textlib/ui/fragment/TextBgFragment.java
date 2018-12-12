package com.newbeiyang.snbc.textlib.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.newbeiyang.snbc.textlib.R;
import com.newbeiyang.snbc.textlib.adapter.TextBackgroundAdapter;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 文字背景界面
 * 包名称：com.darly.snbc.snbcprint.fragment.bg
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 13:40
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextBgFragment extends BaseTextFragment {

    private OnEditSupernatantListener textBackgroundListener;

    private RecyclerView id_fragment_bg_rv;

    private TextBackgroundAdapter adapter;

    @Override
    protected int root() {
        return R.layout.fragment_text_bg;
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
        List<EditSupernatant> data = new ArrayList<EditSupernatant>();
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_text,R.mipmap.icon_bubble_text));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_selected,R.mipmap.icon_bubble_normal));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_1_preview,R.mipmap.icon_bubble_1));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_2_preview,R.mipmap.icon_bubble_2));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_3_preview,R.mipmap.icon_bubble_3));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_4_preview,R.mipmap.icon_bubble_4));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_5_preview,R.mipmap.icon_bubble_5));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_6_preview,R.mipmap.icon_bubble_6));
        data.add(new EditSupernatant(null,R.mipmap.icon_bubble_7_preview,R.mipmap.icon_bubble_7));

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
    @Override
    public void updateBG(List<EditSupernatant> datas) {
        super.updateBG(datas);
        adapter.setDatas(datas);
    }
}
