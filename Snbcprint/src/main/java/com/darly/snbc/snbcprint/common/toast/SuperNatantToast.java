package com.darly.snbc.snbcprint.common.toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * SuperNatantToast 实现全局工具类
 *
 * @author Darly/张宇辉/2017/11/23 14:18
 */
public class SuperNatantToast {

    private static final int TOASTTIME = Toast.LENGTH_SHORT;

    private static Toast toast = null;

    private static SuperNatantToast instance = null;
    //展示提示信息
    private boolean show = true;

    private static Context context;

    private SuperNatantToast() {
    }

    public static SuperNatantToast getInstance() {
        if (instance == null) {
            instance = new SuperNatantToast();
        }
        return instance;
    }

    public boolean isShow() {
        return show;
    }

    public void setContext(Context cxt) {
        SuperNatantToast.context = cxt;
    }

    public static void showToast(Context context, String msg) {
        if (!getInstance().isShow())
            return;

        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(Context context, int msg) {
        if (!getInstance().isShow())
            return;

        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(String msg) {
        if (!getInstance().isShow())
            return;

        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(int msg) {
        if (!getInstance().isShow())
            return;

        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void mustShow(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void mustShow(Context context, int msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void mustShow(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void mustShow(int msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, TOASTTIME);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}