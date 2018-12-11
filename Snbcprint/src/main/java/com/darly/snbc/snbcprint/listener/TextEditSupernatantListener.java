package com.darly.snbc.snbcprint.listener;

import com.darly.snbc.snbcprint.bean.AlignmentThickness;
import com.darly.snbc.snbcprint.bean.FontRecover;
import com.darly.snbc.snbcprint.bean.FontSizeSpacing;

/**
 * 文本编辑浮层接口
 * 包名称：com.darly.snbc.snbcprint.listener
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/7 9:40
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface TextEditSupernatantListener {

    //本地背景选择，传递到界面增加文字编辑框
    void onBackGroundLocal(int resId);
    //网络背景选择，传递到界面增加文字编辑框
    //void onBackGroundNet(String resId);
    //获取字体，传递到界面进行修改字体
    void onFontSelect(FontRecover font);
    //文字粗细对齐方式选择，传递到界面进行文字粗细对齐设置
    void onAlignmentThickness (AlignmentThickness alignmentThickness);
    //字号间距行距方式选择，传递到界面进行文字字号行间距字间距设置
    void onFontSizeSpacing (FontSizeSpacing fontSizeSpacing);

}
