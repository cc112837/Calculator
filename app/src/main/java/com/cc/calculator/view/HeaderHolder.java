package com.cc.calculator.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cc.calculator.R;


/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 创建时间：2017/1/22 13:49
 */
public class HeaderHolder extends RecyclerView.ViewHolder {
    public ImageView data_image;
    public RelativeLayout header;
    public TextView data_title;

    public HeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        data_title=(TextView) itemView.findViewById(R.id.data_title);
        header=(RelativeLayout) itemView.findViewById(R.id.header);
        data_image = (ImageView) itemView.findViewById(R.id.data_image);
    }
}
