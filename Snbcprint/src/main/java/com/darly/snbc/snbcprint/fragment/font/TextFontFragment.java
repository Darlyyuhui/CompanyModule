package com.darly.snbc.snbcprint.fragment.font;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.darly.snbc.snbcprint.R;
import com.darly.snbc.snbcprint.adapter.TextFontAdapter;
import com.darly.snbc.snbcprint.bean.FontRecover;
import com.darly.snbc.snbcprint.common.TypefaceCreat;
import com.darly.snbc.snbcprint.fragment.BaseTextFragment;
import com.darly.snbc.snbcprint.listener.OnItemClickListener;
import com.darly.snbc.snbcprint.listener.TextFontListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 文字字体界面
 * Created by maxiao on 2018/10/19.
 */
public class TextFontFragment extends BaseTextFragment {
    private RecyclerView fontRv;
    private TextFontAdapter textFontAdapter;
    private TextFontListener callBack;

    @Override
    protected int root() {
        return R.layout.fragment_text_font;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fontRv = rootView.findViewById(R.id.fontRv);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        fontRv.setLayoutManager(manager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
    }

    @Override
    protected void loadData() {
        final List<String> list = new ArrayList<>();
        list.add("方正卡通简体");
        list.add("楷体");
        list.add("瘦金体");
        list.add("隶书");
        list.add("华康娃娃体");
        list.add("方正正圆");

        List<FontRecover> fontData = new ArrayList<FontRecover>();
        for (String font : list) {
            FontRecover recover = new FontRecover();
            recover.setFontName(font);
            recover.setTypeface(TypefaceCreat.getTypeface(getActivity(), font));
            fontData.add(recover);
        }

        textFontAdapter = new TextFontAdapter(fontData);
        fontRv.setAdapter(textFontAdapter);

        textFontAdapter.setItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(Typeface typeface, int position) {
                callBack.getTypeface(typeface);
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setFontData(List<FontRecover> fontData) {
        textFontAdapter.setmDatas(fontData);
    }

    @Override
    public void setFontListener(TextFontListener callBack) {
        this.callBack = callBack;
    }
}
