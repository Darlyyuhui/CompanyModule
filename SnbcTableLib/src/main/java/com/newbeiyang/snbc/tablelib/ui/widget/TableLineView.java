package com.newbeiyang.snbc.tablelib.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.newbeiyang.snbc.tablelib.R;
import com.newbeiyang.snbc.tablelib.bean.TableEditBean;
import com.newbeiyang.snbc.tablelib.common.listener.TableLineListener;


/**
 * 定义边框获取View
 * 包名称：com.newbeiyang.snbc.tablelib.ui.widget
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/17 15:46
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TableLineView extends LinearLayout {

    private Context mContext;

    private LinearLayout table_line_view;
    private View table_line_view_iv;
    private RadioGroup table_line_radio_group;
    private RadioButton table_line_radio_blank;
    private RadioButton table_line_radio_gray;
    private RadioButton table_line_radio_white;

    private TableLineListener lineListener;

    private boolean isOutLine;

    private int lineRes = 0;

    private int lineColor = Color.BLACK;

    private int[] lines = new int[]{R.drawable.shape_line_default1, R.drawable.shape_line_default2, R.drawable.shape_line_default3, R.drawable.shape_line_default4};

    private TableEditBean bean = new TableEditBean();

    public TableLineView(Context context) {
        super(context);
        init(context);
    }

    public TableLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TableLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TableLineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    /**
     * 界面初始化
     *
     * @param context 父类引用
     */
    private void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.table_lib_line_view, this, true);
        table_line_view = findViewById(R.id.table_line_view);
        table_line_view_iv = findViewById(R.id.table_line_view_iv);
        table_line_radio_group = findViewById(R.id.table_line_radio_group);
        table_line_radio_blank = findViewById(R.id.table_line_radio_blank);
        table_line_radio_gray = findViewById(R.id.table_line_radio_gray);
        table_line_radio_white = findViewById(R.id.table_line_radio_white);
        table_line_radio_blank.setChecked(true);
        initListener();
    }

    private void initListener() {
        table_line_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (lineListener == null) {
                    Log.d(getClass().getSimpleName(), "接口未初始化");
                }
                //点选修改
                if (checkedId == table_line_radio_blank.getId()) {
                    lineColor = Color.BLACK;
                    //选黑
                } else if (checkedId == table_line_radio_gray.getId()) {
                    //选灰
                    lineColor = Color.GRAY;
                } else if (checkedId == table_line_radio_white.getId()) {
                    //选白
                    lineColor = Color.WHITE;
                }
                if (isOutLine) {
                    bean.setExtcolor(lineColor);
                    bean.setExttype(lineRes);
                } else {
                    bean.setIncolor(lineColor);
                    bean.setIntype(lineRes);
                }
                lineListener.onChecked(bean);
            }
        });

        table_line_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击弹出层
                SelectLineView select = new SelectLineView(mContext, lines, "请选择线条类型");
                select.setSelectResultItemClick(new SelectLineView.SelectResultItemClick() {
                    @Override
                    public void resultOnClick(int result, String title, int position) {
                        lineRes = position;
                        table_line_view_iv.setBackgroundResource(lines[position]);
                        if (isOutLine) {
                            bean.setExtcolor(lineColor);
                            bean.setExttype(lineRes);
                        } else {
                            bean.setIncolor(lineColor);
                            bean.setIntype(lineRes);
                        }
                        lineListener.onChecked(bean);
                    }
                });
                select.show();
            }
        });
    }


    public void setTableLineListener(TableLineListener tableLineListener, boolean isOutLine) {
        this.lineListener = tableLineListener;
        this.isOutLine = isOutLine;
    }


    public void setDefaultConfig(int color, int type) {
        switch (color) {
            case Color.BLACK:
                table_line_radio_blank.setChecked(true);
                break;
            case Color.GRAY:
                table_line_radio_gray.setChecked(true);
                break;
            case Color.WHITE:
                table_line_radio_white.setChecked(true);
                break;
        }
        table_line_view_iv.setBackgroundResource(lines[type]);
        lineColor = color;
        lineRes = type;
        if (isOutLine) {
            bean.setExtcolor(lineColor);
            bean.setExttype(lineRes);
        } else {
            bean.setIncolor(lineColor);
            bean.setIntype(lineRes);
        }
    }

    public int getLineColor() {
        return lineColor;
    }

    public int getLineRes() {
        return lineRes;
    }
}
