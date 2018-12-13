package com.newbeiyang.snbc.textlib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.log.SuperNatantLog;
import com.newbeiyang.snbc.textlib.common.observer.AbstractDesigner;
import com.newbeiyang.snbc.textlib.common.observer.DesignListener;
import com.newbeiyang.snbc.textlib.common.observer.InitCfg;
import com.newbeiyang.snbc.textlib.common.SuperNatantEnum;
import com.newbeiyang.snbc.textlib.ui.fragment.BaseTextFragment;
import com.newbeiyang.snbc.textlib.common.listener.TextEditSupernatantListener;
import com.newbeiyang.snbc.textlib.ui.widget.textedit.TextEditSupernatantView;

import java.util.List;

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
        SuperNatantLog.d(getClass().getSimpleName() + "管理类构造成功");
    }

    //第一步，初始化管理类
    public TextEditManager init(ViewGroup parent) {
        view = new TextEditSupernatantView(context);
        view.setVisibility(View.GONE);
        parent.addView(view);
        SuperNatantLog.d(getClass().getSimpleName() + "添加父组件成功");
        return this;
    }

    //第二步，初始化监听
    public TextEditManager setListener(TextEditSupernatantListener textEditSupernatantListener) {
        view.setTextEditSupernatantListener(textEditSupernatantListener);
        SuperNatantLog.d(getClass().getSimpleName() + "管理类设置监听成功");
        return this;
    }

    //第三步，初始化子菜单位置
    public TextEditManager setMenuPostion(SuperNatantEnum type) {
        view.setMenuPostion(type);
        show();
        SuperNatantLog.d(getClass().getSimpleName() + "管理类菜单位置成功");
        return this;
    }


    /**
     * 设置自定义布局，传递进入Fragment，进行自定义布局展示。但是回调方法还是走历史路径
     *
     * @param fragment   自定义的布局
     * @param natantEnum 布局类型
     */
    public TextEditManager setView(BaseTextFragment fragment, SuperNatantEnum natantEnum) {
        switch (natantEnum) {
            case BACKGROUND:
                //自定义背景界面
                view.setBackgroundFragment(fragment);
                SuperNatantLog.d(getClass().getSimpleName() + "自定义背景界面设置成功");
                break;
            case FONT:
                //自定义字体界面
                view.setFontFragment(fragment);
                SuperNatantLog.d(getClass().getSimpleName() + "自定义字体界面设置成功");
                break;
            case ALIGNMENT:
                //自定义对齐界面
                view.setAlignFragment(fragment);
                SuperNatantLog.d(getClass().getSimpleName() + "自定义对齐等界面设置成功");
                break;
            case SIZESPACING:
                //自定义样式界面
                view.setSpacingFragment(fragment);
                SuperNatantLog.d(getClass().getSimpleName() + "自定义其他界面设置成功");
                break;
        }
        view.switchFragment(fragment).commit();
        return this;
    }

    /**
     * 设置背景地址列表
     * 格式如下：
     * List<EditSupernatant> data = new ArrayList<EditSupernatant>();
     * data.add(new EditSupernatant(null,R.mipmap.icon_bubble_text,R.mipmap.icon_bubble_text));
     *
     * @param data 背景数据
     */
    public TextEditManager setBackgroundResouce(List<EditSupernatant> data) {
        view.setBgResouce(data);
        SuperNatantLog.d(getClass().getSimpleName() + "默认UI设置背景参数，设置成功");
        return this;
    }

    /**
     * 设置字体地址列表
     * 格式如下：
     * List<EditSupernatant> data = new ArrayList<EditSupernatant>();
     * data.add(new EditSupernatant("瘦金体",Typeface.createFromAsset(context.getAssets(), "fonts/shoujinti.ttf")));
     *
     * @param data 字体数据
     */
    public TextEditManager setFontResouce(List<EditSupernatant> data) {
        view.setFontResouce(data);
        SuperNatantLog.d(getClass().getSimpleName() + "默认UI设置字体参数，设置成功");
        return this;
    }

    /**
     * 设置居中样式列表
     *  new EditSupernatant(1.0f) 间距对象
     *  new EditSupernatant("六号", 18) 字号对象
     * @param fontSize 字号数据
     * @param spacing 间距数据
     */
    public TextEditManager setAlignResouce(List<EditSupernatant> fontSize, List<EditSupernatant> spacing) {
        view.setAlignResouce(fontSize,spacing);
        SuperNatantLog.d(getClass().getSimpleName() + "默认UI设置对齐等参数，设置成功");
        return this;
    }


    /**
     * 当用户选中一个新的控件，焦点进行变更时，焦点发生变更时，调用此方法进行库内部清理。
     */
    public void resetFocuss() {
        view.resetNatant();
    }

    public void show() {
        view.setVisibility(View.VISIBLE);
    }

    public void dismiss() {
        view.setVisibility(View.GONE);
    }


}
