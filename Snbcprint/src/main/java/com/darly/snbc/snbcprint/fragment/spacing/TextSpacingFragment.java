package com.darly.snbc.snbcprint.fragment.spacing;

import android.os.Bundle;
import android.widget.TextView;

import com.darly.snbc.snbcprint.R;
import com.darly.snbc.snbcprint.fragment.BaseTextFragment;
import com.darly.snbc.snbcprint.listener.OnEditSupernatantListener;

/**
 * 文字样式界面
 * 包名称：com.darly.snbc.snbcprint.fragment.spacing
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 13:57
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextSpacingFragment extends BaseTextFragment {

    private OnEditSupernatantListener textSpacingListener;

    private TextView id_fragment_tv;

    @Override
    protected int root() {
        return R.layout.fragment_text;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id_fragment_tv = rootView.findViewById(R.id.id_fragment_tv);
        id_fragment_tv.setText(getClass().getSimpleName());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setSpacingListener(OnEditSupernatantListener listener) {
        super.setSpacingListener(listener);
        textSpacingListener  = listener;
    }
}
