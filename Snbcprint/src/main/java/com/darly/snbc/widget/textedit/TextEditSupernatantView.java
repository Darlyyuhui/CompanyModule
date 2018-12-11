package com.darly.snbc.widget.textedit;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.darly.snbc.observer.SupernatantCfg;
import com.darly.snbc.snbcprint.R;
import com.darly.snbc.snbcprint.bean.EditSupernatant;
import com.darly.snbc.snbcprint.common.SuperNatantEnum;
import com.darly.snbc.snbcprint.common.log.SuperNatantLog;
import com.darly.snbc.snbcprint.fragment.BaseTextFragment;
import com.darly.snbc.snbcprint.fragment.align.TextAlignFragment;
import com.darly.snbc.snbcprint.fragment.bg.TextBgFragment;
import com.darly.snbc.snbcprint.fragment.font.TextFontFragment;
import com.darly.snbc.snbcprint.fragment.spacing.TextSpacingFragment;
import com.darly.snbc.snbcprint.listener.OnEditSupernatantListener;
import com.darly.snbc.snbcprint.listener.TextEditSupernatantListener;

/**
 * 文本编辑浮层主类
 * 包名称：com.darly.snbc.widget.textedit
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 9:38
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TextEditSupernatantView extends RelativeLayout implements RadioGroup.OnCheckedChangeListener,OnEditSupernatantListener {
    private int width;
    private RelativeLayout id_supernatant_relative;

    private FrameLayout id_supernatant_frame;
    private RadioGroup id_supernatant_radiogroup;

    private RadioButton id_supernatant_bubble;
    private RadioButton id_supernatant_font;
    private RadioButton id_supernatant_align;
    private RadioButton id_supernatant_italic;


    private FragmentManager fm;

    private TextEditSupernatantListener textEditSupernatantListener;

    //字体布局
    private BaseTextFragment fontFragment;

    private BaseTextFragment bgFragment;
    private BaseTextFragment alignFragment;
    private BaseTextFragment spacingFragment;

    private BaseTextFragment currentFragment;

    private EditSupernatant supernatant = new EditSupernatant();

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
        id_supernatant_relative.setLayoutParams(new RelativeLayout.LayoutParams(width, width / 2));

        id_supernatant_frame = findViewById(R.id.id_supernatant_frame);

        id_supernatant_radiogroup = findViewById(R.id.id_supernatant_radiogroup);
        id_supernatant_bubble = findViewById(R.id.id_supernatant_bubble);
        id_supernatant_font = findViewById(R.id.id_supernatant_font);
        id_supernatant_align = findViewById(R.id.id_supernatant_align);
        id_supernatant_italic = findViewById(R.id.id_supernatant_italic);


        fm = ((FragmentActivity) context).getSupportFragmentManager();
        fontFragment = new TextFontFragment();
        bgFragment = new TextBgFragment();
        alignFragment = new TextAlignFragment();
        spacingFragment = new TextSpacingFragment();
        initListener();
    }

    //监听
    private void initListener() {
        id_supernatant_radiogroup.setOnCheckedChangeListener(this);
        id_supernatant_bubble.setSelected(true);
        switchFragment(bgFragment).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (id_supernatant_bubble.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(true);
            id_supernatant_font.setSelected(false);
            id_supernatant_align.setSelected(false);
            id_supernatant_italic.setSelected(false);
            switchFragment(bgFragment).commit();
        }
        if (id_supernatant_font.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(false);
            id_supernatant_font.setSelected(true);
            id_supernatant_align.setSelected(false);
            id_supernatant_italic.setSelected(false);
            switchFragment(fontFragment).commit();
        }
        if (id_supernatant_align.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(false);
            id_supernatant_font.setSelected(false);
            id_supernatant_align.setSelected(true);
            id_supernatant_italic.setSelected(false);
            switchFragment(alignFragment).commit();
        }
        if (id_supernatant_italic.getId() == checkedId) {
            //Fragment
            id_supernatant_bubble.setSelected(false);
            id_supernatant_font.setSelected(false);
            id_supernatant_align.setSelected(false);
            id_supernatant_italic.setSelected(true);
            switchFragment(spacingFragment).commit();
        }
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
    public void setRadioGroupPostion(SuperNatantEnum type) {
        if (id_supernatant_radiogroup == null || id_supernatant_frame == null) {
            //未初始化控件
            return;
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
     * @param fragment 需要展示的Fragment
     * @return FragmentTransaction
     */
    public FragmentTransaction switchFragment(BaseTextFragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        if (!fragment.isAdded()){
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null){
                ft.hide(currentFragment);
            }
            ft.add(R.id.id_supernatant_frame, fragment);
        }else {
            ft.hide(currentFragment).show(fragment);
        }
        currentFragment = fragment;
        return ft;
    }


    @Override
    public void getBackGround(EditSupernatant background) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(background,SuperNatantEnum.NATANT_TEXTBACKGROUND);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }

    @Override
    public void getTypeface(EditSupernatant font) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(font,SuperNatantEnum.NATANT_FONTRECOVER);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }

    @Override
    public void getAlign(EditSupernatant align) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(align,SuperNatantEnum.NATANT_ALIGNMENTTHICKNESS);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }

    @Override
    public void getSpacing(EditSupernatant spacing) {
        if (textEditSupernatantListener != null) {
            textEditSupernatantListener.onSelectSupernatant(spacing,SuperNatantEnum.NATANT_FONTSIZESPACING);
        } else {
            SuperNatantLog.i("监听未初始化，请初始化");
        }
    }
}
