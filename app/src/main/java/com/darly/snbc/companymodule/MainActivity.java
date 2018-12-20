package com.darly.snbc.companymodule;

import android.graphics.Color;
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
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.darly.snbc.base.BaseActivity;
import com.darly.snbc.widget.table.ExcelRecyclerView;
import com.darly.snbc.widget.table.GridInsideDivider;
import com.darly.snbc.widget.table.GridOutsideDivider;
import com.darly.snbc.widget.table.adapter.TableAdapter;
import com.darly.snbc.widget.text.OnDoubleClickListener;
import com.darly.snbc.widget.text.TextEditBackgroundView;
import com.newbeiyang.snbc.tablelib.TableEditManager;
import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.TableEditEnum;
import com.newbeiyang.snbc.tablelib.common.listener.TableEditListener;
import com.newbeiyang.snbc.textlib.TextEditManager;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.SuperNatantEnum;
import com.newbeiyang.snbc.textlib.common.listener.TextEditSupernatantListener;
import com.newbeiyang.snbc.textlib.common.log.SuperNatantLog;
import com.newbeiyang.snbc.textlib.common.observer.SupernatantCfg;

/**
 * 使用默认布局
 * 包名称：com.darly.snbc.companymodule
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 11:07
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, TextEditSupernatantListener, TabLayout.OnTabSelectedListener, TableEditListener {

    EditText id_main_edit;
    ImageView id_main_edit_view_gone;
    TabLayout id_main_tab;

    RelativeLayout id_main_parent;
    LinearLayout id_main_content;
    ScrollView id_main_scroll;
    TextEditManager manager;

    private Handler handler;

    private TextView currentCheckView;

    private TableEditManager tableEditManager;

    private ExcelRecyclerView currentTableLayout;

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

        id_main_content.setDividerPadding(20);
        id_main_content.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        TabLayout.Tab textEdit = id_main_tab.newTab();
        textEdit.setIcon(R.mipmap.icon_text);
        textEdit.setText("文字");
        id_main_tab.addTab(textEdit, false);

        TabLayout.Tab tableEdit = id_main_tab.newTab();
        tableEdit.setIcon(R.mipmap.icon_form);
        tableEdit.setText("表格");
        id_main_tab.addTab(tableEdit, false);

        handler = new Handler();
        manager = new TextEditManager(BuildConfig.DEBUG, this, getPackageName());
        tableEditManager = new TableEditManager(BuildConfig.DEBUG, this, getPackageName());

    }

    @Override
    protected void initListener() {
        manager.init(id_main_parent).setListener(this);
        tableEditManager.init(id_main_parent).setListener(this);

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
                id_main_tab.setSelected(false);
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
                    Toast.makeText(MainActivity.this, "onDoubleClick--->进行汉字编写", Toast.LENGTH_SHORT).show();
                }
            }));

            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            localLayoutParams.setMargins(0, 20, 0, 10);
            id_main_content.addView(view, localLayoutParams);

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
    public void onTabSelected(TabLayout.Tab tab) {
        if ("文字".equals(tab.getText().toString().trim())) {
            manager.setMenuPostion(SuperNatantEnum.RADIOTOP).show();
            tableEditManager.dismiss();
        } else if ("表格".equals(tab.getText().toString().trim())) {
            manager.dismiss();
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
    public void onTableCreate(TableEditBean tableEditBean, TableEditEnum direction) {

        switch (direction) {
            case VERTICAL:
                createTable(tableEditBean, true);
                SuperNatantLog.d(getClass().getSimpleName() + "垂直表格建立成功");
                break;
            case HORIZONTAL:
                createTable(tableEditBean, false);
                SuperNatantLog.d(getClass().getSimpleName() + "水平表格建立成功");
                break;
            case TABLE_CLOUMN_ROW_ADD:
                addTableCloumnRow(tableEditBean);
                SuperNatantLog.d(getClass().getSimpleName() + "行列添加成功");
                break;
            case TABLE_CLOUMN_ROW_MINUS:
                minusTableCloumnRow(tableEditBean);
                SuperNatantLog.d(getClass().getSimpleName() + "行列移除成功");
                break;
            case TABLE_OUT_LINE:
                SuperNatantLog.d(getClass().getSimpleName() + "外边框修改成功");
                break;
            case TABLE_IN_LINE:
                SuperNatantLog.d(getClass().getSimpleName() + "内边框修改成功");
                break;
            default:
                break;
        }


    }

    /**
     * 新增表格行列
     *
     * @param tableEditBean 简表参数
     */
    private void addTableCloumnRow(TableEditBean tableEditBean) {
        if (currentTableLayout != null) {
            if (tableEditBean.getCloumn() != 0) {
                currentTableLayout.addColumn();
            }
            if (tableEditBean.getRow() != 0) {
                currentTableLayout.addRow();
            }
        }
    }

    /**
     * 删除表格行列
     *
     * @param tableEditBean 简表参数
     */
    private void minusTableCloumnRow(TableEditBean tableEditBean) {
        if (currentTableLayout != null) {
            if (tableEditBean.getCloumn() != 0) {
                currentTableLayout.deleteColumn();
            }
            if (tableEditBean.getRow() != 0) {
                currentTableLayout.deleteRow();
            }
        }
    }

    /**
     * 修改外边框
     *
     * @param tableEditBean 简表参数
     */
    private void outLine(TableEditBean tableEditBean) {
        if (currentTableLayout != null) {
            currentTableLayout.setOutsideDivider(new GridOutsideDivider(tableEditBean.getCloumn(), 0, 2, tableEditBean.getExtcolor()));
        }
    }

    /**
     * 修改内边框
     *
     * @param tableEditBean 简表参数
     */
    private void outIn(TableEditBean tableEditBean) {
        if (currentTableLayout != null) {
            currentTableLayout.setInsideDivider(new GridInsideDivider(tableEditBean.getCloumn(), 0, 2, tableEditBean.getIncolor()));
        }
    }

    /**
     * 根据条件建立表格
     *
     * @param isVertical    是否垂直表格
     * @param tableEditBean 简表参数
     */
    private void createTable(TableEditBean tableEditBean, boolean isVertical) {
        final ExcelRecyclerView tableLayout = new ExcelRecyclerView(this);
        currentTableLayout = tableLayout;
        tableLayout.initAdapter(tableEditBean.getRow(), tableEditBean.getCloumn());
        switch (tableEditBean.getIntype()) {
            case 0:
                tableLayout.setInsideDivider(new GridInsideDivider(tableEditBean.getCloumn(), 0, 2, tableEditBean.getIncolor()));
                break;
            case 1:
                tableLayout.setInsideDivider(new GridInsideDivider(tableEditBean.getCloumn(), 0, 4, tableEditBean.getIncolor()));
                break;
            case 2:
                tableLayout.setInsideDivider(new GridInsideDivider(tableEditBean.getCloumn(), 8, 4, tableEditBean.getIncolor()));
                break;
            case 3:
                tableLayout.setInsideDivider(new GridInsideDivider(tableEditBean.getCloumn(), 2, 2, tableEditBean.getIncolor()));
                break;
        }

        switch (tableEditBean.getExttype()) {
            case 0:
                tableLayout.setOutsideDivider(new GridOutsideDivider(tableEditBean.getCloumn(), 0, 2, tableEditBean.getExtcolor()));
                break;
            case 1:
                tableLayout.setOutsideDivider(new GridOutsideDivider(tableEditBean.getCloumn(), 0, 4, tableEditBean.getExtcolor()));
                break;
            case 2:
                tableLayout.setOutsideDivider(new GridOutsideDivider(tableEditBean.getCloumn(), 8, 4, tableEditBean.getExtcolor()));
                break;
            case 3:
                tableLayout.setOutsideDivider(new GridOutsideDivider(tableEditBean.getCloumn(), 2, 2, tableEditBean.getExtcolor()));
                break;
        }
        tableLayout.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
            @Override
            public void itemClick(TextView tv, int position) {
                //点击输入文字
                SuperNatantLog.d(getClass().getSimpleName() + "点击输入文字");
                tv.setText("测试汉字");
                //获取焦点
                tableLayout.setFocusable(true);
                tableLayout.setFocusableInTouchMode(true);
                tableLayout.requestFocus();
                tableLayout.requestFocusFromTouch();
                //直接获取控件
                currentTableLayout = tableLayout;
            }
        });
        if (isVertical) {
            //垂直表格
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(SupernatantCfg.getWidth() / 2, (int) ((SupernatantCfg.getWidth() / 2) * 1.5));
            localLayoutParams.setMargins(0, 20, 0, 10);
            localLayoutParams.gravity = 1;
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
            anim.setFillAfter(true);
            tableLayout.startAnimation(anim);
            id_main_content.addView(tableLayout, localLayoutParams);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    id_main_scroll.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        } else {
            //水平表格
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams((int) (SupernatantCfg.getWidth() / 1.5), SupernatantCfg.getWidth() / 2);
            localLayoutParams.setMargins(0, 20, 0, 10);
            localLayoutParams.gravity = 1;
            id_main_content.addView(tableLayout, localLayoutParams);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    id_main_scroll.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

}
