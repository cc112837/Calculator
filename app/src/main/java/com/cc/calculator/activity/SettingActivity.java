package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cc.calculator.MyApplication;
import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.utils.MyAndroidUtil;

public class SettingActivity extends Activity {
    private ImageView back;
    private TextView tv_volume;
    private Button out;
    private LinearLayout ll_updata, ll_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        back = (ImageView) findViewById(R.id.back);
        tv_volume = (TextView) findViewById(R.id.tv_volume);
        ll_clear = (LinearLayout) findViewById(R.id.ll_clear);
        ll_updata = (LinearLayout) findViewById(R.id.ll_updata);
        out = (Button) findViewById(R.id.out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (MyApplication.sharedPreferences.getBoolean(Constants.SHARELOGIN,
                        false)) {
                    MyAndroidUtil.editXml(
                            Constants.SHARELOGIN, false);
                } else {
                    MyAndroidUtil.removeXml(Constants.LOGIN_PWD);
                }
                intent.putExtra("set", "set");
                setResult(-1, intent);
                finish();
            }
        });
        ll_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "清理完成", Toast.LENGTH_LONG).show();
                SystemClock.sleep(3000);
                tv_volume.setText("0M");
            }
        });
        ll_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, UpdataPwdActivity.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
