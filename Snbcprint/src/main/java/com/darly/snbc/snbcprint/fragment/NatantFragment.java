package com.darly.snbc.snbcprint.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darly.snbc.snbcprint.R;
import com.darly.snbc.snbcprint.bean.FontRecover;

import java.util.List;

/**
 * 字体列表展示
 * 包名称：com.darly.snbc.snbcprint
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 15:39
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class NatantFragment extends SnbcPrintFragment {
    public View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view, container, false);
        return rootView;
    }

    @Override
    public void setFontData(List<FontRecover> fontData) {
        super.setFontData(fontData);
    }
}
