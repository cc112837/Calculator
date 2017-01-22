package com.cc.calculator.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cc.calculator.R;


/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 创建时间：2017/1/22 13:49
*/
public class DescHolder extends RecyclerView.ViewHolder {
    public TextView book_title_item;
    public RelativeLayout data_item;

    public DescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        data_item=(RelativeLayout) itemView.findViewById(R.id.data_item);
        book_title_item = (TextView) itemView.findViewById(R.id.book_title_item);
    }
}