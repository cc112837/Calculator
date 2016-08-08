package com.cc.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

public class FireActivity extends Activity {
    private ImageView back;
    private LinearLayout ll_result;
    private ArrayList<String> optionsItems = new ArrayList<>();
    private OptionsPickerView pvOptions;
    private String s;
    private EditText et_size;
    private TextView tv_rate, tv_time, tv_conclusion, tv_density, tv_surface, tv_appli, tv_flowrate, tv_volume, tv_inchhight;//罐尺寸，泡沫液强度选择，所需时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        Intent intent = getIntent();
        String flag = intent.getStringExtra("flag");
        init();
        if (flag.equals("imper")) {
            et_size.setHint("ft");
            tv_conclusion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_size.length() <= 0||s==null) {
                        Toast.makeText(FireActivity.this, "请检查输入项是否为空", Toast.LENGTH_LONG).show();
                    } else {
                        ll_result.setVisibility(View.VISIBLE);
                        int size = Integer.parseInt(et_size.getText().toString());
                        double areaDensity = size < 151 ? 0.16 : size < 201 ? 0.18 : size < 251 ? 0.2 : size < 301 ? 0.22 : size < 401 ? 0.25 : 0;
                        double area = Math.pow(size / (2 * 1.0), 2) * 3.14159;
                        double applirate = areaDensity * area;
                        double flowrate = Integer.parseInt(s) * applirate * 0.01;
                        double volume = flowrate * 65;
                        double inchh =volume*2 ;
                        tv_inchhight.setText(Math.round(inchh) + "gallons");
                        tv_volume.setText(Math.round(volume) + "gallons");
                        tv_flowrate.setText(Math.round(flowrate) + "gpm");
                        tv_appli.setText(Math.round(applirate) + "gpm");
                        tv_surface.setText(Math.round(area) + "ft²");
                        tv_density.setText(areaDensity + "gpm/ft²");
                    }
                }
            });

        } else {
            et_size.setHint("m");
            tv_conclusion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_size.length() <= 0||s==null) {
                        Toast.makeText(FireActivity.this, "请检查输入项是否为空", Toast.LENGTH_LONG).show();
                    } else {
                        ll_result.setVisibility(View.VISIBLE);
                        double size = Integer.parseInt(et_size.getText().toString()) * 3.280833437;
                        double areaDensity = size < 151 ? 0.16 : size < 201 ? 0.18 : size < 251 ? 0.2 : size < 301 ? 0.22 : size < 401 ? 0.25 : 0;
                        double area = Math.pow(Math.round(size * 50) / (100 * 1.0), 2) * 3.14159;
                        double applirate = areaDensity * area;
                        double flowrate = Integer.parseInt(s) * applirate * 0.01;
                        double volume = flowrate * 65;;
                        double inchh = volume*2;
                        tv_inchhight.setText(Math.round(inchh * 3.785412) + " L");
                        tv_volume.setText(Math.round(volume * 3.785412) + " L");
                        tv_flowrate.setText(Math.round(flowrate * 3.785412) + " lpm");
                        tv_appli.setText(Math.round(applirate * 3.785412) + " lpm");
                        tv_surface.setText(Math.round(0.09290304 * area) + "m²");
                        tv_density.setText(Math.round(areaDensity * 40.745835661 * 100) / (100 * 1.0) + " lpm/m²");
                    }
                }
            });

        }
    }

    private void setVaule() {
        optionsItems.add("1");
        optionsItems.add("3");
        optionsItems.add("6");
    }

    private void init() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pvOptions = new OptionsPickerView(this);
        ll_result = (LinearLayout) findViewById(R.id.ll_result);
        ll_result.setVisibility(View.GONE);
        tv_appli = (TextView) findViewById(R.id.tv_appli);
        et_size = (EditText) findViewById(R.id.et_size);
        tv_rate = (TextView) findViewById(R.id.tv_rate);
        tv_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(FireActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                pvOptions.show();
            }
        });
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_density = (TextView) findViewById(R.id.tv_density);
        tv_surface = (TextView) findViewById(R.id.tv_surface);
        tv_flowrate = (TextView) findViewById(R.id.tv_flowrate);
        tv_volume = (TextView) findViewById(R.id.tv_volume);
        tv_inchhight = (TextView) findViewById(R.id.tv_inchhight);
        tv_conclusion = (TextView) findViewById(R.id.tv_conclusion);
        setVaule();
        pvOptions.setPicker(optionsItems);
        pvOptions.setTitle("浓缩泡沫比例");
        pvOptions.setCyclic(false);
        pvOptions.setSelectOptions(1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                s = optionsItems.get(options1);
                tv_rate.setText(s + "%");
            }
        });
        tv_time.setText("65min");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pvOptions.isShowing()) {
            pvOptions.dismiss();
        }
    }
}
