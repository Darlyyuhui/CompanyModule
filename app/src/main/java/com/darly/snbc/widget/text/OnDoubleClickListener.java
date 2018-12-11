package com.darly.snbc.widget.text;


import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * 双击监听事件
 * 包名称：com.darly.snbc.companymodule
 * 作者：zhangyuhui 项目名称：CompanyModule
 * 日期：2018/12/10 17:45
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class OnDoubleClickListener implements View.OnTouchListener {

    private int count = 0;//点击次数

    private Handler handler;
    /**
     * 两次点击时间间隔，单位毫秒
     */
    private final int totalTime = 1000;
    /**
     * 自定义回调接口
     */
    private DoubleClickCallback mCallback;

    public interface DoubleClickCallback {

        void onClick(View v);

        void onDoubleClick(View v);
    }

    public OnDoubleClickListener(DoubleClickCallback callback) {
        super();
        this.mCallback = callback;
        handler = new Handler();
    }

    /**
     * 触摸事件处理
     *
     * @param v     点击的控件
     * @param event 事件
     * @return 是否处理
     */
    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {//按下
            count++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (count == 1) {
                        mCallback.onClick(v);
                    } else if (count == 2) {
                        mCallback.onDoubleClick(v);
                    }
                    handler.removeCallbacksAndMessages(null);
                    //清空handler延时，并防内存泄漏
                    count = 0;//计数清零
                }
            }, totalTime);//延时timeout后执行run方法中的代码
        }
        return false;//让点击事件继续传播，方便再给View添加其他事件监听
    }
}
