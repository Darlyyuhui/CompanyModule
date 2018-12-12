package com.darly.snbc.widget.text;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darly.snbc.companymodule.R;
import com.newbeiyang.snbc.textlib.common.observer.SupernatantCfg;


/**
 * 定义一个包裹文字的输入框
 * 包名称：com.darly.snbc.widget.textedit
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 17:28
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextEditBackgroundView extends RelativeLayout {

    private RelativeLayout id_bg_relative;

    private TextView id_bg_edit;

    public TextEditBackgroundView(Context context) {
        super(context);
        init(context);
    }

    public TextEditBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextEditBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextEditBackgroundView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    //初始化方法，获取屏幕信息
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.text_bg_view, this, true);
        id_bg_relative = findViewById(R.id.id_bg_relative);
        id_bg_edit = findViewById(R.id.id_bg_edit);
        initListener();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(SupernatantCfg.getWidth() / 4, SupernatantCfg.getWidth() / 4);
    }

    private void initListener() {
    }

    public void setBackgroundView(int resId) {
        id_bg_relative.setBackgroundResource(resId);
    }


    public TextView getEdit() {
        return id_bg_edit;
    }
}
