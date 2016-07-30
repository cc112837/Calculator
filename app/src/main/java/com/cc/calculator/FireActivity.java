package com.cc.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FireActivity extends Activity {
    private ImageView back;
    private LinearLayout ll_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        Intent intent=getIntent();
        String flag=intent.getStringExtra("flag");
        init();
        if (flag.equals("imper")){

        }
        else{

        }
    }

    private void init() {
        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_result=(LinearLayout) findViewById(R.id.ll_result);
        ll_result.setVisibility(View.GONE);
    }
}
