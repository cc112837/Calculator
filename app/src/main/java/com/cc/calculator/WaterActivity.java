package com.cc.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

public class WaterActivity extends Activity {
    private ImageView back;
    private TextView tv_diameter,tv_height,tv_press,tv_2,tv_22,tv_3,tv_33,tv_4,tv_44,tv_line1;
    private LinearLayout ll_result,ll_press;
    private ArrayList<String> optionsItems = new ArrayList<>();
    private OptionsPickerView pvOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Intent intent = getIntent();
        String flag = intent.getStringExtra("flag");
        init();
        if (flag.equals("imper")) {
            tv_press.setText("psi");
            tv_diameter.setText("″");
            tv_22.setText("gpm");
            tv_44.setText("ft");
            tv_height.setText("0ft");
            ftValue();
            pvOptions.setPicker(optionsItems);
            pvOptions.setTitle("直径尺寸");
            pvOptions.setCyclic(false);
            pvOptions.setSelectOptions(1);
            pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    String s = optionsItems.get(options1);
                    if("12".equals(s)){
                        special12();
                    }
                    else{
                        usual();
                    }
                    tv_diameter.setText(s + "″");
                }
            });
        } else {
            tv_press.setText("kpa");
            tv_diameter.setText("mm");
            tv_22.setText("lpm");
            tv_44.setText("m");
            tv_height.setText("0m");
            mValue();
            pvOptions.setPicker(optionsItems);
            pvOptions.setTitle("直径尺寸");
            pvOptions.setCyclic(false);
            pvOptions.setSelectOptions(1);
            pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    String s = optionsItems.get(options1);
                    if ("304".equals(s)) {
                        special12();
                    }else{
                        usual();
                    }
                    tv_diameter.setText(s + "mm");
                }
            });
        }
        tv_diameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

    private void usual() {
        tv_2.setText("总流量");
        tv_3.setText("软管的数量");
        tv_4.setText("软管的长度");
        tv_line1.setVisibility(View.GONE);
        ll_press.setVisibility(View.GONE);
    }

    private void special12() {
        tv_2.setText("流速");
        tv_3.setText("单个软管长度");
        tv_4.setText("总的软管长度");
        tv_line1.setVisibility(View.VISIBLE);
        ll_press.setVisibility(View.VISIBLE);
    }

    private void init() {
        back = (ImageView) findViewById(R.id.back);
        pvOptions = new OptionsPickerView(this);
        ll_result=(LinearLayout) findViewById(R.id.ll_result);
        ll_result.setVisibility(View.GONE);
        tv_diameter = (TextView) findViewById(R.id.tv_diameter);
        tv_height=(TextView) findViewById(R.id.tv_height);
        tv_press=(TextView) findViewById(R.id.tv_press);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_22=(TextView) findViewById(R.id.tv_22);
        tv_3=(TextView) findViewById(R.id.tv_3);
        tv_33 = (TextView) findViewById(R.id.tv_33);
        tv_4=(TextView) findViewById(R.id.tv_4);
        tv_line1=(TextView) findViewById(R.id.tv_line1);
        tv_44=(TextView) findViewById(R.id.tv_44);
        ll_press=(LinearLayout) findViewById(R.id.ll_press);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mValue() {
        optionsItems.add("38");
        optionsItems.add("45");
        optionsItems.add("51");
        optionsItems.add("64");
        optionsItems.add("80");
        optionsItems.add("93");
        optionsItems.add("102");
        optionsItems.add("127");
        optionsItems.add("152");
        optionsItems.add("183");
        optionsItems.add("304");
    }

    private void ftValue() {
        optionsItems.add("1.5");
        optionsItems.add("1.75");
        optionsItems.add("2");
        optionsItems.add("2.5");
        optionsItems.add("3");
        optionsItems.add("3.5");
        optionsItems.add("4");
        optionsItems.add("5");
        optionsItems.add("6");
        optionsItems.add("7.5");
        optionsItems.add("12");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pvOptions.isShowing()) {
            pvOptions.dismiss();
        }
    }
}
