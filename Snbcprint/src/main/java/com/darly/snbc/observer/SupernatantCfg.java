package com.darly.snbc.observer;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * 系统参数设置类，包括相机参数和设备宽高参数等。
 * @author  Darly/张宇辉/2017/11/23 14:35
 */

public class SupernatantCfg {

    private static SharedPreferences mysp = null;
    private static final String PREFERENCE_NAME = "SupernatantCfg";

    public static void init(Context context) {
        mysp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        Log.i("SupernatantCfg","初始化.."+mysp);
    }


    //设置屏幕宽度
    public static void setWidth(int w) {
       
        mysp.edit().putInt("phoneWidth", w).commit();
    }

    //获取屏幕宽度
    public static int getWidth() {
       
        return mysp.getInt("phoneWidth", 0);
    }

    //设置屏幕高度
    public static void setHeight(int h) {
       
        mysp.edit().putInt("phoneHeight", h).commit();
    }

    //获取屏幕高度
    public static int getHeight() {
       
        return mysp.getInt("phoneHeight", 0);
    }

    public static boolean isDebug() {
        return mysp.getBoolean("isDebug", false);
    }

    public static void setIsDebug(boolean isDebug) {
        mysp.edit().putBoolean("isDebug", isDebug).commit();
    }

}
