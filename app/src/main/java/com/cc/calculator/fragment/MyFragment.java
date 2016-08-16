package com.cc.calculator.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cc.calculator.R;
import com.cc.calculator.activity.AboutActivity;
import com.cc.calculator.activity.AdviceActivity;
import com.cc.calculator.activity.CollectionActivity;
import com.cc.calculator.activity.SettingActivity;

public class MyFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_head;
    private LinearLayout ll_set, ll_share, ll_collect, ll_about, ll_record;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        iv_head = (ImageView) v.findViewById(R.id.iv_head);
        ll_set = (LinearLayout) v.findViewById(R.id.ll_set);
        ll_share = (LinearLayout) v.findViewById(R.id.ll_share);
        ll_collect = (LinearLayout) v.findViewById(R.id.ll_collect);
        ll_about = (LinearLayout) v.findViewById(R.id.ll_about);
        ll_record = (LinearLayout) v.findViewById(R.id.ll_record);
        iv_head.setOnClickListener(this);
        ll_set.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
        ll_about.setOnClickListener(this);
        ll_record.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_about://关于界面
                intent=new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_share://分享
                break;
            case R.id.ll_collect://收藏
                intent=new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_record://意见反馈
                intent=new Intent(getActivity(), AdviceActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_set://设置
                intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_head://图像选择
                break;
        }
    }
}
