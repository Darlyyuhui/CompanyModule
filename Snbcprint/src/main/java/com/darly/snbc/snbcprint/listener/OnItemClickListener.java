package com.darly.snbc.snbcprint.listener;

import android.graphics.Typeface;
import android.view.View;

/**
 * Created by maxiao on 2018/10/19.
 */
public interface OnItemClickListener {

    void onItemClick(Typeface typeface, int position);

    void onItemLongClick(View view);
}
