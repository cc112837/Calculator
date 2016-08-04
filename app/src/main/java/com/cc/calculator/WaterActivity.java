package com.cc.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

public class WaterActivity extends Activity {
    private ImageView back;
    private String flowrate, length, number, press, s, height, ss;
    private EditText tv_22, tv_33, tv_44, tv_press, tv_height;
    private TextView tv_diameter, tv_conclusion, tv_totalloss, tv_weight,
            tv_volume, tv_2, tv_3, tv_4, tv_line1, tv_111, tv_222, tv_line2,
            tv_333, tv_line3, tv_444;
    private LinearLayout ll_result, ll_press, tv_ll1, ll_4;
    private ArrayList<String> optionsItems = new ArrayList<>();
    private OptionsPickerView pvOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Intent intent = getIntent();
        final String flag = intent.getStringExtra("flag");
        init();
        if (flag.equals("imper")) {//英尺
            tv_press.setHint("psi");
            tv_diameter.setText("″");
            tv_22.setHint("gpm");
            tv_44.setHint("ft");
            tv_height.setHint("0ft");
            ftValue();
            pvOptions.setPicker(optionsItems);
            pvOptions.setTitle("水带直径");
            pvOptions.setCyclic(false);
            pvOptions.setSelectOptions(1);
            pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    s = optionsItems.get(options1);
                    ll_result.setVisibility(View.GONE);
                    if ("12".equals(s)) {
                        special12();
                    } else {
                        usual();
                    }
                    tv_diameter.setText(s + "″");
                }
            });
            tv_conclusion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flowrate = tv_22.getText().toString();
                    number = tv_33.getText().toString();
                    length = tv_44.getText().toString();
                    press = tv_press.getText().toString();
                    height = tv_height.getText().toString();
                    if (flowrate.length() <= 0 || number.length() <= 0 || length.length() <= 0 || height.length() <= 0 || s == null) {
                        Toast.makeText(WaterActivity.this, "请检查输入，输入不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        double b = Double.parseDouble(s);
                        if (("").equals(press) && b!=12.0) {
                            double volume = Math.pow(b / 2, 2) * 3.14159 / 144 * Integer.parseInt(length) * Integer.parseInt(number) * 7.48;
                            double weight = volume * 8.5 * 1.1;
                            double c = (b == 1.5) ? 20.3 : (b == 1.75) ? 30.14 : (b == 2) ? 40.38 : (b == 2.5) ? 66.6 : (b == 3) ? 110.25 : (b == 3.5) ?
                                    171.5 : (b == 4) ? 228 : (b == 5) ? 389 : (b == 6) ? 617 : (b == 7.25) ? 1040 : 0;
                            double loss = Math.pow((Double.parseDouble(flowrate) / Integer.parseInt(number)), 2) / (Math.pow(c, 2))
                                    / 100 * Integer.parseInt(length) + Integer.parseInt(height) * 0.4335161;
                            ll_result.setVisibility(View.VISIBLE);
                            tv_volume.setText(Math.round(volume) + " gallons");
                            tv_weight.setText(Math.round(weight) + " kg");
                            tv_totalloss.setText(Math.round(loss) + " psi");
                        } else if (("").equals(press) &&b==12.0) {
                            Toast.makeText(WaterActivity.this, "请检查输入，进口压力不能为空", Toast.LENGTH_LONG).show();
                        } else {
                            ll_result.setVisibility(View.VISIBLE);
                            int num=Math.round(Integer.parseInt(length)/(Integer.parseInt(number)));
                            tv_volume.setText( num+ " #");
                            tv_weight.setText("0" + " psi");
                            tv_333.setText(Math.round(Integer.parseInt(height)*0.4335)+ "psi");
                            tv_444.setText("0" + "psi");
                            tv_totalloss.setText("0" + " psi");
                        }
                    }
                }
            });


        } else {//米
            tv_press.setHint("kpa");
            tv_diameter.setText("mm");
            tv_22.setHint("lpm");
            tv_44.setHint("m");
            tv_height.setHint("0m");
            mValue();
            pvOptions.setPicker(optionsItems);
            pvOptions.setTitle("水带直径");
            pvOptions.setCyclic(false);
            pvOptions.setSelectOptions(1);
            pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    ss = optionsItems.get(options1);
                    ll_result.setVisibility(View.GONE);
                    if ("304".equals(ss)) {
                        special12();
                    } else {
                        usual();
                    }
                    tv_diameter.setText(ss + "mm");
                }
            });

            tv_conclusion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flowrate = tv_22.getText().toString();
                    number = tv_33.getText().toString();
                    press = tv_press.getText().toString();
                    length = tv_44.getText().toString();
                    height = tv_height.getText().toString();
                    if (flowrate.length() <= 0 || number.length() <= 0 || length.length() <= 0 || height.length() <= 0 || ss.length() == 0) {
                        Toast.makeText(WaterActivity.this, "请检查输入，输入不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        int dia = Integer.parseInt(ss);
                        if (("").equals(press) && dia != 304) {
                            ll_result.setVisibility(View.VISIBLE);
                            double b = (dia == 38) ? 1.5 : (dia == 45) ? 1.75 : (dia == 51) ? 2 : (dia == 64) ? 2.5 : (dia == 80) ? 3 : (dia == 93) ? 3.5
                                    : (dia == 102) ? 4 : (dia == 127) ? 5 : (dia == 152) ? 6 : (dia == 183) ? 7.25 : (dia == 304) ? 12 : 0;
                            double volume = Math.pow(b / 2, 2) * 3.14159 / 144 * Integer.parseInt(length) * 3.28043 * Integer.parseInt(number) * 7.48;
                            double weight = volume * 8.5 * 1.1;
                            double c = (b == 1.5) ? 20.3 : (b == 1.75) ? 30.14 : (b == 2) ? 40.38 : (b == 2.5) ? 66.6 : (b == 3) ? 110.25 : (b == 3.5) ?
                                    171.5 : (b == 4) ? 228 : (b == 5) ? 389 : (b == 6) ? 617 : (b == 7.25) ? 1040 : 0;
                            double loss = Math.pow((Double.parseDouble(flowrate) * 0.2641720373 / Integer.parseInt(number)), 2) / (Math.pow(c, 2))
                                    / 100 * Integer.parseInt(length) * 3.28043 + Integer.parseInt(height) * 3.28043 * 0.4335161;
                            tv_volume.setText(Math.round(volume * 3.785412) + " liters");
                            tv_weight.setText(Math.round(weight * 0.4535929) + " kg");
                            tv_totalloss.setText(Math.round(loss * 6.894745) + " kpa");
                        } else if (("").equals(press) && dia == 304) {
                            Toast.makeText(WaterActivity.this, "请检查输入，进口压力不能为空", Toast.LENGTH_LONG).show();
                        } else {
                            ll_result.setVisibility(View.VISIBLE);
                            int num=Math.round(Integer.parseInt(length) / (Integer.parseInt(number)));
                            tv_volume.setText( num+ " #");
                            tv_weight.setText("0" + " kpa");
                            tv_totalloss.setText("0" + " kpa");
                            tv_333.setText(Math.round(Integer.parseInt(height)*0.4335*6.894745) + "kpa");
                            tv_444.setText("0" + "kpa");
                        }
                    }
                }
            });
        }
        tv_diameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(WaterActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                pvOptions.show();
            }
        });
    }


    private void usual() {
        tv_2.setText("总流量");
        tv_3.setText("水带铺设数量");
        tv_4.setText("铺设长度");
        tv_line1.setVisibility(View.GONE);
        ll_press.setVisibility(View.GONE);
        tv_ll1.setVisibility(View.GONE);
        ll_4.setVisibility(View.GONE);
        tv_111.setText("水带存水量");
        tv_222.setText("水带的总重量");
        tv_line2.setVisibility(View.GONE);
        tv_line3.setVisibility(View.GONE);

    }

    private void special12() {
        tv_2.setText("水流强度");
        tv_3.setText("单个水带长度");
        tv_4.setText("总的水带长度");
        tv_line1.setVisibility(View.VISIBLE);
        ll_press.setVisibility(View.VISIBLE);
        tv_ll1.setVisibility(View.VISIBLE);
        ll_4.setVisibility(View.VISIBLE);
        tv_111.setText("水管数量");
        tv_222.setText("耦合损失");
        tv_line2.setVisibility(View.VISIBLE);
        tv_line3.setVisibility(View.VISIBLE);
    }

    private void init() {
        back = (ImageView) findViewById(R.id.back);
        pvOptions = new OptionsPickerView(this);
        ll_result = (LinearLayout) findViewById(R.id.ll_result);
        ll_result.setVisibility(View.GONE);
        tv_conclusion = (TextView) findViewById(R.id.tv_conclusion);
        tv_diameter = (TextView) findViewById(R.id.tv_diameter);
        tv_height = (EditText) findViewById(R.id.tv_height);
        tv_press = (EditText) findViewById(R.id.tv_press);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_22 = (EditText) findViewById(R.id.tv_22);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_33 = (EditText) findViewById(R.id.tv_33);
        tv_4 = (TextView) findViewById(R.id.tv_4);
        tv_line1 = (TextView) findViewById(R.id.tv_line1);
        tv_44 = (EditText) findViewById(R.id.tv_44);
        ll_press = (LinearLayout) findViewById(R.id.ll_press);
        tv_weight = (TextView) findViewById(R.id.tv_weight);
        tv_volume = (TextView) findViewById(R.id.tv_volume);
        tv_totalloss = (TextView) findViewById(R.id.tv_totalloss);
        tv_111 = (TextView) findViewById(R.id.tv_111);
        tv_222 = (TextView) findViewById(R.id.tv_222);
        tv_line2 = (TextView) findViewById(R.id.tv_line2);
        tv_333 = (TextView) findViewById(R.id.tv_333);
        tv_line3 = (TextView) findViewById(R.id.tv_line3);
        tv_444 = (TextView) findViewById(R.id.tv_444);
        tv_ll1 = (LinearLayout) findViewById(R.id.tv_ll1);
        ll_4 = (LinearLayout) findViewById(R.id.ll_4);
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
        optionsItems.add("7.25");
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
