package com.darly.snbc.companymodule;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
import com.darly.snbc.snbcprint.TextEditManager;
import com.darly.snbc.snbcprint.bean.EditSupernatant;
import com.darly.snbc.snbcprint.common.SuperNatantEnum;
import com.darly.snbc.snbcprint.listener.TextEditSupernatantListener;
import com.darly.snbc.widget.text.OnDoubleClickListener;
import com.darly.snbc.widget.text.TextEditBackgroundView;

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
        manager.init(id_main_parent);

    }

    @Override
    protected void initListener() {
        manager.setListener(this);
        id_main_radio_group.setOnCheckedChangeListener(this);
        id_main_edit_view_gone.setOnClickListener(this);
        manager.setView(new BGFragment(), SuperNatantEnum.BACKGROUND);
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
    public void onAlignmentThickness(EditSupernatant alignmentThickness) {

    }
    //修改间距字号
    public void onFontSizeSpacing(EditSupernatant fontSizeSpacing) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.id_main_radio_text_edit:
                id_main_radio_text_edit.setSelected(true);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(false);
                manager.setMenuPostion(SuperNatantEnum.RADIOTOP);
                break;
            case R.id.id_main_radio_text_down:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(true);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(false);
                manager.setMenuPostion(SuperNatantEnum.RADIODOWN);
                break;
            case R.id.id_main_radio_text_left:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(true);
                id_main_radio_text_right.setSelected(false);
                manager.setMenuPostion(SuperNatantEnum.RADIOLEFT);
                break;
            case R.id.id_main_radio_text_right:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(true);
                manager.setMenuPostion(SuperNatantEnum.RADIORIGHT);
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
            case NATANT_FONTSIZESPACING:
                onFontSizeSpacing(supernatant);
                break;
            default:
                break;
        }
    }
}
