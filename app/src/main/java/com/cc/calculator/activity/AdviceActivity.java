package com.cc.calculator.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cc.calculator.R;

public class AdviceActivity extends Activity {
    private ImageView back;
    private Button btn_submit;
    private EditText et_advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        init();
    }

    private void init() {
        back=(ImageView) findViewById(R.id.back);
        btn_submit=(Button) findViewById(R.id.btn_submit);
        et_advice=(EditText) findViewById(R.id.et_advice);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO: 2016/8/16 提交用户意见
                String advice=et_advice.getText().toString();
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
