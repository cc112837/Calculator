package com.cc.calculator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cc.calculator.R;
import com.cc.calculator.activity.FireActivity;
import com.cc.calculator.activity.WaterActivity;


public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button water,fire,meter,imper;//水带磨损，燃烧成本，米，英制

    String flag="imper";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    private void init(View v) {
        water=(Button) v.findViewById(R.id.water);
        fire=(Button) v.findViewById(R.id.fire);
        meter=(Button) v.findViewById(R.id.meter);
        imper=(Button) v.findViewById(R.id.imper);
        water.setOnClickListener(this);
        fire.setOnClickListener(this);
        meter.setOnClickListener(this);
        imper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.water:
                Intent intent=new Intent(getActivity(),WaterActivity.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
                break;
            case  R.id.fire:
                Intent intent1=new Intent(getActivity(),FireActivity.class);
                intent1.putExtra("flag",flag);
                startActivity(intent1);
                break;
            case R.id.meter:
                flag="meter";
                meter.setBackgroundResource(R.mipmap.left);
                meter.setTextColor(getResources().getColor(R.color.white));
                imper.setBackgroundResource(R.mipmap.white_right);
                imper.setTextColor(getResources().getColor(R.color.buttoncolor));
                break;
            case R.id.imper:
                flag="imper";
                meter.setBackgroundResource(R.mipmap.white_left);
                meter.setTextColor(getResources().getColor(R.color.buttoncolor));
                imper.setBackgroundResource(R.mipmap.right);
                imper.setTextColor(getResources().getColor(R.color.white));
                break;
        }

    }
}
