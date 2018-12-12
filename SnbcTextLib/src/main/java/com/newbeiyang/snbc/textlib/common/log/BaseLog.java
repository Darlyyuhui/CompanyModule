package com.newbeiyang.snbc.textlib.common.log;

import android.util.Log;

/**基础日志类。
 * @author Darly/张宇辉/2017/11/23 14:18
 * @version 1.0/com.darly.common
 */
public class BaseLog {

    private static final int MAX_LENGTH = 4000;

    public static void printDefault(int type, String tag, String msg) {

        int index = 0;
        int length = msg.length();
        int countOfSub = length / MAX_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + MAX_LENGTH);
                printSub(type, tag, sub);
                index += MAX_LENGTH;
            }
            printSub(type, tag, msg.substring(index, length));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case SuperNatantLog.V:
                Log.v(tag, sub);
                break;
            case SuperNatantLog.D:
                Log.d(tag, sub);
                break;
            case SuperNatantLog.I:
                Log.i(tag, sub);
                break;
            case SuperNatantLog.W:
                Log.w(tag, sub);
                break;
            case SuperNatantLog.E:
                Log.e(tag, sub);
                break;
            case SuperNatantLog.A:
                Log.wtf(tag, sub);
                break;
        }
    }

}
