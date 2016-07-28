package com.cc.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WaterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Intent intent=getIntent();
        String flag=intent.getStringExtra("flag");
        if (flag.equals("imper")){

        }
        else{

        }
    }
}
