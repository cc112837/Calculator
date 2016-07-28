package com.cc.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button water,fire,meter,imper;//水带磨损，燃烧成本，米，英制

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        water=(Button) findViewById(R.id.water);
        fire=(Button) findViewById(R.id.fire);
        meter=(Button) findViewById(R.id.meter);
        imper=(Button) findViewById(R.id.imper);
        water.setOnClickListener(this);
        fire.setOnClickListener(this);
        meter.setOnClickListener(this);
        imper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String flag="imper";
        switch (v.getId()){

            case R.id.water:
                Intent intent=new Intent(MainActivity.this,WaterActivity.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
                break;
            case  R.id.fire:
                Intent intent1=new Intent(MainActivity.this,FireActivity.class);
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
