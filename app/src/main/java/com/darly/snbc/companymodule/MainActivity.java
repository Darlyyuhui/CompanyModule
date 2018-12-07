package com.darly.snbc.companymodule;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.darly.snbc.base.BaseActivity;
import com.darly.snbc.snbcprint.bean.FontRecover;
import com.darly.snbc.snbcprint.listener.TextEditSupernatantListener;
import com.darly.snbc.widget.textedit.SuperNatantEnum;
import com.darly.snbc.widget.textedit.TextEditSupernatantView;

public class MainActivity extends BaseActivity implements TextEditSupernatantListener, RadioGroup.OnCheckedChangeListener {

    EditText id_main_edit;

    RadioGroup id_main_radio_group;
    RadioButton id_main_radio_text_edit;
    RadioButton id_main_radio_text_down;
    RadioButton id_main_radio_text_left;
    RadioButton id_main_radio_text_right;
    TextEditSupernatantView id_main_radio_text_edit_view;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        id_main_radio_group = findViewById(R.id.id_main_radio_group);
        id_main_radio_text_edit = findViewById(R.id.id_main_radio_text_edit);
        id_main_radio_text_down = findViewById(R.id.id_main_radio_text_down);
        id_main_radio_text_left = findViewById(R.id.id_main_radio_text_left);
        id_main_radio_text_right = findViewById(R.id.id_main_radio_text_right);
        id_main_edit = findViewById(R.id.id_main_edit);
        id_main_radio_text_edit_view = findViewById(R.id.id_main_radio_text_edit_view);
    }

    @Override
    protected void loadData() {
        id_main_radio_text_edit.setSelected(true);
    }

    @Override
    protected void initListener() {
        id_main_radio_text_edit_view.setTextEditSupernatantListener(this);
        id_main_radio_group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onFontSelect(FontRecover font) {
        id_main_edit.setTypeface(font.getTypeface());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View viewById = group.findViewById(checkedId);
        if (!viewById.isPressed()){
            return;
        }
        switch (checkedId) {
            case R.id.id_main_radio_text_edit:
                id_main_radio_text_edit.setSelected(true);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(false);
                id_main_radio_text_edit_view.setRadioGroupPostion(SuperNatantEnum.RADIOTOP);
                id_main_radio_text_edit_view.setVisibility(View.VISIBLE);
                break;
            case R.id.id_main_radio_text_down:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(true);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(false);
                id_main_radio_text_edit_view.setRadioGroupPostion(SuperNatantEnum.RADIODOWN);
                id_main_radio_text_edit_view.setVisibility(View.VISIBLE);
                break;
            case R.id.id_main_radio_text_left:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(true);
                id_main_radio_text_right.setSelected(false);
                id_main_radio_text_edit_view.setRadioGroupPostion(SuperNatantEnum.RADIOLEFT);
                id_main_radio_text_edit_view.setVisibility(View.VISIBLE);
                break;
            case R.id.id_main_radio_text_right:
                id_main_radio_text_edit.setSelected(false);
                id_main_radio_text_down.setSelected(false);
                id_main_radio_text_left.setSelected(false);
                id_main_radio_text_right.setSelected(true);
                id_main_radio_text_edit_view.setRadioGroupPostion(SuperNatantEnum.RADIORIGHT);
                id_main_radio_text_edit_view.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
