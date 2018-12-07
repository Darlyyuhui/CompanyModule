package com.darly.snbc.snbcprint.font;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darly.snbc.snbcprint.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxiao on 2018/10/19.
 */
public class TextFontFragment extends Fragment {
    private RecyclerView fontRv;
    private TextFontAdapter textFontAdapter;
    private TextFontCallBack callBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_text_font, null);
        fontRv = v.findViewById(R.id.fontRv);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        fontRv.setLayoutManager(manager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        final List<String> list = new ArrayList<>();
        list.add("方正卡通简体");
        list.add("楷体");
        list.add("瘦金体");
        list.add("隶书");
        list.add("华康娃娃体");
        list.add("方正正圆");
        textFontAdapter = new TextFontAdapter(list);
        fontRv.setAdapter(textFontAdapter);

        textFontAdapter.setItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(TextView view, int position) {
                callBack.getTypeface(new TypefaceCreat().getTypeface(getContext(), list.get(position)));
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
        return v;
    }

    public void setFont(TextFontCallBack callBack) {
        this.callBack = callBack;
    }

}
