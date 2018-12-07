package com.darly.snbc.widget.textedit;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.darly.snbc.snbcprint.R;
import com.darly.snbc.snbcprint.bean.FontRecover;
import com.darly.snbc.snbcprint.font.TextFontCallBack;
import com.darly.snbc.snbcprint.font.TextFontFragment;
import com.darly.snbc.snbcprint.fragment.NatantFragment;
import com.darly.snbc.snbcprint.fragment.SnbcPrintFragment;
import com.darly.snbc.snbcprint.listener.TextEditSupernatantListener;

import java.util.List;

/**
 * 文本编辑浮层主类
 * 包名称：com.darly.snbc.widget.textedit
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 9:38
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextEditSupernatantView extends RelativeLayout implements RadioGroup.OnCheckedChangeListener {
    private int width;
    private RelativeLayout id_supernatant_relative;

    private FrameLayout id_supernatant_frame;
    private RadioGroup id_supernatant_radiogroup;

    private RadioButton id_supernatant_bubble;
    private RadioButton id_supernatant_font;
    private RadioButton id_supernatant_align;
    private RadioButton id_supernatant_italic;


    private  FragmentManager fm;

    private TextEditSupernatantListener textEditSupernatantListener;

    //字体布局
    private SnbcPrintFragment natantFragment;

    private TextFontFragment textFontFragment;

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
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        width = dm.widthPixels;
        //float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        //int screenWidth = (int) (width / density);  // 屏幕宽度(dp)


        id_supernatant_relative = findViewById(R.id.id_supernatant_relative);
        id_supernatant_relative.setLayoutParams(new RelativeLayout.LayoutParams(width, width / 2));

        id_supernatant_frame = findViewById(R.id.id_supernatant_frame);

        id_supernatant_radiogroup = findViewById(R.id.id_supernatant_radiogroup);
        id_supernatant_bubble = findViewById(R.id.id_supernatant_bubble);
        id_supernatant_font = findViewById(R.id.id_supernatant_font);
        id_supernatant_align = findViewById(R.id.id_supernatant_align);
        id_supernatant_italic = findViewById(R.id.id_supernatant_italic);


        fm = ((FragmentActivity)context).getSupportFragmentManager();

        natantFragment = new NatantFragment();
        textFontFragment = new TextFontFragment();
        initListener();
    }

    //监听
    private void initListener() {
        id_supernatant_radiogroup.setOnCheckedChangeListener(this);
        textFontFragment.setFont(new TextFontCallBack() {
            @Override
            public void getTypeface(Typeface typeface) {
                if (textEditSupernatantListener!=null){
                    FontRecover recover = new FontRecover();
                    recover.setTypeface(typeface);
                    textEditSupernatantListener.onFontSelect(recover);
                }else {
                    Log.i("","监听未初始化，请初始化");
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View viewById = group.findViewById(checkedId);
        if (!viewById.isPressed()) {
            return;
        }
        if (id_supernatant_bubble.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(true);
            id_supernatant_font.setSelected(false);
            id_supernatant_align.setSelected(false);
            id_supernatant_italic.setSelected(false);
        }
        if (id_supernatant_font.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(false);
            id_supernatant_font.setSelected(true);
            id_supernatant_align.setSelected(false);
            id_supernatant_italic.setSelected(false);
        }
        if (id_supernatant_align.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(false);
            id_supernatant_font.setSelected(false);
            id_supernatant_align.setSelected(true);
            id_supernatant_italic.setSelected(false);
        }
        if (id_supernatant_italic.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(false);
            id_supernatant_font.setSelected(false);
            id_supernatant_align.setSelected(false);
            id_supernatant_italic.setSelected(true);
        }
    }


    public TextEditSupernatantView setTextEditSupernatantListener(TextEditSupernatantListener textEditSupernatantListener) {
        this.textEditSupernatantListener = textEditSupernatantListener;
        return this;
    }

    /** 用户自定义字体布局，传递到这里，必须是一个Fragment
     * @param fontFragment 字体布局
     * @return 返回本界面对象
     */
    public TextEditSupernatantView setFontFragment(SnbcPrintFragment fontFragment){
        if (fontFragment == null){
            //用户布局为空
            return null;
        }
        natantFragment = fontFragment;
        return this;
    }

    /**
     * 用户使用默认布局，并传递参数进行整体布局初始化操作
     * @param backs 背景参数列表传递
     * @param fonts 字体对象列表传递
     * @return 返回类型本身可以直接进行链接操作
     */
    public TextEditSupernatantView setInitData(List<Integer> backs,List<FontRecover> fonts){
        //初始化背景界面
        if (backs!=null&&backs.size()>0){

        }
        //初始化字体界面
        if (fonts!=null&&fonts.size()>0){
            natantFragment.setFontData(fonts);
        }
        return this;
    }


    /**
     * 根据传递的位置信息，进行样式修改
     * @param type 枚举
     * @return 返回类型本身可以直接进行链接操作
     */
    public TextEditSupernatantView setRadioGroupPostion(SuperNatantEnum type) {
        if (id_supernatant_radiogroup == null || id_supernatant_frame == null) {
            //未初始化控件
            return null;
        }
        switch (type) {
            case RADIOTOP:
                //标签在顶部展示（水平方向）默认方向
                RelativeLayout.LayoutParams top = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                top.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                id_supernatant_radiogroup.setLayoutParams(top);
                id_supernatant_radiogroup.setOrientation(LinearLayout.HORIZONTAL);
                RelativeLayout.LayoutParams topView = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, width / 2 - 60);
                topView.addRule(RelativeLayout.BELOW, R.id.id_supernatant_radiogroup);
                id_supernatant_frame.setLayoutParams(topView);
                break;
            case RADIODOWN:
                //标签在底部展示（水平方向）
                RelativeLayout.LayoutParams down = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                down.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                id_supernatant_radiogroup.setLayoutParams(down);
                id_supernatant_radiogroup.setOrientation(LinearLayout.HORIZONTAL);
                RelativeLayout.LayoutParams downView = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, width / 2 - 130);
                downView.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                id_supernatant_frame.setLayoutParams(downView);
                break;
            case RADIOLEFT:
                //标签在左侧展示（垂直方向）
                RelativeLayout.LayoutParams left = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                id_supernatant_radiogroup.setLayoutParams(left);
                id_supernatant_radiogroup.setOrientation(LinearLayout.VERTICAL);
                RelativeLayout.LayoutParams leftView = new RelativeLayout.LayoutParams(width - 60, RelativeLayout.LayoutParams.MATCH_PARENT);
                leftView.addRule(RelativeLayout.RIGHT_OF, R.id.id_supernatant_radiogroup);
                id_supernatant_frame.setLayoutParams(leftView);
                break;
            case RADIORIGHT:
                //标签在右侧展示（垂直方向）
                RelativeLayout.LayoutParams right = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                id_supernatant_radiogroup.setLayoutParams(right);
                id_supernatant_radiogroup.setOrientation(LinearLayout.VERTICAL);
                RelativeLayout.LayoutParams rightView = new RelativeLayout.LayoutParams(width - 60, RelativeLayout.LayoutParams.MATCH_PARENT);
                rightView.addRule(RelativeLayout.LEFT_OF, R.id.id_supernatant_radiogroup);
                id_supernatant_frame.setLayoutParams(rightView);
                break;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.id_supernatant_frame, textFontFragment);
        ft.commit();
        return this;
    }



}
