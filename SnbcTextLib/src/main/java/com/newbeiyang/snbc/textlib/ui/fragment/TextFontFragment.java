package com.newbeiyang.snbc.textlib.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.newbeiyang.snbc.textlib.R;
import com.newbeiyang.snbc.textlib.adapter.TextFontAdapter;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.TypefaceCreat;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;
import com.newbeiyang.snbc.textlib.common.log.SuperNatantLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 文字字体界面
 * Created by maxiao on 2018/10/19.
 */
public class TextFontFragment extends BaseTextFragment {
    private RecyclerView fontRv;
    private TextFontAdapter textFontAdapter;
    private OnEditSupernatantListener callBack;
    private String [] fontList = new String[]{"方正卡通简体","瘦金体","隶书","华康娃娃体"};

    @Override
    protected int root() {
        return R.layout.fragment_text_font;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fontRv = rootView.findViewById(R.id.fontRv);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        fontRv.setLayoutManager(manager);
    }

    @Override
    protected void loadData() {
        List<EditSupernatant> fontData = new ArrayList<EditSupernatant>();
        for (String font : fontList) {
            EditSupernatant recover = new EditSupernatant();
            recover.setFontName(font);
            recover.setTypeface(TypefaceCreat.getTypeface(getActivity(), font));
            fontData.add(recover);
        }

        textFontAdapter = new TextFontAdapter(fontData);
        fontRv.setAdapter(textFontAdapter);

        textFontAdapter.setItemClickListener(new TextFontAdapter.OnShowItemClickListener() {

            @Override
            public void onItemClick(EditSupernatant recover , int position) {
                SuperNatantLog.d(getClass().getSimpleName() + "启动回调方案");
                callBack.getTypeface(recover);
            }
        });
        SuperNatantLog.d(getClass().getSimpleName() + "界面UI初始化完成");
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void updateFont(List<EditSupernatant> datas) {
        super.updateFont(datas);
        textFontAdapter.setDatas(datas);
    }

    @Override
    public void setFontListener(OnEditSupernatantListener callBack) {
        this.callBack = callBack;
    }

    @Override
    public void resetNatant() {

    }
}
