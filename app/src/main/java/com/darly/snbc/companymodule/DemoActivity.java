package com.darly.snbc.companymodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.darly.snbc.base.BaseActivity;

/**
 * 包名称：com.darly.snbc.companymodule
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 11:00
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class DemoActivity extends BaseActivity implements View.OnClickListener {

    private Button id_demo_default;

    private Button id_demo_default_paramer;

    private Button id_demo_difine_view;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_demo);
        id_demo_default = findViewById(R.id.id_demo_default);
        id_demo_default_paramer = findViewById(R.id.id_demo_default_paramer);
        id_demo_difine_view = findViewById(R.id.id_demo_difine_view);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        id_demo_default.setOnClickListener(this);
        id_demo_default_paramer.setOnClickListener(this);
        id_demo_difine_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_demo_default:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.id_demo_default_paramer:
                startActivity(new Intent(this, ParamerActivity.class));
                break;
            case R.id.id_demo_difine_view:
                startActivity(new Intent(this, DifiViewActivity.class));
                break;
        }
    }
}
