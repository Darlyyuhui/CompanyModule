package com.darly.snbc.snbcprint.font;

import android.view.View;
import android.widget.TextView;

/**
 * Created by maxiao on 2018/10/19.
 */
public interface OnItemClickListener {
    void onItemClick(TextView view, int position);
    void onItemLongClick(View view);
}
