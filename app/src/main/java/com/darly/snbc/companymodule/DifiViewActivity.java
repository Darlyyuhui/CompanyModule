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
import com.darly.snbc.companymodule.fragment.AlignFragment;
import com.darly.snbc.companymodule.fragment.BgFragment;
import com.darly.snbc.companymodule.fragment.FontFragment;
import com.darly.snbc.companymodule.fragment.TableFragment;
import com.darly.snbc.widget.table.ExcelRecyclerView;
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
 * 使用自定义布局
 * 包名称：com.darly.snbc.companymodule
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 11:06
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class DifiViewActivity extends BaseActivity implements View.OnClickListener, TextEditSupernatantListener, TabLayout.OnTabSelectedListener,TableEditListener{
    EditText id_main_edit;
    ImageView id_main_edit_view_gone;
    TabLayout id_main_tab;


    RelativeLayout id_main_parent;
    LinearLayout id_main_content;
    ScrollView id_main_scroll;
    TextEditManager manager;
    private TableEditManager tableEditManager;
    private ExcelRecyclerView currentTableLayout;

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
        manager.setView(new BgFragment(), SuperNatantEnum.BACKGROUND);
        manager.setView(new FontFragment(), SuperNatantEnum.FONT);
        manager.setView(new AlignFragment(), SuperNatantEnum.ALIGNMENT);

        tableEditManager = new TableEditManager(BuildConfig.DEBUG, this, getPackageName());
        tableEditManager.init(id_main_parent).setListener(this);
        tableEditManager.setView(new TableFragment());
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
                    Toast.makeText(DifiViewActivity.this, "onDoubleClick--->进行汉字编写", Toast.LENGTH_SHORT).show();
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
                outLine(tableEditBean);
                SuperNatantLog.d(getClass().getSimpleName() + "外边框修改成功");
                break;
            case TABLE_IN_LINE:
                outIn(tableEditBean);
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
            currentTableLayout.setDivider(tableEditBean, true);
        }
    }

    /**
     * 修改内边框
     *
     * @param tableEditBean 简表参数
     */
    private void outIn(TableEditBean tableEditBean) {
        if (currentTableLayout != null) {
            currentTableLayout.setDivider(tableEditBean, false);
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
        tableLayout.setDivider(tableEditBean, true);
        tableLayout.setDivider(tableEditBean, false);

        LinearLayout.LayoutParams localLayoutParams = null;
        if (isVertical) {
            //垂直表格
            tableLayout.setRotation(90);
            localLayoutParams = new LinearLayout.LayoutParams((int) (SupernatantCfg.getWidth() / 1.5), SupernatantCfg.getWidth() / 2 + 10);
            localLayoutParams.setMargins(50, 60, 0, 60);
            tableLayout.setPadding(0, 10, 10, 50);
        } else {
            //水平表格
            tableLayout.setPadding(10, 10, 10, 0);
            localLayoutParams = new LinearLayout.LayoutParams((int) (SupernatantCfg.getWidth() / 1.5), SupernatantCfg.getWidth() / 2 + 10);
            localLayoutParams.setMargins(0, 20, 0, 40);
        }

        localLayoutParams.gravity = 1;
        id_main_content.addView(tableLayout, localLayoutParams);
        handler.post(new Runnable() {
            @Override
            public void run() {
                id_main_scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        tableLayout.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
            @Override
            public void itemClick(TextView tv, int position) {
                //点击输入文字
                SuperNatantLog.d(getClass().getSimpleName() + "点击输入文字");
                tv.setText("测试汉字");
                tableLayout.setStringData("测试汉字", position);
                //获取焦点
                tableLayout.setFocusable(true);
                tableLayout.setFocusableInTouchMode(true);
                tableLayout.requestFocus();
                tableLayout.requestFocusFromTouch();
                //直接获取控件
                currentTableLayout = tableLayout;
                tableEditManager.resetFocuss(currentTableLayout.getBean());
            }
        });
    }

}
