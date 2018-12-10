package com.darly.snbc.snbcprint.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by maxiao on 2018/10/19.
 */
public class TypefaceCreat {

    public static Typeface getTypeface(Context context, String name) {
        switch (name){
            case "方正卡通简体":
                name="fangzhengkatongjianti";
                break;
            case "楷体":
                name="kaiti";
                break;
            case "瘦金体":
                name="shoujinti";
                break;
            case "隶书":
                name="lishu";
                break;
            case "华康娃娃体":
                name="huakangwawati";
                break;
            case "方正正圆":
                name="fangzhengzhengyuan";
                break;
        }
        AssetManager mgr = context.getAssets();
        try{
            Typeface tf = Typeface.createFromAsset(mgr, "fonts/" + name + ".ttf");
            return tf;
        }catch (Exception e){
            Typeface tf = Typeface.createFromAsset(mgr, "fonts/" + name + ".TTF");
            return tf;
        }


    }

}
