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

import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.utils.MyAndroidUtil;

public class SettingActivity extends Activity {
    private ImageView back;
    private TextView tv_volume;
    private Button out;
    private LinearLayout ll_msg,ll_updata,ll_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        back=(ImageView) findViewById(R.id.back);
        tv_volume=(TextView) findViewById(R.id.tv_volume);
        ll_clear=(LinearLayout) findViewById(R.id.ll_clear);
        ll_updata=(LinearLayout) findViewById(R.id.ll_updata);
        ll_msg=(LinearLayout) findViewById(R.id.ll_msg);
        out=(Button) findViewById(R.id.out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
                MyAndroidUtil.removeXml(Constants.LOGIN_PWD);
                startActivity(intent);
                finish();
            }
        });
        ll_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/8/16 新消息通知
            }
        });
        ll_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"清理缓存中",Toast.LENGTH_LONG).show();
                SystemClock.sleep(3000);
                tv_volume.setText("0M");
                Toast.makeText(SettingActivity.this,"清理完成",Toast.LENGTH_LONG).show();
            }
        });
        ll_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,UpdataPwdActivity.class);
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
