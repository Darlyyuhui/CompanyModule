package com.darly.snbc.snbcprint;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.darly.snbc.observer.AbstractDesigner;
import com.darly.snbc.observer.DesignListener;
import com.darly.snbc.observer.InitCfg;
import com.darly.snbc.snbcprint.common.SuperNatantEnum;
import com.darly.snbc.snbcprint.fragment.BaseTextFragment;
import com.darly.snbc.snbcprint.listener.TextEditSupernatantListener;
import com.darly.snbc.widget.textedit.TextEditSupernatantView;

/**
 * 自定义控件布局管理类，在这里可以进行界面参数设置。
 * 包名称：com.darly.snbc.snbcprint
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 11:16
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextEditManager {


    private TextEditSupernatantView view;

    private Context context;

    public TextEditManager(boolean debug, Context context, String logName) {
        this.context = context;
        //在构造函数中进行观察者模式创建。观察者模式中的响应者。
        DesignListener design = new AbstractDesigner();
        design.addObserver(InitCfg.getInstance());
        design.init(debug, context, logName);
    }

    //第一步，初始化管理类
    public void init(ViewGroup parent) {
        view = new TextEditSupernatantView(context);
        view.setVisibility(View.GONE);
        parent.addView(view);
    }

    //第二步，初始化监听
    public void setListener(TextEditSupernatantListener textEditSupernatantListener) {
        view.setTextEditSupernatantListener(textEditSupernatantListener);
    }

    //第三步，初始化子菜单位置
    public void setMenuPostion(SuperNatantEnum type) {
        view.setRadioGroupPostion(type);
        show();
    }


    /**
     * 设置自定义布局，传递进入Fragment，进行自定义布局展示。但是回调方法还是走历史路径
     *
     * @param fragment   自定义的布局
     * @param natantEnum 布局类型
     */
    public void setView(BaseTextFragment fragment, SuperNatantEnum natantEnum) {
        switch (natantEnum) {
            case BACKGROUND:
                //自定义背景界面
                view.setBackgroundFragment(fragment);
                break;
            case FONT:
                //自定义字体界面
                view.setFontFragment(fragment);
                break;
            case ALIGNMENT:
                //自定义对齐界面
                view.setAlignFragment(fragment);
                break;
            case SIZESPACING:
                //自定义样式界面
                view.setSpacingFragment(fragment);
                break;
        }
    }


    public void show() {
        view.setVisibility(View.VISIBLE);
    }

    public void dismiss() {
        view.setVisibility(View.GONE);
    }
}
