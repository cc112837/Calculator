package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.calculator.R;
import com.cc.calculator.utils.PackageUtils;

public class AboutActivity extends Activity {
    private TextView tv_service,tv_version;
    private ImageView leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
    }

    private void init() {
        tv_service=(TextView) findViewById(R.id.tv_service);
        tv_version=(TextView) findViewById(R.id.tv_version);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        tv_version.setText( "版本：V " + PackageUtils.getPackageVersion(this));
        tv_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
