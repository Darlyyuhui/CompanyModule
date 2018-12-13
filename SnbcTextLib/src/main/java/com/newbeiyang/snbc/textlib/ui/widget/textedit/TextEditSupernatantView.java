package com.newbeiyang.snbc.textlib.ui.widget.textedit;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.newbeiyang.snbc.textlib.R;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.bean.SuperNatantMenu;
import com.newbeiyang.snbc.textlib.common.SuperNatantEnum;
import com.newbeiyang.snbc.textlib.common.listener.OnEditSupernatantListener;
import com.newbeiyang.snbc.textlib.common.listener.TextEditSupernatantListener;
import com.newbeiyang.snbc.textlib.common.log.SuperNatantLog;
import com.newbeiyang.snbc.textlib.common.observer.SupernatantCfg;
import com.newbeiyang.snbc.textlib.ui.fragment.BaseTextFragment;
import com.newbeiyang.snbc.textlib.ui.fragment.TextAlignFragment;
import com.newbeiyang.snbc.textlib.ui.fragment.TextBgFragment;
import com.newbeiyang.snbc.textlib.ui.fragment.TextFontFragment;
import com.newbeiyang.snbc.textlib.ui.widget.verticaltablayout.VerticalTabLayout;
import com.newbeiyang.snbc.textlib.ui.widget.verticaltablayout.adapter.TabAdapter;
import com.newbeiyang.snbc.textlib.ui.widget.verticaltablayout.widget.TabView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 文本编辑浮层主类
 * 包名称：com.darly.snbc.widget.textedit
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 9:38
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextEditSupernatantView extends RelativeLayout implements OnEditSupernatantListener, OnTabSelectedListener {
    private int width;
    private RelativeLayout id_supernatant_relative;

    private FrameLayout id_supernatant_frame;

    private TabLayout id_supernatant_tab;

    private VerticalTabLayout id_supernatant_tab_vertical;

    private FragmentManager fm;

    private TextEditSupernatantListener textEditSupernatantListener;

    //字体布局
    private BaseTextFragment fontFragment;

    private BaseTextFragment bgFragment;

    private BaseTextFragment alignFragment;

    private BaseTextFragment spacingFragment;

    private BaseTextFragment currentFragment;

    public TextEditSupernatantView(Context context) {
        super(context);
        init(context);
    }

    public TextEditSupernatantView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextEditSupernatantView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextEditSupernatantView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    //初始化方法，获取屏幕信息
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.text_edit_supernatant_view, this, true);
        // 屏幕宽度（像素）
        width = SupernatantCfg.getWidth();
        //float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        //int screenWidth = (int) (width / density);  // 屏幕宽度(dp)


        id_supernatant_relative = findViewById(R.id.id_supernatant_relative);
        id_supernatant_relative.setLayoutParams(new LayoutParams(width, width / 2));

        id_supernatant_frame = findViewById(R.id.id_supernatant_frame);

        id_supernatant_tab = findViewById(R.id.id_supernatant_tab);
        id_supernatant_tab_vertical = findViewById(R.id.id_supernatant_tab_vertical);

        List<SuperNatantMenu> menus= new ArrayList<>();
        Collections.addAll(menus, new SuperNatantMenu("BG", R.mipmap.icon_bubble_selected, R.mipmap.icon_bubble_normal,R.drawable.natant_bubble_select)
                , new SuperNatantMenu("FONT", R.mipmap.icon_font_selected, R.mipmap.icon_font_unselected,R.drawable.natant_font_select)
                , new SuperNatantMenu("ALIGN", R.mipmap.icon_align_selected, R.mipmap.icon_align_unselected,R.drawable.natant_align_select));

        id_supernatant_tab_vertical.setTabAdapter(new MyTabAdapter(menus));

        for (SuperNatantMenu mes:menus) {
            TabLayout.Tab bg = id_supernatant_tab.newTab();
            bg.setTag(mes.getName());
            bg.setIcon(mes.getSelectDrawable());
            id_supernatant_tab.addTab(bg);
        }

        fm = ((FragmentActivity) context).getSupportFragmentManager();
        //默认界面
        fontFragment = new TextFontFragment();
        bgFragment = new TextBgFragment();
        alignFragment = new TextAlignFragment();

        //默认直接加载界面
        switchFragment(alignFragment).commit();
        switchFragment(fontFragment).commit();
        switchFragment(bgFragment).commit();

        initListener();
    }

    //监听
    private void initListener() {
        //默认界面
        bgFragment.setBgListener(this);
        fontFragment.setFontListener(this);
        alignFragment.setAlignListener(this);

        id_supernatant_tab.addOnTabSelectedListener(this);
        id_supernatant_tab_vertical.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                if ("BG".equals(tab.getIcon().getTag())) {
                    switchFragment(bgFragment).commit();
                } else if ("FONT".equals(tab.getIcon().getTag())) {
                    switchFragment(fontFragment).commit();
                } else if ("ALIGN".equals(tab.getIcon().getTag())) {
                    switchFragment(alignFragment).commit();
                } else {
                    switchFragment(spacingFragment).commit();
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }


    //第一步：设置接口监听
    public void setTextEditSupernatantListener(TextEditSupernatantListener textEditSupernatantListener) {
        this.textEditSupernatantListener = textEditSupernatantListener;
    }


    /**
     * 根据传递的位置信息，进行样式修改
     *
     * @param type 枚举
     */
    public void setMenuPostion(SuperNatantEnum type) {
        if (id_supernatant_tab == null || id_supernatant_frame == null) {
            //未初始化控件
            return;
        }
        int height = SupernatantCfg.getWidth()/12;
        switch (type) {
            case RADIOTOP:
                //标签在顶部展示（水平方向）默认方向
                id_supernatant_tab_vertical.setVisibility(GONE);
                id_supernatant_tab.setVisibility(VISIBLE);
                LayoutParams top = new LayoutParams(LayoutParams.MATCH_PARENT, height);
                top.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                id_supernatant_tab.setLayoutParams(top);
                LayoutParams topView = new LayoutParams(LayoutParams.MATCH_PARENT, width / 2 - height);
                topView.addRule(RelativeLayout.BELOW, R.id.id_supernatant_tab);
                id_supernatant_frame.setLayoutParams(topView);
                break;
            case RADIODOWN:
                //标签在底部展示（水平方向）
                id_supernatant_tab_vertical.setVisibility(GONE);
                id_supernatant_tab.setVisibility(VISIBLE);
                LayoutParams down = new LayoutParams(LayoutParams.MATCH_PARENT, height);
                down.addRule(RelativeLayout.BELOW, R.id.id_supernatant_frame);
                id_supernatant_tab.setLayoutParams(down);
                LayoutParams downView = new LayoutParams(LayoutParams.MATCH_PARENT, width / 2 - height);
                downView.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                id_supernatant_frame.setLayoutParams(downView);
                break;
            case RADIOLEFT:
                //标签在左侧展示（垂直方向）
                id_supernatant_tab_vertical.setVisibility(VISIBLE);
                id_supernatant_tab.setVisibility(GONE);
                LayoutParams left = new LayoutParams(height, LayoutParams.MATCH_PARENT);
                left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                id_supernatant_tab_vertical.setLayoutParams(left);
                LayoutParams leftView = new LayoutParams(width - height, LayoutParams.MATCH_PARENT);
                leftView.addRule(RelativeLayout.RIGHT_OF, R.id.id_supernatant_tab_vertical);
                id_supernatant_frame.setLayoutParams(leftView);
                break;
            case RADIORIGHT:
                //标签在右侧展示（垂直方向）
                id_supernatant_tab_vertical.setVisibility(VISIBLE);
                id_supernatant_tab.setVisibility(GONE);
                LayoutParams right = new LayoutParams(height, LayoutParams.MATCH_PARENT);
                right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                id_supernatant_tab_vertical.setLayoutParams(right);
                LayoutParams rightView = new LayoutParams(width - height, LayoutParams.MATCH_PARENT);
                rightView.addRule(RelativeLayout.LEFT_OF, R.id.id_supernatant_tab_vertical);
                id_supernatant_frame.setLayoutParams(rightView);
                break;
        }
    }

    /**
     * 自定义背景界面
     *
     * @param fragment 字体布局
     */
    public void setBackgroundFragment(BaseTextFragment fragment) {
        if (fragment == null) {
            //用户布局为空
            return;
        }
        bgFragment = fragment;
        bgFragment.setBgListener(this);
    }

    /**
     * 自定义字体界面
     *
     * @param fragment 字体布局
     */
    public void setFontFragment(BaseTextFragment fragment) {
        if (fragment == null) {
            //用户布局为空
            return;
        }
        fontFragment = fragment;
        fontFragment.setFontListener(this);
    }

    /**
     * 自定义对齐界面
     *
     * @param fragment 字体布局
     */
    public void setAlignFragment(BaseTextFragment fragment) {
        if (fragment == null) {
            //用户布局为空
            return;
        }
        alignFragment = fragment;
        alignFragment.setAlignListener(this);
    }

    /**
     * 自定义样式界面
     *
     * @param fragment 字体布局
     */
    public void setSpacingFragment(BaseTextFragment fragment) {
        if (fragment == null) {
            //用户布局为空
            return;
        }
        spacingFragment = fragment;
        spacingFragment.setSpacingListener(this);
    }


    /**
     * 优化切换Fragment方案。
     *
     * @param fragment 需要展示的Fragment
     * @return FragmentTransaction
     */
    public FragmentTransaction switchFragment(BaseTextFragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                ft.hide(currentFragment);
            }
            ft.add(R.id.id_supernatant_frame, fragment);
        } else {
            ft.hide(currentFragment).show(fragment);
        }
        SuperNatantLog.d(currentFragment+"进行界面选择切换"+fragment);
        currentFragment = fragment;
        return ft;
    }


    @Override
    public void getBackGround(EditSupernatant background) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(background, SuperNatantEnum.NATANT_TEXTBACKGROUND);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }

    @Override
    public void getTypeface(EditSupernatant font) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(font, SuperNatantEnum.NATANT_FONTRECOVER);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }

    @Override
    public void getAlign(EditSupernatant align) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(align, SuperNatantEnum.NATANT_ALIGNMENTTHICKNESS);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }

    @Override
    public void getSpacing(EditSupernatant spacing) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(spacing, SuperNatantEnum.NATANT_FONTSIZESPACING);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }

    //tabLayout 选中某项
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if ("BG".equals(tab.getTag())) {
            switchFragment(bgFragment).commit();
        } else if ("FONT".equals(tab.getTag())) {
            switchFragment(fontFragment).commit();
        } else if ("ALIGN".equals(tab.getTag())) {
            switchFragment(alignFragment).commit();
        } else{
            switchFragment(spacingFragment).commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**设置背景资源
     * @param datas 资源列表
     */
    public void setBgResouce(List<EditSupernatant> datas) {
        if (bgFragment != null){
            bgFragment.updateBG(datas);
        }
    }

    /**设置字体资源
     * @param datas 资源列表
     */
    public void setFontResouce(List<EditSupernatant> datas) {
        if (bgFragment != null){
            bgFragment.updateBG(datas);
        }
    }

    /**
     * 设置居中样式列表
     * @param fontSize 字体大小列表
     * @param spacing 字体间距列表
     */
    public void setAlignResouce(List<EditSupernatant> fontSize, List<EditSupernatant> spacing){
        if (alignFragment != null){
            alignFragment.updateAlign(fontSize,spacing);
        }
    }

    /**
     *  当用户选中一个新的控件，焦点进行变更时，焦点发生变更时，调用此方法进行库内部清理。
     */
    public void resetNatant() {
        if (bgFragment !=null) {
            bgFragment.resetNatant();
        }
        if (fontFragment !=null) {
            fontFragment.resetNatant();
        }
        if (alignFragment !=null) {
            alignFragment.resetNatant();
        }
        if (spacingFragment !=null) {
            spacingFragment.resetNatant();
        }
    }


    private class MyTabAdapter implements TabAdapter {

        List<SuperNatantMenu> menus;

        public MyTabAdapter(List<SuperNatantMenu> data) {
            menus = data;
        }

        @Override
        public int getCount() {
            return menus.size();
        }

        @Override
        public TabView.TabBadge getBadge(int position) {
            return new TabView.TabBadge.Builder().build();
        }

        @Override
        public TabView.TabIcon getIcon(int position) {
            SuperNatantMenu menu = menus.get(position);
            return new TabView.TabIcon.Builder()
                    .setTag(menu.getName())
                    .setIcon(menu.getmSelectIcon(), menu.getmNormalIcon())
                    .setIconGravity(Gravity.END)
                    .build();
        }

        @Override
        public TabView.TabTitle getTitle(int position) {
            return new TabView.TabTitle.Builder().build();
        }

        @Override
        public int getBackground(int position) {
            return -1;
        }

    }
}
