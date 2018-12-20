package com.darly.snbc.companymodule;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.darly.snbc.base.BaseActivity;
import com.darly.snbc.widget.text.OnDoubleClickListener;
import com.darly.snbc.widget.text.TextEditBackgroundView;
import com.newbeiyang.snbc.tablelib.TableEditManager;
import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.TableEditEnum;
import com.newbeiyang.snbc.tablelib.common.listener.TableEditListener;
import com.newbeiyang.snbc.textlib.TextEditManager;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.bean.SuperNatantMenu;
import com.newbeiyang.snbc.textlib.common.SuperNatantConstant;
import com.newbeiyang.snbc.textlib.common.SuperNatantEnum;
import com.newbeiyang.snbc.textlib.common.listener.TextEditSupernatantListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用默认布局传递参数
 * 包名称：com.darly.snbc.companymodule
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 11:07
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ParamerActivity extends BaseActivity implements View.OnClickListener, TextEditSupernatantListener, TabLayout.OnTabSelectedListener, TableEditListener {
    EditText id_main_edit;
    ImageView id_main_edit_view_gone;
    TabLayout id_main_tab;

    RelativeLayout id_main_parent;
    LinearLayout id_main_content;
    ScrollView id_main_scroll;
    TextEditManager manager;
    private TableEditManager tableEditManager;

    private Handler handler;

    private TextView currentCheckView;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        id_main_tab = findViewById(R.id.id_main_tab);
        id_main_edit = findViewById(R.id.id_main_edit);
        id_main_edit_view_gone = findViewById(R.id.id_main_edit_view_gone);
        id_main_parent = findViewById(R.id.id_main_parent);
        id_main_content = findViewById(R.id.id_main_content);
        id_main_scroll = findViewById(R.id.id_main_scroll);
        currentCheckView = id_main_edit;
    }

    @Override
    protected void loadData() {

        TabLayout.Tab textEdit = id_main_tab.newTab();
        textEdit.setIcon(R.mipmap.icon_text);
        textEdit.setText("文字");
        id_main_tab.addTab(textEdit);

        TabLayout.Tab tableEdit = id_main_tab.newTab();
        tableEdit.setIcon(R.mipmap.icon_form);
        tableEdit.setText("表格");
        id_main_tab.addTab(tableEdit);


        handler = new Handler();
        manager = new TextEditManager(BuildConfig.DEBUG, this, getPackageName());
        manager.init(id_main_parent).setListener(this);
        //设置菜单
        List<SuperNatantMenu> menusD = new ArrayList<>();
        Collections.addAll(menusD
                , new SuperNatantMenu(SuperNatantConstant.MENU_FT, "加", com.newbeiyang.snbc.textlib.R.mipmap.icon_font_selected, com.newbeiyang.snbc.textlib.R.mipmap.icon_font_unselected, com.newbeiyang.snbc.textlib.R.drawable.natant_font_select)
                , new SuperNatantMenu(SuperNatantConstant.MENU_AN, "减", com.newbeiyang.snbc.textlib.R.mipmap.icon_align_selected, com.newbeiyang.snbc.textlib.R.mipmap.icon_align_unselected, com.newbeiyang.snbc.textlib.R.drawable.natant_align_select)
                , new SuperNatantMenu(SuperNatantConstant.MENU_BG, "乘", com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_selected, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_normal, com.newbeiyang.snbc.textlib.R.drawable.natant_bubble_select));
        manager.setMenu(menusD);

        //设置背景参数
        List<EditSupernatant> data = new ArrayList<EditSupernatant>();
        data.add(new EditSupernatant(null, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_7_preview, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_7));
        data.add(new EditSupernatant(null, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_6_preview, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_6));
        data.add(new EditSupernatant(null, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_5_preview, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_5));
        data.add(new EditSupernatant(null, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_4_preview, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_4));
        data.add(new EditSupernatant(null, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_3_preview, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_3));
        data.add(new EditSupernatant(null, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_2_preview, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_2));
        data.add(new EditSupernatant(null, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_1_preview, com.newbeiyang.snbc.textlib.R.mipmap.icon_bubble_1));
        manager.setBackgroundResouce(data);

        List<EditSupernatant> fontData = new ArrayList<EditSupernatant>();
        for (int i = 1; i < 10; i++) {
            EditSupernatant recover = new EditSupernatant();
            recover.setFontName("字体" + i);
            recover.setTypeface(Typeface.createFromAsset(getAssets(), "font_" + i + ".ttf"));
            fontData.add(recover);
        }
        manager.setFontResouce(fontData);

        List<EditSupernatant> menus = new ArrayList<EditSupernatant>();
        Collections.addAll(menus, new EditSupernatant("七号", 12)
                , new EditSupernatant("五号", 22)
                , new EditSupernatant("三号", 30)
                , new EditSupernatant("一号", 38));
        List<EditSupernatant> libs = new ArrayList<EditSupernatant>();
        Collections.addAll(libs, new EditSupernatant(1.0f)
                , new EditSupernatant(2.0f)
                , new EditSupernatant(3.0f)
                , new EditSupernatant(4.0f)
                , new EditSupernatant(5.0f)
                , new EditSupernatant(6.0f));
        manager.setAlignResouce(menus, libs);


        tableEditManager = new TableEditManager(BuildConfig.DEBUG, this, getPackageName());
        tableEditManager.init(id_main_parent).setListener(this);
    }

    @Override
    protected void initListener() {
        id_main_tab.addOnTabSelectedListener(this);
        id_main_edit_view_gone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_main_edit_view_gone:
                manager.dismiss();
                tableEditManager.dismiss();
                id_main_edit_view_gone.setVisibility(View.GONE);
                break;
        }
    }

    //修改背景
    public void onBackGroundLocal(int resId) {
        if (resId != 0) {
            if (id_main_content.getChildCount() > 100) {
                Toast.makeText(this, "最多可以增加100个元素", Toast.LENGTH_SHORT).show();
                return;
            }
            final TextEditBackgroundView view = new TextEditBackgroundView(this);
            view.setBackgroundView(resId);
            view.setOnTouchListener(new OnDoubleClickListener(new OnDoubleClickListener.DoubleClickCallback() {
                @Override
                public void onClick(View v) {
                    //处理单击事件选中进行文字功能修改、
                    //获取焦点
                    view.setFocusable(true);
                    view.setFocusableInTouchMode(true);
                    view.requestFocus();
                    view.requestFocusFromTouch();
                    //直接获取控件
                    currentCheckView = view.getEdit();
                    manager.resetFocuss();
                }

                @Override
                public void onDoubleClick(View v) {
                    //处理双击事件
                    Toast.makeText(ParamerActivity.this, "onDoubleClick--->进行汉字编写", Toast.LENGTH_SHORT).show();
                }
            }));
            id_main_content.addView(view);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    id_main_scroll.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

    //修改字体
    public void onFontSelect(Typeface font) {
        if (currentCheckView != null) {
            currentCheckView.setTypeface(font);
        }
    }

    //修改对齐方式粗细
    public void onAlignmentThickness(EditSupernatant align) {
        if (align == null) {
            Log.d(getClass().getSimpleName(), "参数传递错误");
            return;
        }
        if (currentCheckView != null) {
            if (align.getSize() != 0) {
                currentCheckView.setTextSize(TypedValue.COMPLEX_UNIT_SP, align.getSize());
            }
            if (align.getLineSpacingMultiplier() != 0) {
                currentCheckView.setLineSpacing(0, align.getLineSpacingMultiplier());
            }
            String text = currentCheckView.getText().toString().trim();
            SpannableString spanString = new SpannableString(text);
            if (align.isBold()) {
                //设置粗体
                StyleSpan span = new StyleSpan(Typeface.BOLD);
                spanString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (align.isItaic()) {
                //设置斜体
                StyleSpan span = new StyleSpan(Typeface.ITALIC);
                spanString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (align.isUnderLine()) {
                //设置下划线
                UnderlineSpan span = new UnderlineSpan();
                spanString.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            currentCheckView.setText(spanString);
            switch (align.getGravy()) {
                case 1:
                    currentCheckView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                    break;
                case 2:
                    currentCheckView.setGravity(Gravity.CENTER);
                    break;
                case 3:
                    currentCheckView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                    break;
                default:
                    break;
            }
            if (align.getMove() != 0) {
                int padding = currentCheckView.getPaddingLeft();
                if (align.getMove() == -1) {
                    //左移动一格
                    currentCheckView.setPadding(padding - 10, 0, 0, 0);
                } else if (align.getMove() == 1) {
                    //右移动一格
                    currentCheckView.setPadding(padding + 10, 0, 0, 0);
                }
            }
        }

    }


    @Override
    public void onSelectSupernatant(EditSupernatant supernatant, SuperNatantEnum type) {
        switch (type) {
            case NATANT_TEXTBACKGROUND:
                onBackGroundLocal(supernatant.getResId());
                break;
            case NATANT_FONTRECOVER:
                onFontSelect(supernatant.getTypeface());
                break;
            case NATANT_ALIGNMENTTHICKNESS:
                onAlignmentThickness(supernatant);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if ("文字".equals(tab.getText().toString().trim())) {
            manager.setMenuPostion(SuperNatantEnum.RADIOTOP).show();
        } else if ("表格".equals(tab.getText().toString().trim())) {
            tableEditManager.show();
        }
        id_main_edit_view_gone.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTableCreate(TableEditBean tableEditBean, TableEditEnum direction) {

    }
}
