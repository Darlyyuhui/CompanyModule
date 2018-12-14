package com.darly.snbc.companymodule;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.darly.snbc.base.BaseActivity;
import com.darly.snbc.widget.text.OnDoubleClickListener;
import com.darly.snbc.widget.text.TextEditBackgroundView;
import com.newbeiyang.snbc.textlib.TextEditManager;
import com.newbeiyang.snbc.textlib.bean.EditSupernatant;
import com.newbeiyang.snbc.textlib.common.SuperNatantEnum;
import com.newbeiyang.snbc.textlib.common.listener.TextEditSupernatantListener;
/**
 * 使用默认布局
 * 包名称：com.darly.snbc.companymodule
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/14 11:07
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, TextEditSupernatantListener, RadioGroup.OnCheckedChangeListener {

    EditText id_main_edit;
    ImageView id_main_edit_view_gone;
    RadioGroup id_main_radio_group;
    RadioButton id_main_radio_text_edit;
    RadioButton id_main_radio_text_down;
    RadioButton id_main_radio_text_left;
    RadioButton id_main_radio_text_right;
    RelativeLayout id_main_parent;
    LinearLayout id_main_content;
    ScrollView id_main_scroll;
    TextEditManager manager;

    private Handler handler;

    private TextView currentCheckView;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        id_main_radio_group = findViewById(R.id.id_main_radio_group);
        id_main_radio_text_edit = findViewById(R.id.id_main_radio_text_edit);
        id_main_radio_text_down = findViewById(R.id.id_main_radio_text_down);
        id_main_radio_text_left = findViewById(R.id.id_main_radio_text_left);
        id_main_radio_text_right = findViewById(R.id.id_main_radio_text_right);
        id_main_edit = findViewById(R.id.id_main_edit);
        id_main_edit_view_gone = findViewById(R.id.id_main_edit_view_gone);
        id_main_parent = findViewById(R.id.id_main_parent);
        id_main_content = findViewById(R.id.id_main_content);
        id_main_scroll = findViewById(R.id.id_main_scroll);
        currentCheckView = id_main_edit;
    }

    @Override
    protected void loadData() {

        handler = new Handler();
        manager = new TextEditManager(BuildConfig.DEBUG, this, getPackageName());


    }

    @Override
    protected void initListener() {
        manager.init(id_main_parent).setListener(this);
        id_main_radio_group.setOnCheckedChangeListener(this);
        id_main_edit_view_gone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_main_edit_view_gone:
                id_main_radio_text_edit.setChecked(false);
                id_main_radio_text_down.setChecked(false);
                id_main_radio_text_left.setChecked(false);
                id_main_radio_text_right.setChecked(false);
                manager.dismiss();
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
                    Toast.makeText(MainActivity.this, "onDoubleClick--->进行汉字编写", Toast.LENGTH_SHORT).show();
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
                spanString.setSpan(span,0,text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
                    currentCheckView.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                    break;
                case 2:
                    currentCheckView.setGravity(Gravity.CENTER);
                    break;
                case 3:
                    currentCheckView.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                    break;
                default:
                    break;
            }
            if (align.getMove()!=0){
                int padding = currentCheckView.getPaddingLeft();
                if (align.getMove() == -1){
                    //左移动一格
                    currentCheckView.setPadding(padding-10,0,0,0);
                }else if (align.getMove() == 1){
                    //右移动一格
                    currentCheckView.setPadding(padding+10,0,0,0);
                }
            }
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.id_main_radio_text_edit:
                id_main_radio_text_edit.setSelected(true);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(false);
                manager.setMenuPostion(SuperNatantEnum.RADIOTOP).show();
                break;
            case R.id.id_main_radio_text_down:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(true);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(false);
                manager.setMenuPostion(SuperNatantEnum.RADIODOWN).show();
                break;
            case R.id.id_main_radio_text_left:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(true);
                id_main_radio_text_right.setSelected(false);
                manager.setMenuPostion(SuperNatantEnum.RADIOLEFT).show();
                break;
            case R.id.id_main_radio_text_right:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(true);
                manager.setMenuPostion(SuperNatantEnum.RADIORIGHT).show();
                break;
            default:
                break;
        }
        id_main_edit_view_gone.setVisibility(View.VISIBLE);
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
}
