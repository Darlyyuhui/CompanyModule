package com.darly.snbc.snbcprint.fragment.font;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.darly.snbc.snbcprint.R;
import com.darly.snbc.snbcprint.adapter.TextFontAdapter;
import com.darly.snbc.snbcprint.bean.EditSupernatant;
import com.darly.snbc.snbcprint.common.TypefaceCreat;
import com.darly.snbc.snbcprint.fragment.BaseTextFragment;
import com.darly.snbc.snbcprint.listener.OnEditSupernatantListener;

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
    private String [] fontList = new String[]{"方正卡通简体","楷体","瘦金体","隶书","华康娃娃体","方正正圆"};

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
                callBack.getTypeface(recover);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setFontListener(OnEditSupernatantListener callBack) {
        this.callBack = callBack;
    }
}
